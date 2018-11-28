package diaballik.model;

public class MovePiece implements Action {

	private int dx;
	private int dy;

	public MovePiece(final Piece piece, final int x, final int y) {

	}

	public int getDx() {
		return dx;
	}
	public int getDy() {
		return dy;
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
	public boolean verifyAction() {
		return false;
	}

}
