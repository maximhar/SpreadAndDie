package spreadAndDie.levels;

public class LevelException extends Throwable {
	public LevelException() {
	}

	public LevelException(String msg) {
		super(msg);
	}

	public LevelException(Throwable inner) {
		super(inner);
	}
}
