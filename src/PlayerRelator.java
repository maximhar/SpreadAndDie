public class PlayerRelator {
    private CellRelator[] cells;
    private Player player;

    public PlayerRelator(Player player){
        this.cells = new CellRelator[4];
        this.player = player;
        createCellRelators();
    }

    public int cellCount(){
        return cells.length;
    }

    public CellRelator getCellRelator(int index){
        if(index < 0 || index >= cells.length)
            throw new IllegalArgumentException("index");
        return cells[index];
    }

    public CellRelator[] getRelators(){
        return cells.clone();
    }

    public Player movePlayerTo(CellRelator dest) throws PlayerOutOfBoundsException{
        if(dest.cell == player.currentCell().getLeft()){
            player.moveLeft();
        }
        else if(dest.cell == player.currentCell().getTop()){
            player.moveUp();
        }
        else if(dest.cell == player.currentCell().getRight()){
            player.moveRight();
        }
        else if(dest.cell == player.currentCell().getBottom()){
            player.moveDown();
        }
        return player;
    }

    private void createCellRelators() {
        CellRelator left = new CellRelator(player.currentCell().getLeft());
        CellRelator top = new CellRelator(player.currentCell().getTop());
        CellRelator right = new CellRelator(player.currentCell().getRight());
        CellRelator bottom = new CellRelator(player.currentCell().getBottom());

        left.setOpposite(right);
        right.setOpposite(left);
        top.setOpposite(bottom);
        bottom.setOpposite(top);

        left.setAdjacent1(top);
        left.setAdjacent2(bottom);
        top.setAdjacent1(left);
        top.setAdjacent2(right);
        right.setAdjacent1(top);
        right.setAdjacent2(bottom);
        bottom.setAdjacent1(right);
        bottom.setAdjacent2(left);

        cells[0] = left;
        cells[1] = top;
        cells[2] = right;
        cells[3] = bottom;
    }


    public class CellRelator {
        private CellRelator opposite;
        private CellRelator adjacent1;
        private CellRelator adjacent2;
        private final Cell cell;

        public CellRelator(Cell cell){
            this.cell = cell;
        }

        public Cell getCell(){
            return this.cell;
        }

        public CellRelator getOpposite(){
            return this.opposite;
        }

        public CellRelator getAdjacent1(){
            return this.adjacent1;
        }

        public CellRelator getAdjacent2(){
            return this.adjacent2;
        }

        public CellRelator setOpposite(CellRelator cell){
            this.opposite = cell;
            return this;
        }

        public CellRelator setAdjacent1(CellRelator cell){
            this.adjacent1 = cell;
            return this;
        }

        public CellRelator setAdjacent2(CellRelator cell){
            this.adjacent2 = cell;
            return this;
        }
    }
}
