package spreadAndDie.levels;

import java.util.Random;

import spreadAndDie.mechanics.CellRelator;
import spreadAndDie.mechanics.Grid;
import spreadAndDie.mechanics.PlayerOutOfBoundsException;
import spreadAndDie.mechanics.PlayerRelator;

/*
 The player moves randomly - with the exception that
 when it encounters a border, it moves to the cell opposite of the border
 */
public class Level1 extends Level {
	private Random randomizer;

	public Level1(Grid grid, int lives, int scoreThreshold) {
		super(grid, lives, scoreThreshold);
		this.randomizer = new Random();
	}

	@Override
	public String getName() {
		return "Level 1";
	}

	@Override
	protected void movePlayer() throws PlayerOutOfBoundsException {
		PlayerRelator relator = new PlayerRelator(this.player);
		CellRelator randomCell = relator
				.getCellRelator(this.randomizer.nextInt(relator.cellCount()));
		if (randomCell.getCell().isBorder()) {
			relator.movePlayerTo(randomCell.getOpposite());
		} else {
			relator.movePlayerTo(randomCell);
		}
	}
}
