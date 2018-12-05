package diaballik.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIPlayerTest {

	private AIPlayer ai;
	@BeforeEach
	void setUp() {
		ai = new AIPlayer("ordi",Color.BLACK,Difficulty.NOOB);
	}

	@Test
	void testSetLevel() {
		assertTrue(ai.getLevel() instanceof NoobLevel);
		ai.setLevel(new ProgressiveLevel());
		assertTrue(ai.getLevel() instanceof ProgressiveLevel);
	}

	@Test
	void testGetLevel() {
		assertTrue(ai.getLevel() instanceof NoobLevel);
	}

	@Test
	void testEquals() {
		final AIPlayer ai2 = new AIPlayer("ordi2",Color.WHITE,Difficulty.NOOB);
		final AIPlayer ai3 = new AIPlayer("ordi",Color.BLACK,Difficulty.NOOB);
		assertTrue(ai3.equals(ai));
		assertFalse(ai2.equals(ai));
	}

	@Test
	void testHashCode() {
		final AIPlayer ai3 = new AIPlayer("ordi",Color.BLACK,Difficulty.NOOB);
		assertEquals(ai.hashCode(),ai3.hashCode());
	}

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