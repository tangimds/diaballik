package diaballik.model;

import java.util.Objects;

public class MoveBall implements Action {

	private Piece startingPiece;
	private Piece endingPiece;

	public MoveBall(final Piece startingPiece, final Piece endingPiece) {
		this.startingPiece = startingPiece;
		this.endingPiece = endingPiece;
	}

	public void setStartingPiece(final Piece p) {
		startingPiece = p;
	}

	public void setEndingPiece(final Piece p) {
		endingPiece = p;
	}

	public Piece getStartingPiece() { return startingPiece; }

	public Piece getEndingPiece() { return endingPiece; }


	@Override
	public void execute(final Board board) {
		if (startingPiece.getColor() == Color.WHITE) {
			board.setCurrentWhiteHolder(endingPiece);
		} else {
			board.setCurrentBlackHolder(endingPiece);
		}
	}

	@Override
	public boolean verifyAction(final Board b) {

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
		if (this.verifyAction(board)) {
			this.execute(board);
		}
	}

	@Override
	public void undo(final Board board) {
		System.out.println("undoing MoveBall");
		final MoveBall mb = new MoveBall(this.endingPiece, this.startingPiece);
		System.out.println("VERIFY : " + mb.verifyAction(board));
		System.out.println("mp" + mb);
		//System.out.println(board.toStringColor());
		if (mb.verifyAction(board)) {
			System.out.println("execute : " + mb);
			mb.execute(board);
		}
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MoveBall)) {
			return false;
		}
		final MoveBall moveBall = (MoveBall) o;
		return Objects.equals(startingPiece, moveBall.startingPiece) &&
				Objects.equals(endingPiece, moveBall.endingPiece);
	}

	@Override
	public int hashCode() {

		return Objects.hash(startingPiece, endingPiece);
	}

	@Override
	public String toString() {
		return "passe from " + startingPiece + " to " + endingPiece;
	}
}
