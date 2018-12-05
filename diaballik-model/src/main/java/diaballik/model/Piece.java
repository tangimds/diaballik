package diaballik.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Piece {

	private Color color;
	private int x;
	private int y;

	@JsonCreator
	public Piece(@JsonProperty("color") final Color color, @JsonProperty("x") final int x, @JsonProperty("y") final int y) {
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

	public Piece copy() {
		return new Piece(color, x, y);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Piece)) {
			return false;
		}
		final Piece piece = (Piece) o;
		return getX() == piece.getX() &&
				getY() == piece.getY() &&
				getColor() == piece.getColor();
	}

	@Override
	public int hashCode() {

		return Objects.hash(getColor(), getX(), getY());
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
