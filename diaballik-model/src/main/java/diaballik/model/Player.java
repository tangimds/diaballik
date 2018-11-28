package diaballik.model;

public abstract class Player {

	protected String name;
	protected Color color;

	public Player(final String name, final Color color) {
	    this.name = name;
	    this.color = color;
	}

	public abstract Action play(Board board);

	public Color getColor() {
		return color;
	}
	public String getName() {
		return name;
	}

}
