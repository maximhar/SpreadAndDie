public abstract class Level {
    protected final Grid grid;
    protected Player player;
    private final OutcomeNotifier notifier;

    public Level(Grid grid, OutcomeNotifier notifier){
        this.grid = grid;
        this.notifier = notifier;
    }

    public Level placePlayer(int column, int row){
        if(this.grid.inBounds(column, row)){
            throw new IllegalArgumentException();
        }
        this.player = new Player(this.grid.cellAt(column, row));
        return this;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Level setPlayer(Player player){
        this.player = player;
        return this;
    }

    public Level tick(){
        movePlayer();
        grid.tick();
        killPlayerIfDiseased();
        return this;
    }

    protected abstract void movePlayer();

    protected abstract void onPlayerKilled();

    protected void triggerVictory(){
        this.notifier.notifyVictory(this);
    }

    protected void triggerDefeat() {
        this.notifier.notifyLoss(this);
    }

    private void killPlayerIfDiseased() {
        if(this.player.currentCell().isDiseased()){
            this.player.kill();
            onPlayerKilled();
        }
    }
}
