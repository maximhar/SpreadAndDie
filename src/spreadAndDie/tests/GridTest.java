package spreadAndDie.tests;

import java.io.PrintWriter;

import spreadAndDie.io.CellPrinter;
import spreadAndDie.io.GridPrinter;
import spreadAndDie.mechanics.Cell;
import spreadAndDie.mechanics.Grid;
import spreadAndDie.mechanics.Player;
import spreadAndDie.mechanics.ToroidalGrid;

public class GridTest implements Test {
	@Override
	public void run() {
		System.out.println("Running grid tests");
		System.out.println("Creating a 12 x 12 grid with 4 regions");
		Grid grid = new ToroidalGrid(12, 12, 4);
		System.out.println("Checking disease spread");
		System.out.println("Diseasing cell 2, 2");
		grid.cellAt(2, 2).makeDiseased();
		System.out.println("Cell 2, 2 diseased? "
				+ grid.cellAt(2, 2).isDiseased());
		System.out.println("Ticking grid");
		grid.tick();
		System.out.println("Cell 2, 3 infected? "
				+ grid.cellAt(2, 3).isInfected());
		System.out.println("Ticking grid");
		grid.tick();
		System.out.println("Cell 2, 3 diseased? "
				+ grid.cellAt(2, 3).isDiseased());
		System.out.println("Checking cell linkage");
		Cell cell = grid.cellAt(5, 5);
		System.out.println("Is Cell 4, 5 to the left of Cell 5, 5? "
				+ (cell.getLeft() == grid.cellAt(4, 5)));
		System.out.println("Is Cell 6, 5 to the right of Cell 5, 5? "
				+ (cell.getRight() == grid.cellAt(6, 5)));
		System.out.println("Is Cell 5, 4 to above Cell 5, 5? "
				+ (cell.getTop() == grid.cellAt(5, 4)));
		System.out.println("Is Cell 5, 6 to under Cell 5, 5? "
				+ (cell.getBottom() == grid.cellAt(5, 6)));
		System.out.println("Checking printing");
		System.out.println("Printing grid with player at Cell 9, 9");
		GridPrinter gp = new GridPrinter(new CellPrinter('I', 'D', 'P'),
				new PrintWriter(System.out));
		gp.print(grid, new Player(grid.cellAt(9, 9)));
		System.out.println("Grid tests finished");
	}
}
