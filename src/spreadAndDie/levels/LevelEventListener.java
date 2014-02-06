package spreadAndDie.levels;

public interface LevelEventListener {
	public void onDeath(Level sender);

	public void onEnd(Level sender);

	public void onTick(Level sender);

	public void onPlacementRequired(Level sender);
}
