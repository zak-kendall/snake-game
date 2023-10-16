package snake;

public enum SnakeSpeed {
	SLOW(200),
	MEDIUM(150),
	FAST(100),
	LUDICROUS(50);

	private int delayMillis;

	private SnakeSpeed(int delayMillis) {
		this.delayMillis = delayMillis;
	}

	public int getDelayMillis() {
		return delayMillis;
	}
}
