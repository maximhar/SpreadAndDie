package spreadAndDie.mechanics;

/*
 Represents a default rectangular grid - border cells on each of the four
 sides of the grid.
 */
public class RectangularGrid extends Grid {
	public RectangularGrid(int rows, int cols, int regions) {
		super(rows, cols, regions);
	}

	@Override
	protected Grid createCells() {
		for (int col = leftmostColumn(); col <= rightmostColumn(); col++)
			for (int row = topmostRow(); row <= bottommostRow(); row++) {
				Cell topCell = (row == topmostRow() ? Cell.border : cellAt(col,
						row - 1));
				Cell leftCell = (col == leftmostColumn() ? Cell.border
						: cellAt(col - 1, row));
				Cell cell = Cell.createWithNeighbours(topCell, leftCell)
						.setRegion(getRandomRegion());
				placeCell(cell, col, row);
			}
		return this;
	}
}
