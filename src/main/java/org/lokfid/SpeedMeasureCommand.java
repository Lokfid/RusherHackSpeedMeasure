package org.lokfid;

import org.rusherhack.client.api.RusherHackAPI;
import org.rusherhack.client.api.feature.command.Command;
import org.rusherhack.client.api.feature.module.IModule;
import org.rusherhack.core.command.annotations.CommandExecutor;
import org.rusherhack.core.command.annotations.CommandExecutor.Argument;

public class SpeedMeasureCommand extends Command {
	public SpeedMeasureCommand() {
		super("Measure", "Measure");
	}

	@CommandExecutor
	private String example() {
		return "Hello World!";
	}

	@CommandExecutor(subCommand = {"start"})
	private String startMeasure() {
		RusherHackAPI.getModuleManager().getFeature("SpeedMeasure").ifPresent(speedMeasureModule -> {
			if (speedMeasureModule instanceof SpeedMeasureModule) {
				SpeedMeasureModule speedMeasureModule1 = (SpeedMeasureModule)speedMeasureModule;
				speedMeasureModule1.measureStart();
			}
		});
		return "Started measuring";
	}

	@CommandExecutor(subCommand = {"end"})
	private void endMeasure() {
		RusherHackAPI.getModuleManager().getFeature("SpeedMeasure").ifPresent(speedMeasureModule -> {
			if (speedMeasureModule instanceof SpeedMeasureModule) {
				SpeedMeasureModule speedMeasureModule1 = (SpeedMeasureModule)speedMeasureModule;
				speedMeasureModule1.measureEnd();
			}
		});
	}

	@CommandExecutor(subCommand = {"estimate"})
	@Argument({"int"})
	private void estimate(int distance) {
		RusherHackAPI.getModuleManager().getFeature("SpeedMeasure").ifPresent(speedMeasureModule -> {
			if (speedMeasureModule instanceof SpeedMeasureModule) {
				SpeedMeasureModule speedMeasureModule1 = (SpeedMeasureModule)speedMeasureModule;
				speedMeasureModule1.estimate(distance);
			}
		});
	}
}
