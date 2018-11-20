package diaballik.model;

public abstract class Player {

	private String name;

	private Color color;

	public Player(String name, Color color) {

	}

	public abstract Action play(Board board);

	public Color getColor() {
		return null;
	}

	public String getName() {
		return null;
	}

}
