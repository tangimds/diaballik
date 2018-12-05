package diaballik.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MovePieceTest {

	private Board board = new Board();

	@BeforeEach
	void setUp() {
		final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		board = standardBoardBuilder.buildBoard();
	}

	@Test
	void testExecute() {
		Optional<Piece> opt = board.getPieces().stream()
				.filter(p -> p.getX() == 3 && p.getY() == 1)
				.findFirst();

		if (opt.isPresent()) {
			final MovePiece mp = new MovePiece(opt.get(), 3, 2);
			if (mp.verifyAction(board))
				mp.execute(board);
			assertTrue(opt.get().getX() == 3 && opt.get().getY() == 2, "execute");
		}


	}

	@Test
	void testRedo() {
		//TODO : testRedo
	}

	@Test
	void testUndo() {
		//TODO : testUndo
	}

	@Test
	void testVerifyAction() {
		//move la piece (3,1) en (3,2)
		final MovePiece mp = new MovePiece(board.getPiece(3, 1), 3, 2);
		assertTrue(mp.verifyAction(board), "execute");
	}

}