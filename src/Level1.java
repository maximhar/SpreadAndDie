import java.util.Random;

public class Level1 extends Level{
    private Random movementRandomizer;
    public Level1(Grid grid, EventNotifier notifier){
        super(grid, notifier);
        this.movementRandomizer = new Random();
    }

    @Override
    protected void movePlayer() {
        MovementHelper helper = new MovementHelper(this.player);
        MovementHelper.CellHelper randomCell = helper.getHelper(movementRandomizer.nextInt(helper.helpersCount()));
        try {
            if(randomCell.getCell().isBorder()){
                helper.movePlayerTo(randomCell.getOpposite());
            }
            else {
                helper.movePlayerTo(randomCell);
            }
        } catch (PlayerOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPlayerKilled() {
        this.triggerDefeat();
    }
}
