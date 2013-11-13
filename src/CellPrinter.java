public class CellPrinter {
    private final char infected;
    private final char diseased;
    private final char player;

    public CellPrinter(char infected, char diseased, char player){
        this.infected = infected;
        this.diseased = diseased;
        this.player = player;
    }

    public char convertCell(Cell cell, Player player){
        if(player.currentCell() == cell) return this.player;
        if(cell.isDiseased()) return this.diseased;
        if(cell.isInfected()) return this.infected;
        return Character.toChars(cell.getRegion())[0];
    }
}
