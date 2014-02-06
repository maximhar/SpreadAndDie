package spreadAndDie.views;

import java.io.IOException;

import spreadAndDie.io.Highscore;

public class HighscoreWindow implements View {
	private String windowTitle = "Highscores Window";
	private Menu mainMenu;
	private final String filePath = "scores.csv";
	
	public HighscoreWindow(Menu mainMenu) {
		this.mainMenu = mainMenu;
	}

	@Override
	public void show() {
		System.out.println();
		System.out.println("Highscores");
		try {
			Highscore highscore = Highscore.openHighscore(Highscore.defaultPath);
			if(highscore.any()){
				int rank = 0;
				for(Highscore.Entry entry : highscore){
					rank++;
					System.out.println(rank + ". " + entry.getScore() + " - " + entry.getUsername());
				}
			} else System.out.println("No scores recorded yet.");
			
		} catch (IOException e) {
			System.out.println("Error opening " + filePath);
		}
		this.mainMenu.show();
	}

	@Override
	public String getName() {
		return windowTitle;
	}
}
