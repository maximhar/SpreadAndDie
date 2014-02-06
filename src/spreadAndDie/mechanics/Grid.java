package spreadAndDie.mechanics;

import java.util.Random;

/*
 Represents a Grid of cells.
 Creates its cells at construction and
 connects the appropriately, assigning regions randomly.
 Maintains an array of all its cells.
 Can 'tick' - advance the state of the grid
 by calling tick on each cell.
 */
public abstract class Grid {
	protected final Random regionRandomizer;
	protected final int rows;
	protected final int columns;
	protected final int regions;
	protected Cell[] cells;

	protected Grid(int rows, int columns, int regions) {
		if (regions < 1 || regions >= 10)
			throw new IllegalArgumentException("regions");
		this.rows = rows;
		this.columns = columns;
		this.regions = regions;
		this.regionRandomizer = new Random();
		this.cells = new Cell[this.rows * this.columns];
		createCells();
	}

	public Grid tick() {
		for (Cell cell : cells) {
			cell.beginTick();
		}
		for (Cell cell : cells) {
			cell.endTick();
		}
		return this;
	}

	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}

	public int getRegions() {
		return this.regions;
	}

	public Cell cellAt(int column, int row) {
		if (!inBounds(column, row)) {
			throw new IllegalArgumentException();
		}
		return cells[calculateIndex(column, row, this.rows)];
	}

	public int topmostRow() {
		return 0;
	}

	public int leftmostColumn() {
		return 0;
	}

	public int bottommostRow() {
		return this.rows - 1;
	}

	public int rightmostColumn() {
		return this.columns - 1;
	}

	public boolean inBounds(int column, int row) {
		return column >= leftmostColumn() && column <= rightmostColumn()
				&& row >= topmostRow() && row <= bottommostRow();
	}

	public void clearDisease() {
		for (Cell cell : this.cells) {
			cell.makeHealthy();
		}
	}

	protected Grid placeCell(Cell c, int column, int row) {
		if (!inBounds(column, row)) {
			throw new IllegalArgumentException();
		}
		cells[calculateIndex(column, row, this.rows)] = c;
		return this;
	}

	// this shouldn't really be here, since Grid isn't supposed to make any
	// assumptions
	// about the geometry of the world... however, the alternatives are either
	// duplication
	// of code in the current subclasses, or an extra abstract class in the
	// middle
	protected abstract Grid createCells();

	protected int calculateIndex(int col, int row, int colSize) {
		return col * colSize + row;
	}

	protected int getRandomRegion() {
		return regionRandomizer.nextInt(this.regions);
	}
}
