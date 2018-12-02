package diaballik.model;

public class PvCGameBuilder extends GameBuilder {

	public Game buildGame(final String name1, final String name2, final int scenario, final int difficulty) {
		final Player hPlayer = new HumanPlayer(name1, Color.WHITE);
		final Scenario scnr = Scenario.values()[scenario];
		final Level lvl;

		final Player aiPlayer = new AIPlayer(name2, Color.BLACK, Difficulty.values()[difficulty]);

		final Game game = new Game(hPlayer, aiPlayer, scnr);
		game.setLevel(lvl);
		return game;
	}

	@Override
	public Game buildGame(int gameId) {
		return null;
	}

}
