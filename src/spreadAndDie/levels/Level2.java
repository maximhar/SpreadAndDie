package spreadAndDie.levels;

import spreadAndDie.mechanics.CellRelator;
import spreadAndDie.mechanics.Grid;
import spreadAndDie.mechanics.PlayerOutOfBoundsException;
import spreadAndDie.mechanics.PlayerRelator;

/*
 Rudimentary level AI -
 the player sits still until the disease gets closer that 2 cells from him
 once the player is less than 2 cells away from the disease, it
 moves to a cell opposite of the disease (unless that cell is diseased or infected)
 */
public class Level2 extends Level {
	public Level2(Grid grid, int lives, int scoreThreshold) {
		super(grid, lives, scoreThreshold);
	}

	@Override
	public String getName() {
		return "Level 2";
	}

	@Override
	protected void movePlayer() throws PlayerOutOfBoundsException {
		PlayerRelator relator = new PlayerRelator(this.player);
		// iterate through the player's neighbouring cells
		for (CellRelator cellRelator : relator) {
			// if the cell is diseased, and it's opposite cell is neither
			// diseased
			// nor a border, move the player to the cell's opposite cell and end
			// the loop
			if (!cellRelator.getCell().isBorder()
					&& cellRelator.getCell().isNearDisease()
					&& !cellRelator.getOpposite().getCell().isDiseased()
					&& !cellRelator.getOpposite().getCell().isBorder()) {
				relator.movePlayerTo(cellRelator.getOpposite());
				break;
			}
		}
	}
}
