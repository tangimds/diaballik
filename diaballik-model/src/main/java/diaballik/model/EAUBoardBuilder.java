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
		final ArrayList<Integer> randomVal = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 6, 7));
		Collections.shuffle(randomVal);
		//White pieces
		//Populating the board
		IntStream.rangeClosed(1, 7).forEach(x -> pieces.add(new Piece(Color.WHITE, x, 1)));
		//Transforming 2 white pieces to black
		pieces.stream().filter(p -> ((p.getX() == randomVal.get(0)) || (p.getX() == randomVal.get(1))) && p.getY()==1).forEach(p -> p.setColor(Color.BLACK));
		//Setting the white ball holder
		final Optional<Piece> whiteHolder = pieces.stream().filter(p -> p.getX() == 4 && p.getColor() == Color.WHITE).findFirst();
		whiteHolder.ifPresent(board::setCurrentWhiteHolder);

		//Black pieces
		//Reshuffle the random list
		Collections.shuffle(randomVal);
		//Populating the board
		IntStream.rangeClosed(1, 7).forEach(x -> pieces.add(new Piece(Color.BLACK, x, 7)));
		//Transforming 2 black pieces to white
		pieces.stream().filter(p -> ((p.getX() == randomVal.get(0)) || (p.getX() == randomVal.get(1))) && p.getY()==7).forEach(p -> p.setColor(Color.WHITE));
		//Setting the black ball holder
		final Optional<Piece> blackHolder = pieces.stream().filter(p -> p.getX() == 4 && p.getColor() == Color.BLACK).findFirst();
		blackHolder.ifPresent(board::setCurrentBlackHolder);

		board.setPieces(pieces);
		return board;
	}

}
