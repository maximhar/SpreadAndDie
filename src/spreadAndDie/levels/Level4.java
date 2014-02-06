package spreadAndDie.levels;

import java.util.Random;

import spreadAndDie.io.LevelPrinter;
import spreadAndDie.mechanics.Grid;

/*
 * Uses Level2's player movement behaviour;
 * Develops a cure and makes the disease resistant after a random number of turns
 */
public class Level4 extends Level2 {
	private final int maxCureThreshold = 10;
	private int cureThreshold;
	private final Random randomizer;
	private int cureProgress = 0;
	private boolean cureInEffect = false;

	public Level4(Grid grid, int lives, int scoreThreshold) {
		super(grid, lives, scoreThreshold);
		this.randomizer = new Random();
	}

	@Override
	public String getName() {
		return "Level 4";
	}

	@Override
	protected void doTick() {
		if (this.cureProgress <= 0) {
			this.cureInEffect = false;
			this.cureThreshold = this.randomizer.nextInt(this.maxCureThreshold) + 1;
		}

		if (this.cureProgress >= this.cureThreshold) {
			this.cureInEffect = true;
		}

		if (this.cureInEffect) {
			this.cureProgress--;
		} else
			this.cureProgress++;
		if (!cureInEffect) {
			this.grid.tick();
		}
		listener.onTick(this);
	}

	@Override
	public void print(LevelPrinter printer) {
		printer.print(this);
	}

	@Override
	protected void resetState() {
		super.resetState();
		this.cureProgress = 0;
		this.cureThreshold = this.randomizer.nextInt(this.maxCureThreshold) + 1;
		this.cureInEffect = false;
	}

	public boolean isCureInEffect() {
		return cureInEffect;
	}

	public int getCureProgress() {
		return cureProgress;
	}

	public int getCureThreshold() {
		return cureThreshold;
	}
}
