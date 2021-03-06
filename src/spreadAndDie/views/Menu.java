package spreadAndDie.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 Represents a multiple choice menu.
 Facilitates item selection through keyboard input.
 Items themselves are Views, so the menu can directly invoke them
 and retrieve the item title that is to be shown.
 */
public class Menu implements View {
	private List<View> choiceActions;
	private String title;

	public Menu(String title) {
		if (title == null)
			throw new IllegalArgumentException("title");
		this.title = title;
		this.choiceActions = new ArrayList<View>();
	}

	public void addChoice(View view) {
		if (view == null)
			throw new IllegalArgumentException("view");
		choiceActions.add(view);
	}

	@Override
	public void show() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			try {
				System.out.println();
				System.out.println(title);
				System.out.println();
				int index = 0;
				for (View choice : this.choiceActions) {
					System.out.println(index + ". " + choice.getName());
					index++;
				}
				System.out.println();
				System.out
						.print("Type the number of the item you wish to select and press Enter: ");
				int choice = scan.nextInt();
				View selectedChoice = choiceActions.get(choice);
				selectedChoice.show();
				return;
			} catch (Exception e) {
				e.printStackTrace();
				scan.next();
			}
		}
	}

	@Override
	public String getName() {
		return title;
	}
}
