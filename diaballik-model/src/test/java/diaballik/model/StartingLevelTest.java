package diaballik.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartingLevelTest {

	@Test
	void testChooseAction() {
		final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		Board board = standardBoardBuilder.buildBoard();
		final StartingLevel st = new StartingLevel();
		assertTrue(st.chooseAction(board).verifyAction(board));
	}

}