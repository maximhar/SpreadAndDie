import java.util.Random;

public class Level1 extends Level{
    private Random randomizer;
    public Level1(Grid grid, EventNotifier notifier){
        super(grid, notifier);
        this.randomizer = new Random();
    }

    @Override
    protected void movePlayer() {
        PlayerRelator relator = new PlayerRelator(this.player);
        PlayerRelator.CellRelator randomCell = relator.getCellRelator(this.randomizer.nextInt(relator.cellCount()));
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
