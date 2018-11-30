package diaballik.model;

public class AIPlayer extends Player {

	private Level level;

	public AIPlayer(final String n, final Color c, final Level lvl) {
		super(n, c);
		this.level = lvl;
	}

	public AIPlayer setLevel(final Level lvl) {
		return null;
	}

	public Level getLevel() {
		return level;
	}

	@Override
	public Action play(final Board board) {
		return null;
	}


}
