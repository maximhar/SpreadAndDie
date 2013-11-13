import java.io.PrintWriter;

public class GameWindow implements View {
    private String windowTitle = "Game Window";
    private Menu mainMenu;

    public GameWindow(Menu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void show() {
        System.out.println(windowTitle);
        PrintWriter writer = new PrintWriter(System.out);
        CellPrinter cellPrinter = new CellPrinter('I', 'D', 'X');
        GridPrinter gridPrinter = new GridPrinter(cellPrinter, writer);
        CellGrid grid = new CellGrid(12, 12, 4);
        gridPrinter.print(grid);
        try{
            grid.getPlayer().moveDown();
        } catch (PlayerOutOfBoundsException e) { e.printStackTrace(); }
        for(int i = 0; i < 10; i++)
        {
            grid.tick();
            gridPrinter.print(grid);
            try{
                Thread.sleep(500);
            } catch (InterruptedException ex){}
        }
    }

    @Override
    public String getName() {
        return windowTitle;
    }
}
