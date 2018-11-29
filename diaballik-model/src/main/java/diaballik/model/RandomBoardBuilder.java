package diaballik.model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomBoardBuilder implements BoardBuilder {

	public RandomBoardBuilder() {
		super();
	}

	@Override
	public Board buildBoard() {
		final Board board = new Board();
		final ArrayList<Piece> pieces = new ArrayList<>();
		Random r = new Random();
		//White pieces
		//Populating the board
		IntStream.rangeClosed(1, 7).forEach(x -> pieces.add(new Piece(Color.WHITE, x, 1)));
		//Setting the white ball holder
		final int randWhite = r.nextInt(7) + 1;
		final Optional<Piece> whiteHolder = pieces.stream().filter(p -> p.getX() == randWhite && p.getColor() == Color.WHITE).findFirst();
		whiteHolder.ifPresent(board::setCurrentWhiteHolder);

		//Black pieces
		//Populating the board
		IntStream.rangeClosed(1, 7).forEach(x -> pieces.add(new Piece(Color.BLACK, x, 7)));
		//Setting the black ball holder
		final int randBlack = r.nextInt(7) + 1;
		final Optional<Piece> blackHolder = pieces.stream().filter(p -> p.getX() == randBlack && p.getColor() == Color.BLACK).findFirst();
		blackHolder.ifPresent(board::setCurrentBlackHolder);

		board.setPieces(pieces);
		return board;
	}

}
