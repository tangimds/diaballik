package diaballik.model;

public class PvCGameBuilder extends GameBuilder{

	public Game buildGame(final String name1, final String name2, final int scenario, final int difficulty) {
		final Player hPlayer = new HumanPlayer(name1, Color.WHITE);
		final Scenario scnr = Scenario.values()[scenario];
		final Level lvl;
		switch (Difficulty.values()[difficulty]){
			case NOOB:
				lvl = new NoobLevel();
				break;
			case STARTING:
				lvl = new StartingLevel();
				break;
			case PROGRESSIVE:
				lvl = new ProgressiveLevel();
				break;
			default:
				lvl = null;
				break;
		}

		final Player aiPlayer = new AIPlayer(name2, Color.BLACK, lvl);

		return new Game(hPlayer,aiPlayer,scnr);
	}

	@Override
	public Game buildGame(int gameId){
		return null;
	}

}
