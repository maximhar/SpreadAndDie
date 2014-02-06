package spreadAndDie.mechanics;

/*
 A toroidal grid - the left edge is connected to the right edge,
 and the top edge is connected to bottom edge.
 */
public class ToroidalGrid extends RectangularGrid {
	public ToroidalGrid(int rows, int cols, int regions) {
		super(rows, cols, regions);
	}

	@Override
	protected Grid createCells() {
		super.createCells();
		connectEdges();
		return this;
	}

	private void connectEdges() {
		// upper edge
		for (int col = leftmostColumn(); col <= rightmostColumn(); col++)
			cellAt(col, topmostRow()).setTop(cellAt(col, bottommostRow()));
		// lower edge
		for (int col = leftmostColumn(); col <= rightmostColumn(); col++)
			cellAt(col, bottommostRow()).setBottom(cellAt(col, topmostRow()));
		// left edge
		for (int row = topmostRow(); row <= bottommostRow(); row++)
			cellAt(leftmostColumn(), row).setLeft(
					cellAt(rightmostColumn(), row));
		// right edge
		for (int row = topmostRow(); row <= bottommostRow(); row++)
			cellAt(rightmostColumn(), row).setRight(
					cellAt(leftmostColumn(), row));
	}
}
