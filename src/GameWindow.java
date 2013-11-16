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
        //TODO: about time to create a Level class or something
        final PrintWriter writer = new PrintWriter(System.out);
        final CellPrinter cellPrinter = new CellPrinter('I', 'D', 'X');
        final GridPrinter gridPrinter = new GridPrinter(cellPrinter, writer);
        final Grid grid = new ToroidalGrid(12, 12, 4);
        grid.cellAt(1, 1).makeDiseased();
        EventNotifier levelNotifier = new EventNotifier() {
            @Override
            public void notifyLoss(Level sender) {
                System.out.println("Level over!");
                System.out.println("Total steps made: " + sender.getSteps());
            }

            @Override
            public void notifyTick(Level sender) {
                gridPrinter.print(sender.getGrid(), sender.getPlayer());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        };
        Level level1 = new Level1(grid, levelNotifier).placePlayer(5, 5);
        level1.go();
    }

    @Override
    public String getName() {
        return windowTitle;
    }
}
