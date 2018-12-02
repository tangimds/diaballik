package diaballik.model;

public class AIPlayer extends Player {

	private Level level;

	public AIPlayer(final String n, final Color c, final Difficulty difficulty) {
		super(n, c);
		switch (difficulty) {
			case NOOB:
				level = new NoobLevel();
				break;
			case STARTING:
				level = new StartingLevel();
				break;
			case PROGRESSIVE:
				level = new ProgressiveLevel();
				break;
			default:
				level = null;
				break;
		}
	}

	public void setLevel(final Level lvl) {
		level = lvl;
	}

	public Level getLevel() {
		return level;
	}

	@Override
	public Action play(final Board board) {

		return level.chooseAction(board);
	}


}
