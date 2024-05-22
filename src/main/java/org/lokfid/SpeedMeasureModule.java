package org.lokfid;
//this plugin was created by IceTank and reversed from source by Lokfid
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.rusherhack.client.api.events.client.EventUpdate;
import org.rusherhack.client.api.events.render.EventRender2D;
import org.rusherhack.client.api.events.render.EventRender3D;
import org.rusherhack.client.api.feature.module.ModuleCategory;
import org.rusherhack.client.api.feature.module.ToggleableModule;
import org.rusherhack.client.api.utils.ChatUtils;
import org.rusherhack.core.event.subscribe.Subscribe;

public class SpeedMeasureModule extends ToggleableModule {
	@Nullable
	PositionHolder firstPos = null;

	double lastDistance = 0.0D;

	double lastSpeed = 0.0D;

	public SpeedMeasureModule() {
		super("SpeedMeasure", "Measures Speed over time", ModuleCategory.CLIENT);
	}

	@Subscribe
	private void onRender2D(EventRender2D event) {}

	@Subscribe
	private void onUpdate(EventUpdate event) {}

	@Subscribe
	private void onRender3D(EventRender3D event) {}

	public void onDisable() {
		this.lastSpeed = 0.0D;
		this.lastDistance = 0.0D;
	}

	public void measureStart() {
		if (mc.player == null)
			return;
		this.firstPos = new PositionHolder(mc.player.getOnPos(), System.currentTimeMillis());
	}

	public void measureEnd() {
		if (mc.player == null)
			return;
		PositionHolder secondPos = new PositionHolder(mc.player.getOnPos(), System.currentTimeMillis());
		if (this.firstPos == null)
			return;
		double distance = distanceXZ(this.firstPos, secondPos);
		ChatUtils.print("Distance: %.2f".formatted(new Object[] { Double.valueOf(distance) }));
		ChatUtils.print("Time: %s".formatted(new Object[] { convertMillisecondsToHHMMSS(secondPos.time, this.firstPos.time) }));
		double blocksPerSecond = distance / (secondPos.time - this.firstPos.time) * 1000.0D;
		double kmPerHour = blocksPerSecond * 3.6D;
		this.lastDistance = distance;
		this.lastSpeed = blocksPerSecond;
		ChatUtils.print("Speed: %.2f b/s; %.2f km/h".formatted(new Object[] { Double.valueOf(blocksPerSecond), Double.valueOf(kmPerHour) }));
	}

	public double distanceXZ(PositionHolder pos1, PositionHolder pos2) {
		return Math.sqrt(Math.pow((pos1.pos.getX() - pos2.pos.getX()), 2.0D) + Math.pow((pos1.pos.getZ() - pos2.pos.getZ()), 2.0D));
	}

	public void estimate(int distance) {
		if (this.lastSpeed == 0.0D) {
			ChatUtils.print("No Measures");
			return;
		}
		double estimatedTime = distance / this.lastSpeed;
		ChatUtils.print("Esitmated Time: %s".formatted(new Object[] { convertMillisecondsToHHMMSS((int)(estimatedTime * 1000.0D), 0L) }));
	}

	private String convertMillisecondsToHHMMSS(long time1, long time2) {
		long milliseconds = time1 - time2;
		long seconds = milliseconds / 1000L;
		long hours = seconds / 3600L;
		long minutes = seconds % 3600L / 60L;
		long remainingSeconds = seconds % 60L;
		return String.format("%02dh %02dm %02ds", new Object[] { Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(remainingSeconds) });
	}

	static class PositionHolder {
		public BlockPos pos;

		public long time;

		public PositionHolder(BlockPos pos, long time) {
			this.pos = pos;
			this.time = time;
		}
	}
}
