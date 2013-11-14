public class RunGame {
    public static void main(String[] args){
        RunGame game = new RunGame();
        game.run();
    }

    private void run() {
        Menu mainMenu = new Menu("RunGame Menu");
        GameWindow gameWindow = new GameWindow(mainMenu);
        HighscoreWindow highscoreWindow = new HighscoreWindow(mainMenu);
        mainMenu.addChoice(gameWindow);
        mainMenu.addChoice(highscoreWindow);
        mainMenu.show();
    }
}
