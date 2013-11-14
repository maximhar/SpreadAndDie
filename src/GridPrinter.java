import java.io.PrintWriter;

public class GridPrinter {
    public final CellPrinter cellPrinter;
    public final PrintWriter writer;
    public GridPrinter(CellPrinter cellPrinter, PrintWriter writer){
        this.cellPrinter = cellPrinter;
        this.writer = writer;
    }
    public void print(Grid grid){
        for(int i = 0; i < grid.getColumns() + 2; i++)
            this.writer.print('*');
        this.writer.println();
        for(int row = grid.topmostRow(); row <= grid.bottommostRow(); row++) {
            this.writer.print('*');
            for(int col = grid.leftmostColumn(); col <= grid.rightmostColumn(); col++){
                this.cellPrinter.printCell(grid.cellAt(col, row), grid.getPlayer(), this.writer);
            }
            this.writer.print('*');
            this.writer.println();
        }
        for(int i = 0; i < grid.getColumns() + 2; i++)
            this.writer.print('*');
        this.writer.println();
        this.writer.flush();
    }

}
