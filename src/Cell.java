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

    private boolean isInfected;
    private boolean isDiseased;
    private short region;

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

    public void makeDiseased(){
        this.isDiseased = true;
        this.isInfected = true;
    }

    public void makeInfected(){
        this.isInfected = true;
    }

    public boolean isInfected(){
        return this.isInfected;
    }

    public boolean isDiseased(){
        return this.isDiseased;
    }

    public boolean isHealthy(){
        return !(this.isDiseased || this.isInfected);
    }

    public short getRegion() {
        return region;
    }

    public void setRegion(short region) {
        this.region = region;
    }

    public void tick(){
        if(this.isDiseased) return;
        if(this.isInfected){
            this.makeDiseased();
            return;
        }
        checkCell(this.left);
        checkCell(this.right);
        checkCell(this.top);
        checkCell(this.bottom);
    }

    private void checkCell(Cell cell){
        if(cell.isDiseased) {
            if(cell.region == this.region) {
                this.makeDiseased();
            }
            else this.makeInfected();
        }
    }
}
