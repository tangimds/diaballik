package diaballik;


import diaballik.model.Board;
import diaballik.model.Color;
import diaballik.model.Difficulty;
import diaballik.model.EAUBoardBuilder;
import diaballik.model.Game;
import diaballik.model.GameBuilder;
import diaballik.model.MoveBall;
import diaballik.model.MovePiece;
import diaballik.model.Piece;
import diaballik.model.PvCGameBuilder;
import diaballik.model.RandomBoardBuilder;
import diaballik.model.Scenario;
import diaballik.model.StandardBoardBuilder;

import java.util.Optional;
import java.util.stream.IntStream;

public class Test {

	public void testBoard() {
		System.out.println("***** Board *****");

		final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		final Board boardStandard = standardBoardBuilder.buildBoard();
		System.out.println("Standard\n" + boardStandard.toStringColor());

		final RandomBoardBuilder randomBoardBuilder = new RandomBoardBuilder();
		final Board boardRandom = randomBoardBuilder.buildBoard();
		System.out.println("Random\n" + boardRandom.toString());

		final EAUBoardBuilder eauBoardBuilder = new EAUBoardBuilder();
		final Board boardEau = eauBoardBuilder.buildBoard();
		System.out.println("Ennemy among us\n" + boardEau.toString());
	}

	public void testMoveBall() {
		System.out.println("***** MoveBall *****");

		// testing the isBetween function
		final Piece p1 = new Piece(Color.WHITE, 5, 4);
		final Piece p2 = new Piece(Color.WHITE, 3, 2);
		final Piece p3 = new Piece(Color.WHITE, 7, 6);
		final Piece p4 = new Piece(Color.WHITE, 7, 3);
		final Piece p5 = new Piece(Color.WHITE, 2, 4);
		final Piece p6 = new Piece(Color.WHITE, 3, 4);
		final Piece p7 = new Piece(Color.WHITE, 3, 6);
		final Piece p8 = new Piece(Color.WHITE, 4, 5);

		final MoveBall mb = new MoveBall(p1, p2);
		System.out.println("**** true ****");
		System.out.println(">" + p1.isBetween(p2, p3));
		System.out.println(">" + p1.isBetween(p3, p2));
		System.out.println(">" + p8.isBetween(p7, p1));
		System.out.println(">" + p6.isBetween(p5, p1));
		System.out.println(">" + p6.isBetween(p2, p7));

		System.out.println("**** false **** ");
		System.out.println(">" + p1.isBetween(p8, p7));
		System.out.println(">" + p5.isBetween(p6, p1));
		System.out.println(">" + p1.isBetween(p4, p7));


		// board situation after many turns
		final Piece p1W = new Piece(Color.WHITE, 2, 2);
		final Piece p2W = new Piece(Color.WHITE, 4, 2);
		final Piece p3W = new Piece(Color.WHITE, 7, 2);
		final Piece p4W = new Piece(Color.WHITE, 3, 4);
		final Piece p5W = new Piece(Color.WHITE, 4, 4);
		final Piece p6W = new Piece(Color.WHITE, 7, 5);
		final Piece p7W = new Piece(Color.WHITE, 4, 6);

		final Piece p1B = new Piece(Color.BLACK, 1, 3);
		final Piece p2B = new Piece(Color.BLACK, 3, 5);
		final Piece p3B = new Piece(Color.BLACK, 6, 5);
		final Piece p4B = new Piece(Color.BLACK, 2, 6);
		final Piece p5B = new Piece(Color.BLACK, 3, 6);
		final Piece p6B = new Piece(Color.BLACK, 3, 7);
		final Piece p7B = new Piece(Color.BLACK, 5, 7);

		final Board board = new Board();
		board.addPiece(p1W);
		board.addPiece(p2W);
		board.addPiece(p3W);
		board.addPiece(p4W);
		board.addPiece(p5W);
		board.addPiece(p6W);
		board.addPiece(p7W);

		board.addPiece(p1B);
		board.addPiece(p2B);
		board.addPiece(p3B);
		board.addPiece(p4B);
		board.addPiece(p5B);
		board.addPiece(p6B);
		board.addPiece(p7B);

		board.setCurrentBlackHolder(p2B);
		board.setCurrentWhiteHolder(p2W);

		System.out.println(board.toStringColor());

		final MoveBall mbAboveFriend = new MoveBall(p2B, p6B);
		final MoveBall mbEnemyBehind = new MoveBall(p2B, p3B);
		final MoveBall mbDiag = new MoveBall(p2B, p1B);
		final MoveBall mbEnemyBetween = new MoveBall(p2B, p7B);
		final MoveBall mbToEnemy = new MoveBall(p2B, p5W);

		final MoveBall mbleft = new MoveBall(p2W, p1W);
		final MoveBall mbUnauthorized = new MoveBall(p2W, p4W);
		final MoveBall mbNoBall = new MoveBall(p5B, p4B);
		final MoveBall mbUp = new MoveBall(p2W, p5W);

		System.out.println("Test valid pass (true expected) : ");
		System.out.println("above a friend : " + mbAboveFriend.verifyAction(board));
		System.out.println("enemy behind : " + mbEnemyBehind.verifyAction(board));
		System.out.println("diag : " + mbDiag.verifyAction(board));
		System.out.println("left : " + mbleft.verifyAction(board));
		System.out.println("up : " + mbUp.verifyAction(board));
		System.out.println("\n");
		System.out.println("Test invalid pass (false expected): ");
		System.out.println("enemy between : " + mbEnemyBetween.verifyAction(board));
		System.out.println("to enemy : " + mbToEnemy.verifyAction(board));
		System.out.println("unauthorized : " + mbUnauthorized.verifyAction(board));
		System.out.println("no ball : " + mbNoBall.verifyAction(board));

	}

	public void testMovePiece() {
		System.out.println("***** MovePiece *****");

		final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
		final Board board = standardBoardBuilder.buildBoard();

		//move la piece (3,1) en (3,2)
		final MovePiece mp = new MovePiece(board.getPiece(3, 1), 3, 2);
		System.out.println("dx : " + mp.getDx());
		System.out.println("dy : " + mp.getDy());
		System.out.println("autorise : " + mp.verifyAction(board));
	}

	public void testNoobLevel() {
		final GameBuilder builder = new PvCGameBuilder();
		final Game game = builder.buildGame("Taha", "Glados", Scenario.STANDARD, Difficulty.NOOB);
		System.out.println(game.getBoard().toStringColor());

		IntStream.rangeClosed(0, 50).forEach(i -> {
			System.out.println("----------------");
			game.play();
			System.out.println(game.getBoard().toStringColor());
		});
	}

	public void testStartingLevel() {
		final GameBuilder builder = new PvCGameBuilder();
		final Game game = builder.buildGame("Taha", "Glados", Scenario.STANDARD, Difficulty.STARTING);
		System.out.println(game.getBoard().toStringColor());

		IntStream.rangeClosed(0, 200).forEach(i -> {
			System.out.println("----------------" + i);
			game.play();
			System.out.println(game.getBoard().toStringColor());
		});
	}


	public void testProgressiveLevel() {
		final GameBuilder builder = new PvCGameBuilder();
		final Game game = builder.buildGame("Taha", "Glados", Scenario.STANDARD, Difficulty.PROGRESSIVE);
		System.out.println(game.getBoard().toStringColor());

		IntStream.rangeClosed(0, 50).forEach(i -> {
			System.out.println("----------------");
			game.play();
			System.out.println(game.getBoard().toStringColor());
		});
	}

	public void testUndoRedo() {
		final GameBuilder builder = new PvCGameBuilder();
		final Game game = builder.buildGame("Taha", "Glados", Scenario.STANDARD, Difficulty.STARTING);
		IntStream.rangeClosed(0, 5).forEach(i -> {
			System.out.println("\n\n------- coup " + i + " -------");
			if (game.getTurn() % 2 == 0) {
				final Optional<Piece> pieceOptional = game.getBoard().getPieces().stream()
						.filter(p -> p.getX() == game.getBoard().getCurrentWhiteHolder().getX() + 1 && p.getY() == game.getBoard().getCurrentWhiteHolder().getY())
						.findFirst();
				game.play(new MoveBall(game.getBoard().getCurrentWhiteHolder(), pieceOptional.get()));
			} else {
				game.play();
			}
			System.out.println(game.toString());

		});

		game.previousAction();
		System.out.println(game.toString());

		game.previousAction();
		System.out.println(game.toString());

		game.previousAction();
		System.out.println(game.toString());

		game.nextAction();
		System.out.println(game.toString());

		game.nextAction();
		System.out.println(game.toString());
	}

	public static void main(final String[] args) {
		final Test test = new Test();
		System.out.println("************** TEST **************\n");
		//test.testBoard();
		//test.testMoveBall();
		//test.testMovePiece();
		test.testUndoRedo();

	}
}
