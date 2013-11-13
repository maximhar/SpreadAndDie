public class Player {
    private Cell currentCell;

    public Player(Cell startCell){
        this.currentCell = startCell;
    }

    public Player moveUp() throws PlayerOutOfBoundsException {
        if(currentCell.getTop().isBorder()) throw new PlayerOutOfBoundsException();
        currentCell = currentCell.getTop();
        return this;
    }

    public Player moveRight() throws PlayerOutOfBoundsException {
        if(currentCell.getRight().isBorder()) throw new PlayerOutOfBoundsException();
        currentCell = currentCell.getRight();
        return this;
    }

    public Player moveDown() throws PlayerOutOfBoundsException {
        if(currentCell.getBottom().isBorder()) throw new PlayerOutOfBoundsException();
        currentCell = currentCell.getBottom();
        return this;
    }

    public Player moveLeft() throws PlayerOutOfBoundsException {
        if(currentCell.getLeft().isBorder()) throw new PlayerOutOfBoundsException();
        currentCell = currentCell.getLeft();
        return this;
    }

    public Cell currentCell(){
        return currentCell;
    }
}
