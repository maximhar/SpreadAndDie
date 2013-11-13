public class GameWindow implements View {
    private String windowTitle = "Game Window";
    private Menu mainMenu;

    public GameWindow(Menu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void show() {
        System.out.println(windowTitle);
        CellGrid grid = new CellGrid(12, 12, 4);
    }

    @Override
    public String getName() {
        return windowTitle;
    }
}
