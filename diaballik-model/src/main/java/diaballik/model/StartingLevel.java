package diaballik.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

public class StartingLevel implements Level {

	@Override
	public Action chooseAction(final Board board) {
		final ArrayList<Piece> blackPieces = new ArrayList<>();
		board.getPieces().stream().filter(p -> p.getColor() == Color.BLACK && !p.equals(board.getCurrentBlackHolder())).forEach(blackPieces::add);
		final Piece blackHolder = board.getCurrentBlackHolder();
		Collections.shuffle(blackPieces);
		Optional<Action> a;
		final Random r = new Random();
		if (r.nextBoolean()) { //move a Piece
			a = actionMovePiece(blackPieces);
		} else { //move the Ball
			a = actionMoveBall(blackPieces, blackHolder);
		}

		if (!a.isPresent() || !a.get().verifyAction(board)) {
			a = Optional.of(chooseAction(board));
		}
		return a.get();
	}

	private Optional<Action> actionMovePiece(final ArrayList<Piece> pieces) {
		Optional<Action> a = Optional.empty();
		Collections.shuffle(pieces);
		final Random r = new Random();
		if (r.nextDouble() > 0.2) {
			//move piece in front
			final Piece p = pieces.get(0);
			a = Optional.of(new MovePiece(p, p.getX(), p.getY() - 1));
		} else {
			// move piece randomly
			final Piece p = pieces.get(0);
			final int dl = -1 + 2 * r.nextInt(1);
			if (r.nextBoolean()) {
				a = Optional.of(new MovePiece(p, p.getX() + dl, p.getY()));
			} else {
				a = Optional.of(new MovePiece(p, p.getX(), p.getY() + dl));
			}
		}
		return a;
	}

	private Optional<Action> actionMoveBall(final ArrayList<Piece> pieces, final Piece holder) {
		Optional<Action> a = Optional.empty();
		Collections.shuffle(pieces);
		final Optional<Piece> optEndingPiece = pieces.stream().filter(p -> (!p.equals(holder)) &&
				(holder.getY() - p.getY() >= 0) &&
				(holder.getX() == (p.getX()) ||
						(holder.getY() == p.getY()) ||
						(Math.abs(holder.getX() - p.getX()) == Math.abs(holder.getY() - p.getY())))).findFirst();
		if (optEndingPiece.isPresent()) {
			a = Optional.of(new MoveBall(holder, optEndingPiece.get()));
		}
		return a;
	}


}
