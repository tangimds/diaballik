package diaballik.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MoveBallTest {


	private ArrayList<Piece> whitePieces = new ArrayList<>();
	private ArrayList<Piece> blackPieces = new ArrayList<>();
	private Board board = new Board();

	@BeforeEach
	void setUp() {
		whitePieces.add(new Piece(Color.WHITE, 2, 2));
		whitePieces.add(new Piece(Color.WHITE, 4, 2));
		whitePieces.add(new Piece(Color.WHITE, 7, 2));
		whitePieces.add(new Piece(Color.WHITE, 3, 4));
		whitePieces.add(new Piece(Color.WHITE, 4, 4));
		whitePieces.add(new Piece(Color.WHITE, 7, 5));
		whitePieces.add(new Piece(Color.WHITE, 4, 6));

		blackPieces.add(new Piece(Color.BLACK, 1, 3));
		blackPieces.add(new Piece(Color.BLACK, 3, 5));
		blackPieces.add(new Piece(Color.BLACK, 6, 5));
		blackPieces.add(new Piece(Color.BLACK, 2, 6));
		blackPieces.add(new Piece(Color.BLACK, 3, 6));
		blackPieces.add(new Piece(Color.BLACK, 3, 7));
		blackPieces.add(new Piece(Color.BLACK, 5, 7));

		whitePieces.forEach(board::addPiece);
		blackPieces.forEach(board::addPiece);

		Optional<Piece> wh = whitePieces.stream()
				.filter(p -> p.getX() == 4 && p.getY() == 2)
				.findFirst();
		wh.ifPresent(board::setCurrentWhiteHolder);

		Optional<Piece> bh = blackPieces.stream()
				.filter(p -> p.getX() == 3 && p.getY() == 5)
				.findFirst();
		bh.ifPresent(board::setCurrentBlackHolder);
	}

	@Test
	void testSetStartingPiece() {
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentBlackHolder());
		assertTrue(mb.getStartingPiece() != null);
	}

	@Test
	void testSetEndingPiece() {
		MoveBall mb = new MoveBall(null, null);
		mb.setEndingPiece(board.getCurrentBlackHolder());
		assertTrue(mb.getEndingPiece() != null);
	}

	@Test
	void testVerifyAction() {
	}

	@Test
	void testEquals() {
	}

	@Test
	void testHashCode() {
	}

	@Test
	void testExecute() {
		Optional<Piece> dest = blackPieces.stream()
				.filter(p -> p.getX() == 3 && p.getY() == 7)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentBlackHolder());
		dest.ifPresent(mb::setEndingPiece);
		if (mb.verifyAction(board))
			mb.execute(board);
		//dest.ifPresent(piece -> assertTrue(board.getCurrentBlackHolder().equals(piece)));
		assertTrue(board.getCurrentBlackHolder().equals(dest.get()));
	}

	@Test
	void testAboveFriend() {
		Optional<Piece> dest = blackPieces.stream()
				.filter(p -> p.getX() == 3 && p.getY() == 7)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentBlackHolder());
		dest.ifPresent(mb::setEndingPiece);
		assertTrue(mb.verifyAction(board), "above a friend");
	}

	@Test
	void testEnemyBehind() {
		Optional<Piece> dest = blackPieces.stream()
				.filter(p -> p.getX() == 6 && p.getY() == 5)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentBlackHolder());
		dest.ifPresent(mb::setEndingPiece);
		assertTrue(mb.verifyAction(board), "enenmy behind");
	}

	@Test
	void testDiagonal() {
		Optional<Piece> dest = blackPieces.stream()
				.filter(p -> p.getX() == 1 && p.getY() == 3)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentBlackHolder());
		dest.ifPresent(mb::setEndingPiece);
		assertTrue(mb.verifyAction(board), "diagonal");
	}

	@Test
	void testEnemyBetween() {
		Optional<Piece> dest = blackPieces.stream()
				.filter(p -> p.getX() == 5 && p.getY() == 7)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentBlackHolder());
		dest.ifPresent(mb::setEndingPiece);
		assertFalse(mb.verifyAction(board), "enemy between");
	}

	@Test
	void testToEnemy() {
		Optional<Piece> dest = whitePieces.stream()
				.filter(p -> p.getX() == 4 && p.getY() == 4)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentBlackHolder());
		dest.ifPresent(mb::setEndingPiece);
		assertFalse(mb.verifyAction(board), "to enemy");
	}

	@Test
	void testNoBall() {
		Optional<Piece> dest = blackPieces.stream()
				.filter(p -> p.getX() == 2 && p.getY() == 6)
				.findFirst();
		Optional<Piece> start = blackPieces.stream()
				.filter(p -> p.getX() == 3 && p.getY() == 6)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		start.ifPresent(mb::setStartingPiece);
		dest.ifPresent(mb::setEndingPiece);
		assertFalse(mb.verifyAction(board), "no ball");
	}

	@Test
	void testToLeft() {
		Optional<Piece> dest = whitePieces.stream()
				.filter(p -> p.getX() == 2 && p.getY() == 2)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentWhiteHolder());
		dest.ifPresent(mb::setEndingPiece);
		assertTrue(mb.verifyAction(board), "to left");
	}

	@Test
	void testUnauthorized() {
		Optional<Piece> dest = whitePieces.stream()
				.filter(p -> p.getX() == 3 && p.getY() == 4)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentWhiteHolder());
		dest.ifPresent(mb::setEndingPiece);
		assertFalse(mb.verifyAction(board), "unauthorized");
	}

	@Test
	void testToUp() {
		Optional<Piece> dest = whitePieces.stream()
				.filter(p -> p.getX() == 4 && p.getY() == 4)
				.findFirst();
		MoveBall mb = new MoveBall(null, null);
		mb.setStartingPiece(board.getCurrentWhiteHolder());
		dest.ifPresent(mb::setEndingPiece);
		assertTrue(mb.verifyAction(board), "to up");
	}

	@Test
	void testRedo() {
		final GameBuilder builder = new PvCGameBuilder();
		final Game game = builder.buildGame("Taha", "Glados", Scenario.STANDARD, Difficulty.STARTING);
		game.play(new MoveBall(game.getBoard().getCurrentWhiteHolder(), game.getBoard().getPiece(2,1)));
		game.play(new MoveBall(game.getBoard().getCurrentWhiteHolder(), game.getBoard().getPiece(7,1)));

		final Board oldBoard = game.getBoard().copy();

		//annule la derniere action
		game.previousAction();
		assertNotEquals(oldBoard,game.getBoard(),"old et nouveau board ne sont differents");
		//refait l'action
		game.nextAction();
		assertEquals(game.getBoard(),oldBoard,"testRedo");
	}

	@Test
	void testUndo() {
		final GameBuilder builder = new PvCGameBuilder();
		final Game game = builder.buildGame("Taha", "Glados", Scenario.STANDARD, Difficulty.STARTING);
		game.play(new MoveBall(game.getBoard().getCurrentWhiteHolder(), game.getBoard().getPiece(2,1)));
		final Board oldBoard = game.getBoard().copy();
		game.play(new MoveBall(game.getBoard().getCurrentWhiteHolder(), game.getBoard().getPiece(7,1)));

		//annule la derniere action
		game.previousAction();

		assertEquals(game.getBoard(),oldBoard,"testUndo");
	}

}