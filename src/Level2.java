public class Level2 extends Level {
    public Level2(Grid grid, EventNotifier notifier){
        super(grid, notifier);
    }

    @Override
    protected void movePlayer(){
        try{
            PlayerRelator relator = new PlayerRelator(this.player);
            // iterate through the player's neighbouring cells
            for(int cell = 0; cell < relator.cellCount(); cell++){
                PlayerRelator.CellRelator cellRelator = relator.getCellRelator(cell);
                //if the cell is diseased, and it's opposite cell is neither diseased
                //nor a border, move the player to the cell's opposite cell and end the loop
                if(cellRelator.getCell().isDiseased()
                        && !cellRelator.getOpposite().getCell().isDiseased()
                        && !cellRelator.getOpposite().getCell().isBorder()){
                    relator.movePlayerTo(cellRelator.getOpposite());
                    break;
                }
            }
        } catch (PlayerOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }
}
