package diaballik.model;

public class PvPGameBuilder extends GameBuilder {

	@Override
	public Game buildGame(final String name1, final String name2, final Scenario scenario, final Difficulty difficulty) {
		final Player whitePlayer = new HumanPlayer(name1, Color.WHITE);
		final Player blackPlayer = new HumanPlayer(name2, Color.BLACK);

		return new Game(whitePlayer, blackPlayer, scenario);
	}

	@Override
	public Game buildGame(final int gameId) {
		//TODO
		return null;
	}

}
