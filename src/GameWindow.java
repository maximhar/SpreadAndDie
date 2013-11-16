import java.io.PrintWriter;

public class GameWindow implements View {
    private String windowTitle = "Game Window";
    private Menu mainMenu;
    private final PrintWriter writer = new PrintWriter(System.out);
    private final CellPrinter cellPrinter = new CellPrinter('I', 'D', 'X');
    private final GridPrinter gridPrinter = new GridPrinter(cellPrinter, writer);
    public GameWindow(Menu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void show() {
        System.out.println(windowTitle);
        Level level1 = createLevel1();
        level1.go();
    }

    @Override
    public String getName() {
        return windowTitle;
    }

    private Level createLevel1(){
        final Grid grid = new ToroidalGrid(12, 12, 4);
        grid.cellAt(1, 1).makeDiseased();
        EventNotifier levelNotifier = new EventNotifier() {
            @Override
            public void notifyLoss(Level sender) {
                printEndLevelMessage(sender);
            }

            @Override
            public void notifyTick(Level sender) {
                printLevel(sender);
            }
        };
        return new Level1(grid, levelNotifier).placePlayer(5, 5);
    }

    private void printEndLevelMessage(Level level){
        System.out.println("Level over!");
        System.out.println("Total steps made: " + level.getSteps());
    }

    private void printLevel(Level level){
        this.gridPrinter.print(level.getGrid(), level.getPlayer());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
