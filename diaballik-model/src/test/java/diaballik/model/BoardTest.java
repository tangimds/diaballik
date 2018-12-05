package diaballik.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
	@BeforeEach
	void setUp() {
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