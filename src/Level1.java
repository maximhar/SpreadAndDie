import java.util.Random;

public class Level1 extends Level{
    private Random movementRandomizer;
    public Level1(Grid grid, EventNotifier notifier){
        super(grid, notifier);
        this.movementRandomizer = new Random();
    }

    @Override
    protected void movePlayer() {
        PositionRelator relator = new PositionRelator(this.player);
        PositionRelator.CellRelator randomCell = relator.getRelator(movementRandomizer.nextInt(relator.cellCount()));
        try {
            if(randomCell.getCell().isBorder()){
                relator.movePlayerTo(randomCell.getOpposite());
            }
            else {
                relator.movePlayerTo(randomCell);
            }
        } catch (PlayerOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
