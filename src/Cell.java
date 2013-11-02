public class Cell {

    public enum CellState {
        Healthy,
        Infected,
        Diseased
    }

    public static final Cell border = new Cell();

    private Cell left;
    private Cell right;
    private Cell top;
    private Cell bottom;

    private CellState state;

    public Cell getLeft() {
        return left;
    }

    public Cell getRight() {
        return right;
    }

    public Cell getTop() {
        return top;
    }

    public Cell getBottom(){
        return bottom;
    }

    public void setLeft(Cell left){
        if(left == null) throw new IllegalArgumentException();
        this.left = left;
    }

    public void setRight(Cell right) {
        if(right == null) throw new IllegalArgumentException();
        this.right = right;
    }

    public void setTop(Cell top) {
        if(top == null) throw new IllegalArgumentException();
        this.top = top;
    }

    public void setBottom(Cell bottom) {
        if(bottom == null) throw new IllegalArgumentException();
        this.bottom = bottom;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        if(state == null) throw new IllegalArgumentException();
        this.state = state;
    }

}
