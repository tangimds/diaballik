package diaballik.model;

import java.util.*;
import java.util.stream.IntStream;

public class EAUBoardBuilder implements BoardBuilder {

	public EAUBoardBuilder() {

	}

	@Override
	public Board buildBoard() {
		final Board board = new Board();
		final ArrayList<Piece> pieces = new ArrayList<>();

		//Random values to choose pieces to swap
		final ArrayList<Integer> randomVal = new ArrayList<>(Arrays.asList(1, 2, 5, 6, 7));
		Collections.shuffle(randomVal);
		//White pieces
		//Populating the board
		IntStream.rangeClosed(1, 7).forEach(x -> pieces.add(new Piece(Color.WHITE, x, 1)));
		//Swapping pieces
		pieces.stream().filter(p -> (p.getX() == randomVal.get(0)) || (p.getX() == randomVal.get(1))).forEach(p -> p.setPosition(p.getX(), 7));
		//Setting the white ball holder
		final Optional<Piece> whiteHolder = pieces.stream().filter(p -> p.getX() == 4 && p.getColor() == Color.WHITE).findFirst();
		whiteHolder.ifPresent(board::setCurrentWhiteHolder);

		//Black pieces
		//Populating the board by filling the holes
		final ArrayList<Piece> tempBlackPieces = new ArrayList<>();
		pieces.stream().filter(p -> p.getColor() == Color.WHITE).forEach(p -> tempBlackPieces.add(new Piece(Color.BLACK, p.getX(), (p.getY() == 1 ? 7 : 1))));
		//Merging the two lists
		pieces.addAll(tempBlackPieces);
		//Setting the black ball holder
		final Optional<Piece> blackHolder = pieces.stream().filter(p -> p.getX() == 4 && p.getColor() == Color.BLACK).findFirst();
		blackHolder.ifPresent(board::setCurrentBlackHolder);

		board.setPieces(pieces);
		return board;
	}

}
