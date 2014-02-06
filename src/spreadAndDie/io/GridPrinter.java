package spreadAndDie.io;

import java.io.PrintWriter;

import spreadAndDie.mechanics.Grid;
import spreadAndDie.mechanics.Player;

/*
 Provides printing functionality for Grids.
 */
public class GridPrinter {
	private final CellPrinter cellPrinter;
	private final PrintWriter writer;

	public GridPrinter(CellPrinter cellPrinter, PrintWriter writer) {
		this.cellPrinter = cellPrinter;
		this.writer = writer;
	}

	public void print(Grid grid, Player player) {
		for (int i = 0; i < grid.getColumns() + 2; i++)
			this.writer.print('*');
		this.writer.println();
		for (int row = grid.topmostRow(); row <= grid.bottommostRow(); row++) {
			this.writer.print('*');
			for (int col = grid.leftmostColumn(); col <= grid.rightmostColumn(); col++) {
				this.cellPrinter.printCell(grid.cellAt(col, row), player,
						this.writer);
			}
			this.writer.print('*');
			this.writer.println();
		}
		for (int i = 0; i < grid.getColumns() + 2; i++)
			this.writer.print('*');
		this.writer.println();
		this.writer.flush();
	}

	public CellPrinter getCellPrinter() {
		return cellPrinter;
	}

	public PrintWriter getWriter() {
		return writer;
	}

}
