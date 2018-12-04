package diaballik.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		board.addPiece(new Piece(Color.WHITE,1,1));
		assertFalse(board.getPieces().isEmpty(),"AddPiece");
	}

	@Test
	void testWin() {


	}

}