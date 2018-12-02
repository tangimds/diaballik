package diaballik.model;

import java.util.ArrayList;
import java.util.Optional;

public class Board {

	private Piece currentWhiteHolder;
	private Piece currentBlackHolder;
	private ArrayList<Piece> pieces;

	public Board() {
		super();
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> p) {
		pieces = p;
	}

	public void setCurrentWhiteHolder(Piece p) {
		currentWhiteHolder = p;
	}

	public void setCurrentBlackHolder(Piece p) {
		currentBlackHolder = p;
	}

	public Optional<Piece> getPiece(final int x, final int y) {
		final Optional<Piece> pieceRes = pieces.stream()
				.filter(p -> p.getY() == y && p.getX() == x)
				.findFirst();
		return pieceRes;
	}

	public Piece getBall(final Color color) {
		final Piece p;
		if (color == Color.WHITE) {
			p = currentWhiteHolder;
		} else {
			p = currentBlackHolder;
		}
		return p;
	}

	public Optional<Piece> movePiece(final Piece p, final int x, final int y) {
		final Optional<Piece> pieceRes = pieces.stream()
				.filter(pTemp -> pTemp.getX() == p.getX() && pTemp.getY() == p.getY())
				.findFirst();
		pieceRes.ifPresent(piece -> piece.setPosition(x, y));
		return pieceRes;
	}

	public Piece moveBall(final Piece p) {
		Color color = p.getColor();
		if (color == Color.WHITE) {
			currentWhiteHolder = p;
		} else {
			currentBlackHolder = p;
		}
		return p;
	}

	public Color win() {
		// regarder si une piece d'une couleur est dans le camp adverse, return la couleur , null sinon
		if (currentBlackHolder.getY() == 1) {
			return Color.BLACK;
		} else if (currentWhiteHolder.getY() == 7) {
			return Color.WHITE;
		} else {
			return null;
		}
	}

}
