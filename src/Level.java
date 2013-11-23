/*
    Provides common behavior for all levels.
    Maintains a Grid and a Player.
    Maintains a count of the steps taken so far.
    Provides a way for an interested class to 'listen'
    for end of tick/end of level events
 */
public abstract class Level {
    protected final Grid grid;
    protected Player player;
    protected boolean levelFinished;
    protected int stepsPerformed;
    private EventListener listener;

    public Level(Grid grid){
        this.grid = grid;
    }

    public abstract String getName();

    public Level attachListener(EventListener listener){
        this.listener = listener;
        return this;
    }

    public Level placePlayer(int column, int row){
        if(!this.grid.inBounds(column, row)){
            throw new IllegalArgumentException();
        }
        this.player = new Player(this.grid.cellAt(column, row));
        return this;
    }

    public Grid getGrid(){
        return this.grid;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Level setPlayer(Player player){
        this.player = player;
        return this;
    }

    public int getSteps(){
        return this.stepsPerformed;
    }

    public void go(){
        while(!this.levelFinished)
        {
            this.stepsPerformed++;
            movePlayer();
            grid.tick();
            listener.onTick(this);
            killPlayerIfDiseased();
        }
    }

    protected abstract void movePlayer();

    protected void onPlayerKilled(){
        this.triggerLevelEnd();
    }

    protected void triggerLevelEnd() {
        this.levelFinished = true;
        this.listener.onEnd(this);
    }

    private void killPlayerIfDiseased() {
        if(this.player.currentCell().isDiseased()){
            this.player.kill();
            onPlayerKilled();
        }
    }
}
