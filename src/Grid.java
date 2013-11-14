import java.io.*;
import java.util.Random;

public abstract class Grid {
    protected final Random regionRandomizer;
    protected final int rows;
    protected final int columns;
    protected final int regions;
    protected Cell[] cells;
    protected Player player;

    protected Grid(int rows, int columns, int regions){
        if(regions < 1 || regions >= 10) throw new IllegalArgumentException("regions");
        this.rows = rows;
        this.columns = columns;
        this.regions = regions;
        this.regionRandomizer = new Random();
        this.cells = new Cell[this.rows * this.columns];
        createCells();
        this.player = new Player(cells[0]);
    }

    public void tick(){
        for(Cell cell : cells){
            cell.beginTick();
        }
        for(Cell cell : cells){
            cell.endTick();
        }
    }

    public Player getPlayer(){
        return this.player;
    }

    public int getRows(){
        return this.rows;
    }

    public int getColumns(){
        return this.columns;
    }

    public int getRegions(){
        return this.regions;
    }

    public Cell cellAt(int index){
        if(index < 0 && index >= this.cells.length){
            throw new IllegalArgumentException("index");
        }
        return cells[index];
    }

    public Cell cellAt(int column, int row){
        return cells[calculateIndex(column, row, this.rows)];
    }

    protected abstract void createCells();

    protected Cell createCellWithNeighbours(Cell topCell, Cell leftCell) {
        Cell c = new Cell();
        c.setTop(topCell);
        c.setLeft(leftCell);
        //ensure we are bordered from everywhere
        c.setBottom(Cell.border);
        c.setRight(Cell.border);
        if(topCell != Cell.border){
            topCell.setBottom(c);
        }
        if(leftCell != Cell.border){
            leftCell.setRight(c);
        }
        return c;
    }

    protected int calculateIndex(int col, int row, int colSize){
        return col * colSize + row;
    }

    protected int getRandomRegion() {
        return regionRandomizer.nextInt(this.regions);
    }

    protected int topmostRow() {
        return 0;
    }

    protected int leftmostColumn() {
        return 0;
    }

    protected int bottommostRow(){
       return this.rows - 1;
    }

    protected int rightmostColumn() {
        return this.columns - 1;
    }
}
