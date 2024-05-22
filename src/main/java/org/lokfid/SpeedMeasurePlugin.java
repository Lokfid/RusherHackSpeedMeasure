package org.lokfid;

import org.rusherhack.client.api.RusherHackAPI;
import org.rusherhack.client.api.feature.module.IModule;
import org.rusherhack.client.api.plugin.Plugin;
import org.rusherhack.core.command.AbstractCommand;
import org.rusherhack.core.feature.IFeature;

public class SpeedMeasurePlugin extends Plugin {
	public void onLoad() {
		getLogger().info(getName() + " loaded");
		SpeedMeasureModule speedMeasureModule = new SpeedMeasureModule();
		RusherHackAPI.getModuleManager().registerFeature((IModule) speedMeasureModule);
		SpeedMeasureCommand speedMeasureCommand = new SpeedMeasureCommand();
		RusherHackAPI.getCommandManager().registerFeature((AbstractCommand) speedMeasureCommand);
	}

	public void onUnload() {
		getLogger().info(getName() + " unloaded");
	}

	public String getName() {
		return "Speed Measurer";
	}

	public String getVersion() {
		return "v1.0";
	}

	public String getDescription() {
		return "Measures your speed over time";
	}

	public String[] getAuthors() {
		return new String[] { "Ic3Tank" };
	}
}
