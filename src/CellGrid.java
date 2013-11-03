import java.util.ArrayList;
import java.util.List;
public class CellGrid {
    private int rows;
    private int columns;
    private int regions;
    private List<Cell> cells;

    public CellGrid(int rows, int columns, int regions){
        this.rows = rows;
        this.columns = columns;
        this.regions = regions;
        createCells();
    }

    private void createCells() {
        this.cells = new ArrayList<Cell>(this.rows * this.columns);
        //go by columns first and rows second
        for(int col = 0; col < this.rows; col++){
            for(int row = 0; row < this.columns; row++){
                Cell c = new Cell();
                c.setTop(row == 0 ? Cell.border : cells.get(col * row + row));
                c.setLeft(row == 0 ? Cell.border : cells.get((col - 1) * row + row));
                // TODO: finish cell grid construction
                cells.add(c);
            }
        }
    }
}
