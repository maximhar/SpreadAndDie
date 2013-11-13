import java.io.OutputStream;
import java.io.PrintWriter;

public class GridPrinter {
    public final CellPrinter cellPrinter;
    public final PrintWriter writer;
    public GridPrinter(CellPrinter cellPrinter, PrintWriter writer){
        this.cellPrinter = cellPrinter;
        this.writer = writer;
    }
    public void print(CellGrid grid){
        for(int i = 0; i < grid.getColumns() + 2; i++)
            this.writer.print('*');
        this.writer.println();
        for(int col = 0; col < grid.getRows(); col++) {
            this.writer.print('*');
            for(int row = 0; row < grid.getColumns(); row++){
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
