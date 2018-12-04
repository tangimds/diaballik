package diaballik.model;

public enum Scenario {
	STANDARD(0), RANDOM(1), EAU(2);

	private final int value;

	Scenario(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
