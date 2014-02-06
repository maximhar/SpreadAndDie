package spreadAndDie.tests;

import java.io.PrintWriter;

import spreadAndDie.io.CellPrinter;
import spreadAndDie.io.GridPrinter;
import spreadAndDie.io.LevelPrinter;
import spreadAndDie.levels.Level;
import spreadAndDie.levels.Level1;
import spreadAndDie.levels.LevelEventListener;
import spreadAndDie.levels.LevelException;
import spreadAndDie.mechanics.RectangularGrid;

public class LevelTest implements Test {
	@Override
	public void run() {
		System.out.println("Running level tests");
		System.out
				.println("Creating a Level1 with a 12 x 12 4-region grid, player at 4, 4, disease at 11, 11, 3 lives, pass threshold: 10");
		Level level = new Level1(new RectangularGrid(12, 12, 4), 3, 20);
		level.placePlayer(4, 4);
		level.getGrid().cellAt(11, 11).makeDiseased();
		System.out
				.println("Creating a printer with disease = 'D', infection = 'I', player = 'P'");
		final LevelPrinter printer = new LevelPrinter(new GridPrinter(
				new CellPrinter('I', 'D', 'P'), new PrintWriter(System.out)));
		System.out.println("Attaching event handler to level");
		level.attachListener(new LevelEventListener() {
			@Override
			public void onDeath(Level sender) {
				System.out.println("Player died, steps, lives: "
						+ sender.getSteps() + ", " + sender.getLives());
			}

			@Override
			public void onEnd(Level sender) {
				System.out.println("Level ended, steps: " + sender.getSteps());
			}

			@Override
			public void onTick(Level sender) {
				System.out.println("Printing level...");
				sender.print(printer);
				if (sender.getSteps() > 100) {
					System.out
							.println("Level ran for over 100 steps, aborting");
					sender.abort();
				}
			}

			@Override
			public void onPlacementRequired(Level sender) {
				System.out
						.println("Level requires reinitialization of player and grid");
				sender.getGrid().clearDisease();
				sender.placePlayer(4, 4);
				sender.getGrid().cellAt(11, 11).makeDiseased();
			}

		});
		System.out.println("Running level");
		try {
			level.run();
		} catch (LevelException e) {
			System.out.println("Exception catched during level.run(): "
					+ e.toString());
		}
		System.out.println("Level tests finished");
	}

}
