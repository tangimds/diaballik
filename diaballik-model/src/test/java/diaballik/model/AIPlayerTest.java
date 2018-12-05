package diaballik.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIPlayerTest {

	@Test
	void testPlay() {
		final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		Board board = standardBoardBuilder.buildBoard();
		AIPlayer ai = new AIPlayer("ordi", Color.BLACK, Difficulty.NOOB);
		Board oldBoard = board.copy();
		ai.play(board).execute(board);
		assertFalse(oldBoard.equals(board));
	}

}