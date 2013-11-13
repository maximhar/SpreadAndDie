public class GameWindow implements View {
    private String windowTitle = "Game Window";
    private Menu mainMenu;

    public GameWindow(Menu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void show() {
        System.out.println(windowTitle);
        CellPrinter cellPrinter = new CellPrinter('I', 'D', 'X');
        CellGrid grid = new CellGrid(12, 12, 4);
        grid.print(System.out, cellPrinter);
        try{
            grid.getPlayer().moveDown();
        } catch (PlayerOutOfBoundsException e) {}
        grid.print(System.out, cellPrinter);
    }

    @Override
    public String getName() {
        return windowTitle;
    }
}
