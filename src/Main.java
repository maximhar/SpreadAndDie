public class Main {
    public static void main(String[] args){
        Main game = new Main();
        game.run();
    }

    private void run() {
        Menu mainMenu = new Menu("Main Menu");
        GameWindow gameWindow = new GameWindow(mainMenu);
        HighscoreWindow highscoreWindow = new HighscoreWindow(mainMenu);
        mainMenu.addChoice(gameWindow);
        mainMenu.addChoice(highscoreWindow);
        mainMenu.show();
    }
}
