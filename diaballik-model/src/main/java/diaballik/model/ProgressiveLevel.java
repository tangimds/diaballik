package diaballik.model;

public class ProgressiveLevel implements Level {

	private final Level noob;
	private final Level starting;
	private int tour;

	public ProgressiveLevel() {
		this.noob = new NoobLevel();
		this.starting = new StartingLevel();
		tour = 0;
	}

	@Override
	public Action chooseAction(final Board board) {
		if (tour++ / 3 < 5) {
			return this.noob.chooseAction(board);
		} else {
			return this.starting.chooseAction(board);
		}
	}
}
