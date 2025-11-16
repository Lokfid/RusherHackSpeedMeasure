package org.lokfid;

import org.rusherhack.client.api.RusherHackAPI;
import org.rusherhack.client.api.plugin.Plugin;

public class SpeedMeasurePlugin extends Plugin {
	public void onLoad() {
		getLogger().info("SpeedMeasurePlugin loaded!");
		SpeedMeasureModule speedMeasureModule = new SpeedMeasureModule();
		RusherHackAPI.getModuleManager().registerFeature(speedMeasureModule);
		SpeedMeasureCommand speedMeasureCommand = new SpeedMeasureCommand();
		RusherHackAPI.getCommandManager().registerFeature(speedMeasureCommand);
	}

	public void onUnload() {
		getLogger().info("SpeedMeasurePlugin unloaded!");
	}
}
