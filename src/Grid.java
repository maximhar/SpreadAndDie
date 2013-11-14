import java.util.Random;

public abstract class Grid {
    protected final Random regionRandomizer;
    protected final int rows;
    protected final int columns;
    protected final int regions;
    protected Cell[] cells;

    protected Grid(int rows, int columns, int regions){
        if(regions < 1 || regions >= 10) throw new IllegalArgumentException("regions");
        this.rows = rows;
        this.columns = columns;
        this.regions = regions;
        this.regionRandomizer = new Random();
        this.cells = new Cell[this.rows * this.columns];
        createCells();
    }

    public Grid tick(){
        for(Cell cell : cells){
            cell.beginTick();
        }
        for(Cell cell : cells){
            cell.endTick();
        }
        return this;
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

    public Cell cellAt(int column, int row){
        if(!inBounds(column, row)){
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

    public int bottommostRow(){
        return this.rows - 1;
    }

    public int rightmostColumn() {
        return this.columns - 1;
    }

    public boolean inBounds(int column, int row) {
        return column >= leftmostColumn() && column <= rightmostColumn() && row >= topmostRow() && row <= bottommostRow();
    }

    protected Grid placeCell(Cell c, int column, int row){
        if(!inBounds(column, row)){
            throw new IllegalArgumentException();
        }
        cells[calculateIndex(column, row, this.rows)] = c;
        return this;
    }

    protected Grid createCells(){
        for(int col = leftmostColumn(); col <= rightmostColumn(); col++)
            for(int row = topmostRow(); row <= bottommostRow(); row++){
                Cell topCell = (row == topmostRow() ? Cell.border : cellAt(col, row - 1));
                Cell leftCell = (col == leftmostColumn() ? Cell.border : cellAt(col - 1, row));
                Cell cell = Cell.createWithNeighbours(topCell, leftCell).setRegion(getRandomRegion());
                placeCell(cell, col, row);
            }
        return this;
    }

    protected int calculateIndex(int col, int row, int colSize){
        return col * colSize + row;
    }

    protected int getRandomRegion() {
        return regionRandomizer.nextInt(this.regions);
    }
}
