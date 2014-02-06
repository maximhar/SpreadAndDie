package spreadAndDie.mechanics;

import java.util.Arrays;
import java.util.Iterator;

/*
 Provides awareness of the player's neighbouring cells.
 In the context of the relator, cells around the player
 know their opposite and adjacent cells, compared to the player.
 Uses the CellRelator class to give separate cells this awareness functionality.
 */
public class PlayerRelator implements Iterable<CellRelator> {
	private CellRelator[] cells;
	private Player player;

	public PlayerRelator(Player player) {
		this.cells = new CellRelator[4];
		this.player = player;
		createCellRelators();
	}

	public int cellCount() {
		return cells.length;
	}

	public CellRelator getCellRelator(int index) {
		if (index < 0 || index >= cells.length)
			throw new IllegalArgumentException("index");
		return cells[index];
	}

	public CellRelator[] getRelators() {
		return cells.clone();
	}

	public Player movePlayerTo(CellRelator dest)
			throws PlayerOutOfBoundsException {
		if (dest.getCell() == player.currentCell().getLeft()) {
			player.moveLeft();
		} else if (dest.getCell() == player.currentCell().getTop()) {
			player.moveUp();
		} else if (dest.getCell() == player.currentCell().getRight()) {
			player.moveRight();
		} else if (dest.getCell() == player.currentCell().getBottom()) {
			player.moveDown();
		}
		return player;
	}

	private void createCellRelators() {
		CellRelator left = new CellRelator(player.currentCell().getLeft());
		CellRelator top = new CellRelator(player.currentCell().getTop());
		CellRelator right = new CellRelator(player.currentCell().getRight());
		CellRelator bottom = new CellRelator(player.currentCell().getBottom());

		left.setOpposite(right);
		right.setOpposite(left);
		top.setOpposite(bottom);
		bottom.setOpposite(top);

		left.setAdjacent1(top);
		left.setAdjacent2(bottom);
		top.setAdjacent1(left);
		top.setAdjacent2(right);
		right.setAdjacent1(top);
		right.setAdjacent2(bottom);
		bottom.setAdjacent1(right);
		bottom.setAdjacent2(left);

		cells[0] = left;
		cells[1] = top;
		cells[2] = right;
		cells[3] = bottom;
	}
	
	@Override
	public Iterator<CellRelator> iterator() {
		//every time someone gets an iterator a puppy dies but
		//why doesn't java implement iterable for arrays...
		return Arrays.asList(this.cells).iterator();
	}
}
