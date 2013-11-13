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
        for(int i = 0; i < 10; i++)
        {
            grid.tick();
            grid.print(System.out, cellPrinter);
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
