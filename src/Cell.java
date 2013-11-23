/*
    Represents a cell in a Grid.
    Knows what its neighbouring cells are.
    Knows whether it is infected or diseased.
    Knows its region.
    Can 'tick' - change its state based on the state of
    its neighbouring cells.
    Can tell if any of its neighboring cells are diseased.
    Maintains a static border cell instance, which
    is used to represent impassable cells. (essentially borders)
 */
public class Cell {
    public static final Cell border = new Cell();

    private Cell left;
    private Cell right;
    private Cell top;
    private Cell bottom;
    private boolean beingInfected;
    private boolean beingDiseased;
    private boolean isInfected;
    private boolean isDiseased;
    private int region;

    public boolean isBorder(){
        return this == Cell.border;
    }

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

    public Cell setLeft(Cell left){
        if(left == null) throw new IllegalArgumentException();
        this.left = left;
        return this;
    }

    public Cell setRight(Cell right) {
        if(right == null) throw new IllegalArgumentException();
        this.right = right;
        return this;
    }

    public Cell setTop(Cell top) {
        if(top == null) throw new IllegalArgumentException();
        this.top = top;
        return this;
    }

    public Cell setBottom(Cell bottom) {
        if(bottom == null) throw new IllegalArgumentException();
        this.bottom = bottom;
        return this;
    }

    public Cell makeDiseased(){
        this.isDiseased = true;
        this.isInfected = true;
        return this;
    }

    public Cell makeInfected(){
        this.isInfected = true;
        return this;
    }

    public boolean isInfected(){
        return this.isInfected;
    }

    public boolean isDiseased(){
        return this.isDiseased;
    }

    public boolean isNearDisease(){
        return this.left.isDiseased() || this.right.isDiseased() || this.top.isDiseased() || this.bottom.isDiseased();
    }

    public int getRegion() {
        return region;
    }

    public Cell setRegion(int region) {
        this.region = region;
        return this;
    }

    public void beginTick(){
        if(this.isDiseased) return;
        if(this.isInfected){
            this.beingDiseased = true;
            return;
        }
        //check if any of the neighbouring cells are diseased
        checkCell(this.left);
        checkCell(this.right);
        checkCell(this.top);
        checkCell(this.bottom);
    }

    public void endTick(){
        if(this.beingInfected) this.makeInfected();
        if(this.beingDiseased) this.makeDiseased();
        this.beingDiseased = this.beingInfected = false;
    }

    public static Cell createWithNeighbours(Cell topCell, Cell leftCell) {
        Cell c = new Cell().setTop(topCell).setLeft(leftCell).setBottom(Cell.border).setRight(Cell.border);
        if(topCell != Cell.border)
            topCell.setBottom(c);
        if(leftCell != Cell.border)
            leftCell.setRight(c);
        return c;
    }

    private void checkCell(Cell cell){
        if(cell.isDiseased) {
            if(cell.region == this.region) {
                this.beingDiseased = true;
            }
            else this.beingInfected = true;
        }
    }
}
