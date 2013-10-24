public class HighscoreWindow implements View {
    private String windowTitle = "Highscores window";
    private Menu mainMenu;

    public HighscoreWindow(Menu mainMenu){
        this.mainMenu = mainMenu;
    }

    @Override
    public void show() {
        System.out.println("Highscores here");
    }

    @Override
    public String getName() {
        return windowTitle;
    }
}
