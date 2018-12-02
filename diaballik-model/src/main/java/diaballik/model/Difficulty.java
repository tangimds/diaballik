package diaballik.model;

public enum Difficulty {
	NOOB(0), STARTING(1), PROGRESSIVE(2);

	private int value;

	private Difficulty(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
};
