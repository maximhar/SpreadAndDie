import java.io.PrintWriter;
import java.util.Scanner;

public class GameWindow implements View {
    private String windowTitle = "Game Window";
    private Menu mainMenu;
    private Scanner scanner;
    private final PrintWriter writer = new PrintWriter(System.out);
    private final CellPrinter cellPrinter = new CellPrinter('I', 'D', 'X');
    private final GridPrinter gridPrinter = new GridPrinter(cellPrinter, writer);
    public GameWindow(Menu mainMenu) {
        this.mainMenu = mainMenu;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void show() {
        System.out.println(windowTitle);
        runLevel1();
    }

    @Override
    public String getName() {
        return windowTitle;
    }

    private Level createLevel3(){
        final Grid grid = new RectangularGrid(12, 12, 4);
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
        return new Level3(grid, levelNotifier).placePlayer(5, 5);
    }

    private Level createLevel2(){
        final Grid grid = new RectangularGrid(12, 12, 4);
        grid.cellAt(1, 1).makeDiseased();
        EventNotifier levelNotifier = new EventNotifier() {
            @Override
            public void notifyLoss(Level sender) {
                printEndLevelMessage(sender);
                runLevel3();
            }

            @Override
            public void notifyTick(Level sender) {
                printLevel(sender);
            }
        };
        return new Level2(grid, levelNotifier).placePlayer(5, 5);
    }
    
    private Level createLevel1(){
        final Grid grid = new RectangularGrid(12, 12, 4);
        grid.cellAt(1, 1).makeDiseased();
        EventNotifier levelNotifier = new EventNotifier() {
            @Override
            public void notifyLoss(Level sender) {
                printEndLevelMessage(sender);
                runLevel2();
            }

            @Override
            public void notifyTick(Level sender) {
                printLevel(sender);
            }
        };
        return new Level1(grid, levelNotifier).placePlayer(5, 5);
    }

    private void runLevel1() {
        Level level1 = createLevel1();
        System.out.println("Level 1 starting!");
        level1.go();
    }

    private void runLevel2() {
        Level level2 = createLevel2();
        System.out.println("Level 2 starting!");
        level2.go();
    }

    private void runLevel3() {
        Level level3 = createLevel3();
        System.out.println("Level 3 starting!");
        level3.go();
    }

    private void printEndLevelMessage(Level level){
        System.out.println("Level over!");
        System.out.println("Steps made: " + level.getSteps());
    }

    private void printLevel(Level level){
        this.gridPrinter.print(level.getGrid(), level.getPlayer());
        System.out.println("Press Enter please.");
        this.scanner.nextLine();
    }
}
