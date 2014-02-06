package spreadAndDie.mechanics;

public class PlayerOutOfBoundsException extends Throwable {
	public PlayerOutOfBoundsException() {
	}

	public PlayerOutOfBoundsException(String msg) {
		super(msg);
	}
}
