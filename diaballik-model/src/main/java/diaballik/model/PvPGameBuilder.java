package diaballik.model;

public class PvPGameBuilder extends GameBuilder{

	@Override
	public Game buildGame(final String name1, final String name2, final int scenario, final int difficulty) {
		final Player whitePlayer = new HumanPlayer(name1, Color.WHITE);
		final Player blackPlayer = new HumanPlayer(name2, Color.WHITE);
		final Scenario scnr = Scenario.values()[scenario];

		return new Game(whitePlayer,blackPlayer,scnr);
	}

	@Override
	public Game buildGame(int gameId){
		return null;
	}

}
