import spreadAndDie.views.GameWindow;
import spreadAndDie.views.HighscoreWindow;
import spreadAndDie.views.Menu;
import spreadAndDie.views.View;

public class RunGame {
	public static void main(String[] args) {
		RunGame game = new RunGame();
		game.run();
	}

	private void run() {
		Menu mainMenu = new Menu("Game Menu");
		GameWindow gameWindow = new GameWindow(mainMenu);
		HighscoreWindow highscoreWindow = new HighscoreWindow(mainMenu);
		mainMenu.addChoice(gameWindow);
		mainMenu.addChoice(highscoreWindow);
		mainMenu.addChoice(new View() {
			@Override
			public void show() {
				System.out.println("Exiting.");
			}

			@Override
			public String getName() {
				return "Exit";
			}
		});
		mainMenu.show();
	}
}
