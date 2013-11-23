import java.util.Random;
/*
    The player moves randomly - with the exception that
    when it encounters a border, it moves to the cell opposite of the border
 */
public class Level1 extends Level{
    private Random randomizer;
    public Level1(Grid grid){
        super(grid);
        this.randomizer = new Random();
    }

    @Override
    public String getName() {
        return "Level 1";
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
