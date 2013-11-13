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

    public void print(OutputStream out, CellPrinter cellPrinter){
        PrintWriter writer = new PrintWriter(out);
        char[] grid = new char[rows * columns];
        for(int i = 0; i < this.columns + 2; i++)
            writer.print('*');
        writer.println();
        for(int col = 0; col < this.rows; col++) {
            writer.print('*');
            for(int row = 0; row < this.columns; row++){
                cellPrinter.printCell(cells[calculateCellIndex(col, row)], player, writer);
            }
            writer.print('*');
            writer.println();
        }
        for(int i = 0; i < this.columns + 2; i++)
            writer.print('*');
        writer.println();
        writer.flush();
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

    private void createCells() {
        this.cells = new Cell[this.rows * this.columns];
        //go by columns first and rows second
        for(int col = 0; col < this.rows; col++)
        for(int row = 0; row < this.columns; row++){
            Cell topCell = (row == 0 ? Cell.border : cells[calculateCellIndex(col, row - 1)]);
            Cell leftCell = (col == 0 ? Cell.border : cells[calculateCellIndex(col - 1, row)]);
            Cell c = createCellWithNeighbours(topCell, leftCell);
            c.setRegion(getRandomRegion());
            cells[calculateCellIndex(col, row)] = c;
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

    private int calculateCellIndex(int col, int row) {
        return calculateIndex(col, row, this.rows);
    }

    private int calculateIndex(int col, int row, int colSize){
        return col * colSize + row;
    }

    private int getRandomRegion() {
        return regionRandomizer.nextInt(this.regions);
    }
}
