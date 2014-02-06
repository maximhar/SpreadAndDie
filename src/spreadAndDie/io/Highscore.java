package spreadAndDie.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Highscore implements Iterable<Highscore.Entry>{
	private String path;
	private final int highscoreCount = 10;
	private List<Entry> entries;
	
	private Highscore() {
		this.entries = new LinkedList<Entry>();
	}
	
	public static String defaultPath = "scores.csv";
	
	public static Highscore openHighscore(String path) throws IOException {
		File file = new File(path);
		file.createNewFile();
		List<String> lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
		Highscore highscore = new Highscore();
		highscore.path = path;
		for(String line : lines){
			String[] tokens = line.split(",");
			if(tokens.length == 2){
				highscore.entries.add(new Entry(tokens[0], Integer.parseInt(tokens[1])));
			}
		}
		return highscore;
	}
	
	public boolean any(){
		return this.entries != null && this.entries.size() > 0;
	}
	
	public void addEntry(String username, int score){
		//keep them sorted
		int i;
		for(i = 0; i < entries.size(); i++){
			if(entries.get(i).getScore() <= score) break;
		}
		this.entries.add(i, new Entry(username, score));
	}
	
	public void save() throws IOException{
		File file = new File(path);
		file.createNewFile();
		PrintWriter writer = new PrintWriter(new FileWriter(file));
		try {
			for(Entry entry : this.entries) {
				writer.println(entry.username + "," + entry.score);
			}
		} finally {
			writer.close();
		}
		
	}
	
	public static class Entry {
		private String username;
		private int score;
		
		private Entry(String username, int score){
			this.username = username;
			this.score = score;
		}

		public String getUsername() {
			return username;
		}

		public int getScore() {
			return score;
		}
	}

	@Override
	public Iterator<Entry> iterator() {
		return this.entries.iterator();
	}
}
