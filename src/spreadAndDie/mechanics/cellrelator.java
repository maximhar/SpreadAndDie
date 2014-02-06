package spreadAndDie.mechanics;

public class CellRelator {
	private CellRelator opposite;
	private CellRelator adjacent1;
	private CellRelator adjacent2;
	private final Cell cell;

	public CellRelator(Cell cell) {
		this.cell = cell;
	}

	public Cell getCell() {
		return this.cell;
	}

	public CellRelator getOpposite() {
		return this.opposite;
	}

	public CellRelator getAdjacent1() {
		return this.adjacent1;
	}

	public CellRelator getAdjacent2() {
		return this.adjacent2;
	}

	public CellRelator setOpposite(CellRelator cell) {
		this.opposite = cell;
		return this;
	}

	public CellRelator setAdjacent1(CellRelator cell) {
		this.adjacent1 = cell;
		return this;
	}

	public CellRelator setAdjacent2(CellRelator cell) {
		this.adjacent2 = cell;
		return this;
	}
}