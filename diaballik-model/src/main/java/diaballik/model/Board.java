package diaballik.model;

import javax.print.attribute.IntegerSyntax;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class Board {

	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";

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

	public void setPieces(ArrayList<Piece> p) {
		pieces = p;
	}

	public void addPiece(Piece p) { pieces.add(p); }

	public void setCurrentWhiteHolder(Piece p) {
		currentWhiteHolder = p;
	}

	public void setCurrentBlackHolder(Piece p) {
		currentBlackHolder = p;
	}

	public Piece getCurrentWhiteHolder() { return currentWhiteHolder; }

	public Piece getCurrentBlackHolder() { return currentBlackHolder; }

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

	public String toString() {

		//starting by saying explicitly the coordinates of the holders
		final String[] str = {" > The WHITE ball is at ("+currentWhiteHolder.getX()+","+currentWhiteHolder.getY()+")\n" +
				" > The BLACK ball is at ("+currentBlackHolder.getX()+","+currentBlackHolder.getY()+")\n\n" +
				"--------------------------\n\n"};

		IntStream.rangeClosed(1, 7).forEach(jj -> {
			IntStream.rangeClosed(1, 7).forEach(i -> {
				//inverse the coordiante j to start from the top of the grid
				final int j = 8-jj;
				//get the piece at the coordinates (i,j)
				final Optional<Piece> piece = pieces.stream().filter(p->p.getX()==i && p.getY()==j).findFirst();

				//if there is a piece, print the bound caracter
				piece.ifPresent(piece1 -> {
					if(piece1.equals(currentBlackHolder)){
						str[0] = str[0].concat(" B");
					}else if(piece1.equals(currentWhiteHolder)){
						str[0] = str[0].concat(" W");
					}else if(piece1.getColor()==Color.WHITE){
						str[0] = str[0].concat(" w");
					}else if(piece1.getColor()==Color.BLACK){
						str[0] = str[0].concat(" b");
					}
				});

				//else print a point to visualize an empty slot
				if(!piece.isPresent()){
					str[0] = str[0].concat(" •");
				}
			});
			str[0] = str[0].concat("\n");
		});
		return str[0];
	}

	public String toStringColor() {

		//starting by saying explicitly the coordinates of the holders
		final String[] str = {" > The"+ANSI_BLUE+" WHITE"+ANSI_RESET+" ball is at ("+currentWhiteHolder.getX()+","+currentWhiteHolder.getY()+")\n" +
				" > The "+ANSI_RED+"BLACK"+ANSI_RESET+" ball is at ("+currentBlackHolder.getX()+","+currentBlackHolder.getY()+")\n\n" +
				"--------------------------\n\n"};

		IntStream.rangeClosed(1, 7).forEach(jj -> {
			IntStream.rangeClosed(1, 7).forEach(i -> {
				//inverse the coordiante j to start from the top of the grid
				final int j = 8-jj;
				//get the piece at the coordinates (i,j)
				final Optional<Piece> piece = pieces.stream().filter(p->p.getX()==i && p.getY()==j).findFirst();

				//if there is a piece, print the bound caracter
				piece.ifPresent(piece1 -> {
					if(piece1.equals(currentBlackHolder)){
						str[0] = str[0].concat(ANSI_RED+" 0"+ANSI_RESET);
					}else if(piece1.equals(currentWhiteHolder)){
						str[0] = str[0].concat(ANSI_BLUE+" 0"+ANSI_RESET);
					}else if(piece1.getColor()==Color.WHITE){
						str[0] = str[0].concat(ANSI_BLUE+" *"+ANSI_RESET);
					}else if(piece1.getColor()==Color.BLACK){
						str[0] = str[0].concat(ANSI_RED+" *"+ANSI_RESET);
					}
				});

				//else print a point to visualize an empty slot
				if(!piece.isPresent()){
					str[0] = str[0].concat(" •");
				}
			});
			str[0] = str[0].concat("\n");
		});
		return str[0];
	}


	public static void main(final String[] args) {
		/*
		final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		final Board boardStandard = standardBoardBuilder.buildBoard();
		System.out.println("Standard\n"+boardStandard.toString());

		final RandomBoardBuilder randomBoardBuilder = new RandomBoardBuilder();
		final Board boardRandom = randomBoardBuilder.buildBoard();
		System.out.println("Random\n"+boardRandom.toString());

		final EAUBoardBuilder eauBoardBuilder = new EAUBoardBuilder();
		final Board boardEau = eauBoardBuilder.buildBoard();
		System.out.println("Ennemy among us\n"+boardEau.toString());
		*/
	}

}
