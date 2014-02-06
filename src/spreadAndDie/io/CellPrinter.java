package spreadAndDie.io;

import java.io.PrintWriter;

import spreadAndDie.mechanics.Cell;
import spreadAndDie.mechanics.Player;

/*
 Provides printing functionality for separate grid Cells.
 Usually used in conjunction with GridPrinter.
 */
public class CellPrinter {
	private final char infected;
	private final char diseased;
	private final char player;

	public CellPrinter(char infected, char diseased, char player) {
		this.infected = infected;
		this.diseased = diseased;
		this.player = player;
	}

	public void printCell(Cell cell, Player player, PrintWriter writer) {
		if (player.currentCell() == cell)
			writer.print(this.player);
		else if (cell.isDiseased())
			writer.print(this.diseased);
		else if (cell.isInfected())
			writer.print(this.infected);
		else
			writer.print(cell.getRegion());
	}
}
