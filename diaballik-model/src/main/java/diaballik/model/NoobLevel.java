package diaballik.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

public class NoobLevel implements Level {

	@Override
	public Action chooseAction(final Board board) {
		return pickAction(board);
	}

	private Action pickAction(final Board board) {
		final ArrayList<Piece> blackPieces = new ArrayList<>();
		board.getPieces().stream().filter(p -> p.getColor() == Color.BLACK && !p.equals(board.getCurrentBlackHolder())).forEach(blackPieces::add);
		final Piece blackHolder = board.getCurrentBlackHolder();
		Collections.shuffle(blackPieces);
		Optional<Action> a = Optional.empty();
		final Random r = new Random();
		if (r.nextBoolean()) { //Move a piece
			final Piece p = blackPieces.get(0);
			final int dl = -1 + 2 * r.nextInt(1);
			if (r.nextBoolean()) {
				a = Optional.of(new MovePiece(p, p.getX() + dl, p.getY()));
			} else {
				a = Optional.of(new MovePiece(p, p.getX(), p.getY() + dl));
			}
		} else { //Move a ball
			//final Piece startingPiece = blackPieces.get(0);

			final Optional<Piece> optEndingPiece = blackPieces.stream().filter(p -> (!p.equals(blackHolder)) && (blackHolder.getX() == (p.getX()) ||
					(blackHolder.getY() == p.getY()) ||
					(Math.abs(blackHolder.getX() - p.getX()) == Math.abs(blackHolder.getY() - p.getY())))).findFirst();

			if (optEndingPiece.isPresent()) {
				a = Optional.of(new MoveBall(blackHolder, optEndingPiece.get()));
			}
		}

		if (!a.isPresent() || !a.get().verifyAction(board)) {
			a = Optional.of(pickAction(board));
		}
		return a.get();
	}

}
