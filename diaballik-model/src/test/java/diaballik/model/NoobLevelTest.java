package diaballik.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoobLevelTest {

	private Board board = new Board();

	@Test
	void testChooseActionAuthorized() {
		final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		board = standardBoardBuilder.buildBoard();
		final NoobLevel nb = new NoobLevel();
		assertTrue(nb.chooseAction(board).verifyAction(board));
	}

	@Test
	void testChooseActionType() {
		final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		board = standardBoardBuilder.buildBoard();
		final NoobLevel nb = new NoobLevel();
		assertTrue(nb.chooseAction(board) != null);
	}


}