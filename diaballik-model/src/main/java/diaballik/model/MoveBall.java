package diaballik.model;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class MoveBall implements Action {

	private Piece startingPiece;
	private Piece endingPiece;

	public MoveBall(final Piece startingPiece, final Piece endingPiece) {
		this.startingPiece = startingPiece;
		this.endingPiece = endingPiece;
	}

	@Override
	public void execute(final Board board) {
		if (startingPiece.getColor() == Color.WHITE) {
			board.setCurrentWhiteHolder(endingPiece);
		} else {
			board.setCurrentBlackHolder(endingPiece);
		}
	}

	@Override
	public boolean verifyAction(Board b) {

		//check the rules
		final boolean moveVertical = startingPiece.getX() == endingPiece.getX();
		final boolean moveHorizontal = startingPiece.getY() == endingPiece.getY();
		final boolean moveDiagonal = Math.abs(startingPiece.getX() - endingPiece.getX()) == Math.abs(startingPiece.getY() - endingPiece.getY());
		final boolean moveAuthorized = moveDiagonal || moveHorizontal || moveVertical;

		//check the teams
		final boolean sameTeam = startingPiece.getColor() == endingPiece.getColor();
		final boolean hasTheBall;

		//check if the launcher has the ball
		if (startingPiece.getColor() == Color.WHITE) {
			hasTheBall = startingPiece.equals(b.getCurrentWhiteHolder());
		} else {
			hasTheBall = startingPiece.equals(b.getCurrentBlackHolder());
		}

		final boolean[] enemyOnTheWay = new boolean[1];
		enemyOnTheWay[0] = false;

		b.getPieces().stream()
				.filter(p -> p.isBetween(startingPiece, endingPiece))
				.forEach(pLine -> {
					if (pLine.getColor() != startingPiece.getColor()) {
						enemyOnTheWay[0] = true;
					}
				});

		/*System.out.println("moveAuthorized : "+moveAuthorized);
		System.out.println("enemy on the way : "+enemyOnTheWay[0]);
		System.out.println("same team : "+sameTeam);
		System.out.println("has the ball : "+hasTheBall);*/

		return moveAuthorized && !enemyOnTheWay[0] && sameTeam && hasTheBall;
	}

	@Override
	public void redo(final Board board) {

	}

	@Override
	public void undo(final Board board) {

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MoveBall)) {
			return false;
		}
		MoveBall moveBall = (MoveBall) o;
		return Objects.equals(startingPiece, moveBall.startingPiece) &&
				Objects.equals(endingPiece, moveBall.endingPiece);
	}

	@Override
	public int hashCode() {

		return Objects.hash(startingPiece, endingPiece);
	}

	public static void main(final String[] args) {
		final Piece p1 = new Piece(Color.WHITE, 5, 4);
		final Piece p2 = new Piece(Color.WHITE, 3, 2);
		final Piece p3 = new Piece(Color.WHITE, 7, 6);
		final Piece p4 = new Piece(Color.WHITE, 7, 3);
		final Piece p5 = new Piece(Color.WHITE, 2, 4);
		final Piece p6 = new Piece(Color.WHITE, 3, 4);
		final Piece p7 = new Piece(Color.WHITE, 3, 6);
		final Piece p8 = new Piece(Color.WHITE, 4, 5);

		final MoveBall mb = new MoveBall(p1, p2);
		System.out.println("**** true ****");
		System.out.println(">" + p1.isBetween(p2, p3));
		System.out.println(">" + p1.isBetween(p3, p2));
		System.out.println(">" + p8.isBetween(p7, p1));
		System.out.println(">" + p6.isBetween(p5, p1));
		System.out.println(">" + p6.isBetween(p2, p7));

		System.out.println("**** false **** ");
		System.out.println(">" + p1.isBetween(p8, p7));
		System.out.println(">" + p5.isBetween(p6, p1));
		System.out.println(">" + p1.isBetween(p4, p7));


		//System.out.println("false"+mb.onTheSameLine(p1,p2,p4)+mb.onTheSameLine(p2,p4,p5));


		//final int x1=-1, y1=-2, x2=3 , y2=6;
		//System.out.println((x1*x2+y1*y2) / (Math.sqrt((x1*x1)+(y1*y1))*Math.sqrt((x2*x2)+(y2*y2))));

	}

}
