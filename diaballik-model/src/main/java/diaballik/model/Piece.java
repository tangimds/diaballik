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

	public void setColor(final Color c) {
		color = c;
	}

	public boolean isBetween(final Piece p1, final Piece p2) {

		/*
		v1 = (x1,y1) vector between p1 and this
		v2 = (x2,y2) vector between p1 and p2
		*/
		final int x1 = this.x - p1.x;
		final int y1 = this.y - p1.y;
		final int x2 = p2.x - p1.x;
		final int y2 = p2.y - p1.y;

		final boolean colinear = x1 * y2 - x2 * y1 == 0;
		//System.out.println("colinear : "+colinear);

		final double cosinus = (x1 * x2 + y1 * y2) / (Math.sqrt((x1 * x1) + (y1 * y1)) * Math.sqrt((x2 * x2) + (y2 * y2)));
		final boolean sameDirection = cosinus > 0;
		final boolean shorter = (Math.sqrt((x1 * x1) + (y1 * y1)) < Math.sqrt((x2 * x2) + (y2 * y2)));
		//System.out.println("same direction : "+sameDirection);

		return colinear && sameDirection && shorter;
	}
}
