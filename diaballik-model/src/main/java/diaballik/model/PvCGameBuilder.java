package diaballik.model;

public class PvCGameBuilder extends GameBuilder {

	@Override
	public Game buildGame(final String name1, final String name2, final Scenario scenario, final Difficulty difficulty) {
		final Player hPlayer = new HumanPlayer(name1, Color.WHITE);

		final Player aiPlayer = new AIPlayer(name2, Color.BLACK, difficulty);

		final Game game = new Game(hPlayer, aiPlayer, scenario);
		return game;
	}

	@Override
	public Game buildGame(final int gameId) {
		//TODO
		return null;
	}
}
