package spreadAndDie.io;

import spreadAndDie.levels.Level;
import spreadAndDie.levels.Level4;

public class LevelPrinter {
	private final GridPrinter gridPrinter;

	public LevelPrinter(GridPrinter gridPrinter) {
		this.gridPrinter = gridPrinter;
	}

	// print a regular level
	public void print(Level level) {
		this.gridPrinter.print(level.getGrid(), level.getPlayer());
		this.gridPrinter.getWriter().println(
				"Steps so far: " + level.getSteps());
		this.gridPrinter.getWriter().println("Lives: " + level.getLives());
		this.gridPrinter.getWriter().flush();
	}

	// print level 4 which has additional state that needs to be displayed
	public void print(Level4 level) {
		if (level.isCureInEffect()) {
			gridPrinter.getWriter().println("Cure is in effect... for now.");
		} else {
			gridPrinter.getWriter().println(
					"Disease is spreading! Turns until cure is developed: "
							+ (level.getCureThreshold() - level
									.getCureProgress()));
		}
		this.print((Level) level);
	}
}
