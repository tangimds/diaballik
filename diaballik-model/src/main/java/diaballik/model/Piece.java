package diaballik.model;

public class Piece {

	private Color color;
	private int x;
	private int y;

	public Piece(final Color color, final int x, final int y) {
		this.color = color;
		this.x = x;
		this.y = y;
	}


	public void setPosition(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public Color getColor() {
	    return color;
	}
	public int getX() {
		return x;
	}
	public int getY() {
	    return y;
	}

}
