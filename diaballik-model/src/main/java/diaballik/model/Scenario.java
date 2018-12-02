package diaballik.model;

public enum Scenario {
	STANDARD(0), RANDOM(1), EAU(2);

	private int value;

	private Scenario(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
};
