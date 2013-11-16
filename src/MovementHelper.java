public class MovementHelper {
    private CellHelper[] cells;
    private Player player;

    public MovementHelper(Player player){
        this.cells = new CellHelper[4];
        this.player = player;
        createCellHelpers();
    }

    public int helpersCount(){
        return cells.length;
    }

    public CellHelper getHelper(int index){
        if(index < 0 || index >= cells.length)
            throw new IllegalArgumentException("index");
        return cells[index];
    }

    public CellHelper[] getHelpers(){
        return cells.clone();
    }

    public Player movePlayerTo(CellHelper dest) throws PlayerOutOfBoundsException{
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

    private void createCellHelpers() {
        CellHelper left = new CellHelper(player.currentCell().getLeft());
        CellHelper top = new CellHelper(player.currentCell().getTop());
        CellHelper right = new CellHelper(player.currentCell().getRight());
        CellHelper bottom = new CellHelper(player.currentCell().getBottom());

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


    public class CellHelper {
        private CellHelper opposite;
        private CellHelper adjacent1;
        private CellHelper adjacent2;
        private final Cell cell;

        public CellHelper(Cell cell){
            this.cell = cell;
        }

        public Cell getCell(){
            return this.cell;
        }

        public CellHelper getOpposite(){
            return this.opposite;
        }

        public CellHelper getAdjacent1(){
            return this.adjacent1;
        }

        public CellHelper getAdjacent2(){
            return this.adjacent2;
        }

        public CellHelper setOpposite(CellHelper cell){
            this.opposite = cell;
            return this;
        }

        public CellHelper setAdjacent1(CellHelper cell){
            this.adjacent1 = cell;
            return this;
        }

        public CellHelper setAdjacent2(CellHelper cell){
            this.adjacent2 = cell;
            return this;
        }
    }
}
