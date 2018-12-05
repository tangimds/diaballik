package diaballik.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

	private Game game;

	@BeforeEach
	void setUp() {
		final GameBuilder builder = new PvCGameBuilder();
		game = builder.buildGame("Didier", "Glados", Scenario.STANDARD, Difficulty.STARTING);
	}

	@Test
	void testPlayHuman() {
		MoveBall mb = new MoveBall(game.getBoard().getCurrentWhiteHolder(),game.getBoard().getPiece(1,1));

		final Board b = game.getBoard().copy();
		if(mb.verifyAction(b)) {
			mb.execute(b);
		}

		final boolean played = game.play(mb);

		assertTrue(played);
		assertEquals(b,game.getBoard());

	}

	@Test
	void testPlayAI() {
		game.play(new MoveBall(game.getBoard().getCurrentWhiteHolder(),game.getBoard().getPiece(1,1)));
		game.play(new MovePiece(game.getBoard().getPiece(7,1),7,2));
		game.play(new MovePiece(game.getBoard().getPiece(7,2),6,2));
		final Board oldBoard = game.getBoard().copy();
		final boolean played = game.play();
		assertTrue(played);
		assertNotEquals(oldBoard,game.getBoard());
	}

	@Test
	void testNextAction() {
		//TODO
	}

	@Test
	void testPreviousAction() {
		//TODO
	}

	@Test
	void testSave() {
		//TODO
	}

}