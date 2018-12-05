package diaballik.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

	private Board board;
	private ArrayList<Piece> pi;

	@BeforeEach
	void setUp() {
		board = new Board();
		board.addPiece(new Piece(Color.WHITE,1,2));
		board.addPiece(new Piece(Color.WHITE,2,2));
		board.addPiece(new Piece(Color.WHITE,3,2));
		board.addPiece(new Piece(Color.BLACK,3,7));


		pi = new ArrayList<>();
		pi.add(new Piece(Color.WHITE,1,2));
		pi.add(new Piece(Color.WHITE,2,2));
		pi.add(new Piece(Color.WHITE,3,2));
		pi.add(new Piece(Color.BLACK,3,7));
	}

	@Test
	void testGetPieces() {
		assertEquals(pi,board.getPieces());
	}

	@Test
	void testSetPieces() {
		Board newboard = new Board();
		ArrayList<Piece> p = new ArrayList<>();
		p.add(new Piece(Color.WHITE,1,2));
		newboard.setPieces(p);
		assertFalse(newboard.getPieces().isEmpty());
	}

	@Test
	void testSetCurrentWhiteHolder() {
	}

	@Test
	void testSetCurrentBlackHolder() {
	}

	@Test
	void testGetCurrentWhiteHolder() {
	}

	@Test
	void testGetCurrentBlackHolder() {
	}

	@Test
	void testGetPiece() {
		assertEquals(board.getPiece(1,2),new Piece(Color.WHITE,1,2));
	}

	@Test
	void testGetBall() {

	}

	@Test
	void testCopy() {
		Board b2 = board.copy();
		assertEquals(board.getPieces(), b2.getPieces());
	}

	@Test
	void testEquals() {
	}

	@Test
	void testHashCode() {
	}



	//TODO : Faire tous les tests

	@Test
	void testAddPiece() {
		Board board = new Board();
		board.addPiece(new Piece(Color.WHITE, 1, 1));
		assertFalse(board.getPieces().isEmpty(), "AddPiece");
	}

	@Test
	void testWin() {
		Board board = new Board();
		board.addPiece(new Piece(Color.WHITE, 1, 7));
		Optional<Piece> optP = board.getPieces().stream()
				.filter(p -> p.getX() == 1 && p.getY() == 7).findFirst();
		optP.ifPresent(board::setCurrentWhiteHolder);

		board.setCurrentBlackHolder(new Piece(Color.BLACK, 7, 7));

		if (board.win().isPresent()) {
			assertEquals(Color.WHITE, board.win().get(), "win");
		}
	}

}