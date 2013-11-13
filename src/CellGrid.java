import java.io.*;
import java.util.Random;

public class CellGrid {
    private final Random regionRandomizer;
    private final int rows;
    private final int columns;
    private final int regions;
    private Cell[] cells;
    private Player player;

    public CellGrid(int rows, int columns, int regions){
        if(regions < 1 || regions >= 10) throw new IllegalArgumentException("regions");
        this.rows = rows;
        this.columns = columns;
        this.regions = regions;
        this.regionRandomizer = new Random();
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

    private void createCells() {
        this.cells = new Cell[this.rows * this.columns];
        //go by columns first and rows second
        for(int col = 0; col < this.rows; col++)
        for(int row = 0; row < this.columns; row++){
            Cell topCell = (row == 0 ? Cell.border : cellAt(col, row - 1));
            Cell leftCell = (col == 0 ? Cell.border : cellAt(col - 1, row));
            Cell c = createCellWithNeighbours(topCell, leftCell);
            c.setRegion(getRandomRegion());
            cells[calculateIndex(col, row, this.rows)] = c;
        }
        cells[15].makeDiseased();
    }

    private Cell createCellWithNeighbours(Cell topCell, Cell leftCell) {
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

    public int calculateIndex(int col, int row, int colSize){
        return col * colSize + row;
    }

    private int getRandomRegion() {
        return regionRandomizer.nextInt(this.regions);
    }
}
