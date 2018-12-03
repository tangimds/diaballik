package diaballik.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

public class StartingLevel implements Level {

	@Override
	public Action chooseAction(final Board board) {
		//TODO : regler complexité cyclomatique
		final ArrayList<Piece> blackPieces = new ArrayList<>();
		board.getPieces().stream().filter(p -> p.getColor() == Color.BLACK && !p.equals(board.getCurrentBlackHolder())).forEach(blackPieces::add);
		final Piece blackHolder = board.getCurrentBlackHolder();
		Collections.shuffle(blackPieces);
		Optional<Action> a = Optional.empty();
		final Random r = new Random();
		final Double proba = r.nextDouble();
		if (proba > 0.6) { //Move a piece in front
			final Piece p = blackPieces.get(0);
			a = Optional.of(new MovePiece(p, p.getX(), p.getY() - 1));

		} else if (proba > 0.2) { //Move a ball to front
			final Optional<Piece> optEndingPiece = blackPieces.stream().filter(p -> (p != blackHolder) &&
					(blackHolder.getY() - p.getY() > 0) &&
					(blackHolder.getX() == (p.getX()) ||
							(blackHolder.getY() == p.getY()) ||
							(Math.abs(blackHolder.getX() - p.getX()) == Math.abs(blackHolder.getY() - p.getY())))).findFirst();
			if (optEndingPiece.isPresent()) {
				a = Optional.of(new MoveBall(blackHolder, optEndingPiece.get()));
			}
		} else if (proba > 0.1) { //Move a piece in whatever direction
			final Piece p = blackPieces.get(0);
			final int dl = -1 + 2 * r.nextInt(1);
			if (r.nextBoolean()) {
				a = Optional.of(new MovePiece(p, p.getX() + dl, p.getY()));
			} else {
				a = Optional.of(new MovePiece(p, p.getX(), p.getY() + dl));
			}
		} else { //Move a ball
			final Optional<Piece> optEndingPiece = blackPieces.stream().filter(p -> (p != blackHolder) && (blackHolder.getX() == (p.getX()) ||
					(blackHolder.getY() == p.getY()) ||
					(Math.abs(blackHolder.getX() - p.getX()) == Math.abs(blackHolder.getY() - p.getY())))).findFirst();
			if (optEndingPiece.isPresent()) {
				a = Optional.of(new MoveBall(blackHolder, optEndingPiece.get()));
			}
		}

		if (!a.isPresent() || !a.get().verifyAction(board)) {
			a = Optional.of(chooseAction(board));
		}
		return a.get();
	}
}
