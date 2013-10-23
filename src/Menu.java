import java.util.ArrayList;
import java.util.List;

public class Menu implements View {
    private List<View> choiceActions;
    private String title;

    public Menu(String title){
        if(title == null) throw new IllegalArgumentException("title");
        this.title = title;
        this.choiceActions = new ArrayList<View>();
    }

    public void addChoice(View view){
        if(view == null) throw new IllegalArgumentException("view");
        choiceActions.add(view);
    }

    @Override
    public void show() {
        System.out.println(this.title);
    }

    @Override
    public String getName() {
        return title;
    }
}
