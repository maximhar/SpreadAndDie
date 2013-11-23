import java.io.PrintWriter;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
/*
    The main game window.
    Is ultimately responsible for printing the level state,
    navigation between levels and level construction
 */
public class GameWindow implements View {
    private String windowTitle = "Game Window";
    private Menu mainMenu;
    private Scanner scanner;
    private final PrintWriter writer = new PrintWriter(System.out);
    private final CellPrinter cellPrinter = new CellPrinter('I', 'D', 'X');
    private final GridPrinter gridPrinter = new GridPrinter(cellPrinter, writer);
    private final Dictionary<Level, Level> levelSequencer;
    public GameWindow(Menu mainMenu) {
        this.mainMenu = mainMenu;
        this.scanner = new Scanner(System.in);
        this.levelSequencer = new Hashtable<Level, Level>();
    }

    @Override
    public void show() {
        System.out.println(windowTitle);
        Level level1 = makeLevel1();
        Level level2 = makeLevel2();
        Level level3 = makeLevel3();
        levelSequencer.put(level1, level2);
        levelSequencer.put(level2, level3);
        runLevel(level1);
    }

    private void runLevel(Level level) {
        System.out.println(level.getName() + " starting!");
        level.go();
    }

    private Level makeLevel3() {
        Grid level3Grid = new RectangularGrid(12, 12, 4);
        level3Grid.cellAt(1,1).makeDiseased();
        Level level3 = new Level3(level3Grid).placePlayer(5,5);
        attachDefaultListener(level3);
        return level3;
    }

    private Level makeLevel2() {
        Grid level2Grid = new RectangularGrid(12, 12, 4);
        level2Grid.cellAt(1,1).makeDiseased();
        Level level2 = new Level2(level2Grid).placePlayer(5,5);
        attachDefaultListener(level2);
        return level2;
    }

    private Level makeLevel1() {
        Grid level1Grid = new RectangularGrid(12, 12, 4);
        level1Grid.cellAt(1,1).makeDiseased();
        Level level1 = new Level1(level1Grid).placePlayer(5,5);
        attachDefaultListener(level1);
        return level1;
    }

    @Override
    public String getName() {
        return windowTitle;
    }

    private void attachDefaultListener(final Level level){
        EventListener eventListener = new EventListener() {
            @Override
            public void onEnd(Level sender) {
                printEndLevelMessage(sender);
                Level nextLevel;
                if((nextLevel = levelSequencer.get(level)) != null){
                    if(level.getSteps() >= 20) runLevel(nextLevel);
                    else System.out.println("Sadly you don't have enough points to continue to the next level.");
                } else {
                    System.out.println("You're a winner!");
                }
            }

            @Override
            public void onTick(Level sender) {
                printLevel(sender);
            }
        };
        level.attachListener(eventListener);
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
