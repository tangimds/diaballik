package diaballik.model;

public class MoveBall implements Action {

	private Piece startingPiece;
	private Piece endingPiece;

	public MoveBall(final Piece startingPiece, final Piece endingPiece) {
		this.startingPiece = startingPiece;
		this.endingPiece = endingPiece;
	}

	public void execute(final int boardBoard) {

	}

	@Override
	public boolean verifyAction(Board b) {
		return false;
	}

	@Override
	public void redo(final Board board) {

	}

	@Override
	public void undo(final Board board) {

	}

	@Override
	public void execute(final Board board) {

	}

}
