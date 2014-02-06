package spreadAndDie.levels;

import spreadAndDie.io.LevelPrinter;
import spreadAndDie.mechanics.Grid;
import spreadAndDie.mechanics.Player;
import spreadAndDie.mechanics.PlayerOutOfBoundsException;
import spreadAndDie.mechanics.RectangularGrid;
import spreadAndDie.mechanics.ToroidalGrid;

/*
 Provides common behaviour for all levels.
 Maintains a Grid and a Player.
 Maintains a count of the steps taken so far.
 Provides a way for an interested class to 'listen'
 for end of tick/end of level events
 */
public abstract class Level {
	protected Grid grid;
	protected Player player;
	protected boolean levelFinished;
	protected int stepsPerformed;
	protected int lives;
	private int scoreThreshold;
	protected LevelEventListener listener;

	//Static factory level creation methods
	public static Level makeLevel4(int lives, int minSteps, int columns, int rows, int regions) {
		Grid level4Grid = new ToroidalGrid(columns, rows,
				regions);
		Level level4 = new Level4(level4Grid, lives, minSteps);
		return level4;
	}
	
	public static Level makeLevel3(int lives, int minSteps, int columns, int rows, int regions) {
		Grid level3Grid = new RectangularGrid(columns, rows,
				regions);
		Level level3 = new Level3(level3Grid, lives, minSteps);
		return level3;
	}

	public static Level makeLevel2(int lives, int minSteps, int columns, int rows, int regions) {
		Grid level2Grid = new RectangularGrid(columns, rows,
				regions);
		Level level2 = new Level2(level2Grid, lives, minSteps);
		return level2;
	}

	public static Level makeLevel1(int lives, int minSteps, int columns, int rows, int regions) {
		Grid level1Grid = new RectangularGrid(columns, rows,
				regions);
		Level level1 = new Level1(level1Grid, lives, minSteps);
		return level1;
	}
	
	public Level(Grid grid, int lives, int scoreThreshold) {
		this.grid = grid;
		this.lives = lives;
		this.scoreThreshold = scoreThreshold;
	}
	
	public abstract String getName();

	public Level attachListener(LevelEventListener listener) {
		this.listener = listener;
		return this;
	}

	public Level placePlayer(int column, int row) {
		if (!this.grid.inBounds(column, row)) {
			throw new IllegalArgumentException();
		}
		this.player = new Player(this.grid.cellAt(column, row));
		return this;
	}

	public Grid getGrid() {
		return this.grid;
	}

	public Level setGrid(Grid grid) {
		this.grid = grid;
		return this;
	}

	public Player getPlayer() {
		return this.player;
	}

	public Level setPlayer(Player player) {
		this.player = player;
		return this;
	}

	public int getSteps() {
		return this.stepsPerformed;
	}

	public void run() throws LevelException {
		try { // player won't die if he is on the disease during turn 1 which is
				// good
			while (!this.levelFinished) {
				this.stepsPerformed++;
				movePlayer();
				doTick();
				killPlayerIfDiseased();
			}
		} catch (Throwable ex) {
			throw new LevelException(ex);
		}

	}

	// ugly visitor pattern, sadly necessary
	public void print(LevelPrinter printer) {
		printer.print(this);
	}

	public int getLives() {
		return this.lives;
	}

	public int getScoreThreshold() {
		return scoreThreshold;
	}

	public void abort() {
		this.triggerLevelEnd();
	}

	protected void doTick() {
		this.grid.tick();
		listener.onTick(this);
	}

	protected abstract void movePlayer() throws PlayerOutOfBoundsException;

	protected void onPlayerKilled() {
		lives--;
		this.listener.onDeath(this);
		if (lives <= 0 || this.stepsPerformed >= this.scoreThreshold) {
			this.triggerLevelEnd();
		} else {
			this.listener.onPlacementRequired(this);
		}
		resetState();
	}

	protected void resetState() {
		this.stepsPerformed = 0;
	}

	protected void triggerLevelEnd() {
		this.levelFinished = true;
		this.listener.onEnd(this);
	}

	private void killPlayerIfDiseased() {
		if (this.player.currentCell().isDiseased()) {
			this.player.kill();
			onPlayerKilled();
		}
	}
}
