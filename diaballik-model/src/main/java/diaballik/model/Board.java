package diaballik.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class Board {

	//ANSI colors for board representation
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";

	private Piece currentWhiteHolder;
	private Piece currentBlackHolder;
	private ArrayList<Piece> pieces;

	public Board() {
		super();
		pieces = new ArrayList<>();
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(final ArrayList<Piece> p) {
		pieces = p;
	}

	public void addPiece(final Piece p) {
		pieces.add(p);
	}

	public void setCurrentWhiteHolder(final Piece p) {
		currentWhiteHolder = p;
	}

	public void setCurrentBlackHolder(final Piece p) {
		currentBlackHolder = p;
	}

	public Piece getCurrentWhiteHolder() {
		return currentWhiteHolder;
	}

	public Piece getCurrentBlackHolder() {
		return currentBlackHolder;
	}

	public Piece getPiece(final int x, final int y) {
		final Optional<Piece> pieceRes = pieces.stream()
				.filter(p -> p.getY() == y && p.getX() == x)
				.findFirst();
		return pieceRes.get();
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

	//unused
	/*public Optional<Piece> movePiece(final Piece p, final int x, final int y) {
		final Optional<Piece> pieceRes = pieces.stream()
				.filter(pTemp -> pTemp.getX() == p.getX() && pTemp.getY() == p.getY())
				.findFirst();
		pieceRes.ifPresent(piece -> piece.setPosition(x, y));
		return pieceRes;
	}

	//unused
	public Piece moveBall(final Piece p) {
		final Color color = p.getColor();
		if (color == Color.WHITE) {
			currentWhiteHolder = p;
		} else {
			currentBlackHolder = p;
		}
		return p;
	}*/

	public Optional<Color> win() {
		if (currentBlackHolder.getY() == 1) {
			return Optional.of(Color.BLACK);
		} else if (currentWhiteHolder.getY() == 7) {
			return Optional.of(Color.WHITE);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public String toString() {

		//starting by saying explicitly the coordinates of the holders
		final String[] str = {" > The WHITE ball is at (" + currentWhiteHolder.getX() + "," + currentWhiteHolder.getY() + ")\n" +
				" > The BLACK ball is at (" + currentBlackHolder.getX() + "," + currentBlackHolder.getY() + ")\n\n" +
				"--------------------------\n\n"};

		IntStream.rangeClosed(1, 7).forEach(jj -> {
			IntStream.rangeClosed(1, 7).forEach(i -> {
				//inverse the coordiante j to start from the top of the grid
				final int j = 8 - jj;
				//get the piece at the coordinates (i,j)
				final Optional<Piece> piece = pieces.stream().filter(p -> p.getX() == i && p.getY() == j).findFirst();

				//if there is a piece, print the bound caracter
				piece.ifPresent(piece1 -> {
					if (piece1.equals(currentBlackHolder)) {
						str[0] = str[0].concat(" B");
					} else if (piece1.equals(currentWhiteHolder)) {
						str[0] = str[0].concat(" W");
					} else if (piece1.getColor() == Color.WHITE) {
						str[0] = str[0].concat(" w");
					} else if (piece1.getColor() == Color.BLACK) {
						str[0] = str[0].concat(" b");
					}
				});

				//else print a point to visualize an empty slot
				if (!piece.isPresent()) {
					str[0] = str[0].concat(" •");
				}
			});
			str[0] = str[0].concat("\n");
		});
		return str[0];
	}

	public String toStringColor() {

		//starting by saying explicitly the coordinates of the holders
		final String[] str = {" > The" + ANSI_BLUE + " WHITE" + ANSI_RESET + " ball is at (" + currentWhiteHolder.getX() + "," + currentWhiteHolder.getY() + ")\n" +
				" > The " + ANSI_RED + "BLACK" + ANSI_RESET + " ball is at (" + currentBlackHolder.getX() + "," + currentBlackHolder.getY() + ")\n\n" +
				"--------------------------\n\n"};

		IntStream.rangeClosed(1, 7).forEach(jj -> {
			IntStream.rangeClosed(1, 7).forEach(i -> {
				//inverse the coordiante j to start from the top of the grid
				final int j = 8 - jj;
				//get the piece at the coordinates (i,j)
				final Optional<Piece> piece = pieces.stream().filter(p -> p.getX() == i && p.getY() == j).findFirst();

				//if there is a piece, print the bound caracter
				piece.ifPresent(piece1 -> {
					if (piece1.equals(currentBlackHolder)) {
						str[0] = str[0].concat(ANSI_RED + " 0" + ANSI_RESET);
					} else if (piece1.equals(currentWhiteHolder)) {
						str[0] = str[0].concat(ANSI_BLUE + " 0" + ANSI_RESET);
					} else if (piece1.getColor() == Color.WHITE) {
						str[0] = str[0].concat(ANSI_BLUE + " *" + ANSI_RESET);
					} else if (piece1.getColor() == Color.BLACK) {
						str[0] = str[0].concat(ANSI_RED + " *" + ANSI_RESET);
					}
				});

				//else print a point to visualize an empty slot
				if (!piece.isPresent()) {
					str[0] = str[0].concat(" •");
				}
			});
			str[0] = str[0].concat("\n");
		});
		return str[0];
	}

	public Board copy() {
		final Board b = new Board();
		b.setCurrentWhiteHolder(this.getCurrentWhiteHolder());
		b.setCurrentBlackHolder(this.getCurrentBlackHolder());
		pieces.forEach(p -> {
			b.addPiece(p.copy());
		});
		return b;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Board)) {
			return false;
		}
		final Board board = (Board) o;
		return Objects.equals(getCurrentWhiteHolder(), board.getCurrentWhiteHolder()) &&
				Objects.equals(getCurrentBlackHolder(), board.getCurrentBlackHolder()) &&
				Objects.equals(getPieces(), board.getPieces());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getCurrentWhiteHolder(), getCurrentBlackHolder(), getPieces());
	}

}
