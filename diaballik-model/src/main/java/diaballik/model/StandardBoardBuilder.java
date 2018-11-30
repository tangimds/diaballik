package diaballik.model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class StandardBoardBuilder implements BoardBuilder {

	public StandardBoardBuilder() {
		super();
	}

	@Override
	public Board buildBoard() {
		final Board board = new Board();
		final ArrayList<Piece> pieces = new ArrayList<>();
		//White pieces
		//Populating the board
		IntStream.rangeClosed(1, 7).forEach(x -> pieces.add(new Piece(Color.WHITE, x, 1)));
		//Setting the white ball holder
		final Optional<Piece> whiteHolder = pieces.stream().filter(p -> p.getX() == 4 && p.getColor() == Color.WHITE).findFirst();
		whiteHolder.ifPresent(board::setCurrentWhiteHolder);

		//Black pieces
		//Populating the board
		IntStream.rangeClosed(1, 7).forEach(x -> pieces.add(new Piece(Color.BLACK, x, 7)));
		//Setting the black ball holder
		final Optional<Piece> blackHolder = pieces.stream().filter(p -> p.getX() == 4 && p.getColor() == Color.BLACK).findFirst();
		blackHolder.ifPresent(board::setCurrentBlackHolder);


		board.setPieces(pieces);
		return board;
	}

}
