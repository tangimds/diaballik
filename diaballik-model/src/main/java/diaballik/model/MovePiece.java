package diaballik.model;

import java.util.ArrayList;

import static diaballik.model.Color.WHITE;

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

	}

	@Override
	public void redo(final Board board) {

	}

	@Override
	public void undo(final Board board) {

	}

	@Override
	public boolean verifyAction(Board b) {
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

	public static void main(final String[] args) {
		StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		Board board = standardBoardBuilder.buildBoard();

		//move la piece (3,1) en (3,2)
		MovePiece mp = new MovePiece(board.getPiece(3,1),3,2);
		System.out.println("dx : " + mp.dx);
		System.out.println("dy : " + mp.dy);
		System.out.println("autorise : " + mp.verifyAction(board));
	}
}

