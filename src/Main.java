public class Main {
    public static void main(String[] args){
        Main game = new Main();
        game.run();
    }

    private void run() {
        Menu mainMenu = new Menu("Main Menu");
        mainMenu.addChoice(new Menu("Hello"));
        mainMenu.addChoice(new Menu("Welcome"));
        mainMenu.show();
    }
}
