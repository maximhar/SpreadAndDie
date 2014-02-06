package spreadAndDie.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

import spreadAndDie.io.CellPrinter;
import spreadAndDie.io.GridPrinter;
import spreadAndDie.io.Highscore;
import spreadAndDie.io.LevelPrinter;
import spreadAndDie.levels.LevelEventListener;
import spreadAndDie.levels.Level;
import spreadAndDie.levels.Level1;
import spreadAndDie.levels.Level2;
import spreadAndDie.levels.Level3;
import spreadAndDie.levels.Level4;
import spreadAndDie.levels.LevelException;
import spreadAndDie.mechanics.Grid;
import spreadAndDie.mechanics.RectangularGrid;
import spreadAndDie.mechanics.ToroidalGrid;

/*
 The main game window.
 Is ultimately responsible for printing the level state,
 navigation between levels and level construction
 */
public class GameWindow implements View {
	private String windowTitle = "Game Window";
	private Menu mainMenu;
	private final int minSteps = 20;
	private final Scanner scanner = new Scanner(System.in);
	private final PrintWriter writer = new PrintWriter(System.out);
	private final LevelPrinter levelPrinter;
	private final Random randomizer;
	private final int gridColumns = 12;
	private final int gridRows = 12;
	private int lives;
	private String username;
	private int accumulatedScore;
	private Dictionary<Level, Level> levelSequencer;
	private int regions;

	public GameWindow(Menu mainMenu) {
		this.mainMenu = mainMenu;
		this.levelPrinter = new LevelPrinter(new GridPrinter(new CellPrinter(
				'I', 'D', 'P'), writer));
		this.randomizer = new Random();
	}

	@Override
	public void show() {
		this.accumulatedScore = 0;
		this.levelSequencer = new Hashtable<Level, Level>();
		System.out.println(windowTitle);
		System.out.print("What is your name? ");
		this.username = this.scanner.next();
		do {
			this.lives = askForIntInput("How many lives per level would you like? ");
		} while (this.lives <= 0);
		do {
			this.regions = askForIntInput("How many regions in levels (2 to 4)? ");
		} while (!(this.regions >= 2 && this.regions <= 4));
		Level level1 = Level.makeLevel1(lives, minSteps, gridColumns, gridRows, regions).attachListener(getDefaultListener());
		Level level2 = Level.makeLevel2(lives, minSteps, gridColumns, gridRows, regions).attachListener(getDefaultListener());
		Level level3 = Level.makeLevel3(lives, minSteps, gridColumns, gridRows, regions).attachListener(getDefaultListener());
		Level level4 = Level.makeLevel4(lives, minSteps, gridColumns, gridRows, regions).attachListener(getDefaultListener());
		levelSequencer.put(level1, level2);
		levelSequencer.put(level2, level3);
		levelSequencer.put(level3, level4);
		runLevel(level1);
		mainMenu.show();
	}

	private int askForIntInput(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				return this.scanner.nextInt();
			} catch (Exception ex) {
				System.out.println("Failed to parse input");
				this.scanner.next();
			}
		}
	}

	private void runLevel(Level level) {
		System.out.println();
		System.out.println(level.getName() + " starting!");
		try {
			initializeLevel(level);
			level.run();
		} catch (LevelException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return windowTitle;
	}

	private LevelEventListener getDefaultListener() {
		LevelEventListener eventListener = new LevelEventListener() {
			@Override
			public void onEnd(Level sender) {
				printEndLevelMessage(sender);
				runNextLevelOrWin(sender);
			}

			@Override
			public void onTick(Level sender) {
				printLevel(sender);
			}

			@Override
			public void onDeath(Level sender) {
				System.out.println("You died.");
			}

			@Override
			public void onPlacementRequired(Level sender) {
				sender.getGrid().clearDisease();
				initializeLevel(sender);
			}
		};
		return eventListener;
	}

	private void initializeLevel(Level level) {
		int col = 0, row = 0;
		do {
			System.out
					.println("Please enter the coordinates of the disease (0-based): ");
			col = askForIntInput("column: ");
			row = askForIntInput("row: ");
		} while (!level.getGrid().inBounds(col, row));
		level.getGrid().cellAt(col, row).makeDiseased();
		level.placePlayer(randomizer.nextInt(level.getGrid().getColumns()),
				randomizer.nextInt(level.getGrid().getRows()));
	}

	private void printEndLevelMessage(Level level) {
		System.out.println("Level over!");
		System.out.println("Score: " + level.getSteps());
	}

	private void printLevel(Level level) {
		level.print(this.levelPrinter);
		System.out.println("Press 'n' please.");
		if (!this.scanner.next().equals("n")) {
			level.abort();
		}
		System.out.println();
	}

	private void runNextLevelOrWin(final Level level) {
		Level nextLevel;
		this.accumulatedScore += level.getSteps();
		if ((nextLevel = levelSequencer.get(level)) != null) {
			if (level.getSteps() >= level.getScoreThreshold())
				runLevel(nextLevel);
			else
				System.out
						.println("You don't have enough points to proceed to the next level. ("
								+ level.getScoreThreshold() + " required)");
		} else {
			win();
		}
	}

	private void win() {
		try{
			Highscore highscore = Highscore.openHighscore(Highscore.defaultPath);
			highscore.addEntry(this.username, this.accumulatedScore);
			highscore.save();
		} catch(IOException ex) {
			System.out.println("Failed to save highscore.");
		}
		System.out.println("You're a winner, " + this.username
				+ ". Your score is: " + this.accumulatedScore);
	}
}
