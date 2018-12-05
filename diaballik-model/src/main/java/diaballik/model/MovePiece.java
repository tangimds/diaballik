package diaballik.model;

import java.util.Objects;
import java.util.Optional;

public class MovePiece implements Action {

	private int dx;
	private int dy;
	private Piece piece;

	public MovePiece(final Piece piece, final int x, final int y) {
		this.piece = piece;
		dx = x - piece.getX();
		dy = y - piece.getY();
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	private Piece getPiece() {
		return piece;
	}

	@Override
	public void execute(final Board board) {
		final Optional<Piece> optPiece = board.getPieces().stream().filter(p -> p.equals(piece)).findFirst();
		if (optPiece.isPresent()) {
			final Piece p = optPiece.get();
			p.setPosition(p.getX() + dx, p.getY() + dy);
		}
	}

	@Override
	public void redo(final Board board) {
		if (this.verifyAction(board)) {
			this.execute(board);
		}
	}

	@Override
	public void undo(final Board board) {
		System.out.println("undoing MovePiece");
		final MovePiece mp = new MovePiece(piece, piece.getX() - dx, piece.getY() - dy);
		System.out.println("VERIFY : " + mp.verifyAction(board));
		System.out.println("mp" + mp);
		//System.out.println(board.toStringColor());
		if (mp.verifyAction(board)) {
			System.out.println("execute : " + mp);
			mp.execute(board);
		}
	}

	@Override
	public boolean verifyAction(final Board b) {
		final boolean moveAuthorized = Math.abs(dx) <= 1 // decale pas plus de 1 case
				&& Math.abs(dy) <= 1
				&& (Math.abs(dx) + Math.abs(dy) <= 1) // uniquement horizontale/veticale
				&& piece.getX() + dx <= 7
				&& piece.getX() + dx >= 1
				&& piece.getY() + dy <= 7
				&& piece.getY() + dy >= 1;

		final boolean caseOccupied = b.getPieces().stream()
				.anyMatch(p -> p.getX() == piece.getX() + dx
						&& p.getY() == piece.getY() + dy);


		return moveAuthorized && !caseOccupied;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MovePiece)) {
			return false;
		}
		final MovePiece movePiece = (MovePiece) o;
		return getDx() == movePiece.getDx() &&
				getDy() == movePiece.getDy() &&
				Objects.equals(getPiece(), movePiece.getPiece());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getDx(), getDy(), getPiece());
	}

	@Override
	public String toString() {
		return "Move Piece(" + piece + ") -- dx=" + dx + ",dy=" + dy;
	}
}

