package diaballik.model;

public class PvCGameBuilder extends GameBuilder {

	@Override
	public Game buildGame(final String name1, final String name2, final int scenario, final int difficulty) {
		final Player hPlayer = new HumanPlayer(name1, Color.WHITE);
		final Scenario scnr = Scenario.values()[scenario];

		final Player aiPlayer = new AIPlayer(name2, Color.BLACK, Difficulty.values()[difficulty]);

		final Game game = new Game(hPlayer, aiPlayer, scnr);
		return game;
	}

	@Override
	public Game buildGame(final int gameId) {
		//TODO
		return null;
	}
}
