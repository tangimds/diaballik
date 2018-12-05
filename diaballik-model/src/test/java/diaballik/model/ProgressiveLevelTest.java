package diaballik.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgressiveLevelTest {
	@Test
	void testChooseActionNoob() {
		final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		Board board = standardBoardBuilder.buildBoard();
		AIPlayer ai = new AIPlayer("a", Color.BLACK, Difficulty.PROGRESSIVE);
		ai.play(board).execute(board);
		assertTrue(ai.play(board).verifyAction(board));
	}

}