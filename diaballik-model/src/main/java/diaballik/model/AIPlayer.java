package diaballik.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AIPlayer extends Player {

	private Difficulty difficulty;

	@JsonIgnore
	private Level level;

	@JsonCreator
	public AIPlayer(@JsonProperty("name") final String name, @JsonProperty("color") final Color color, @JsonProperty("difficulty") final Difficulty difficulty) {
		super(name, color);
		this.difficulty = difficulty;
		switch (this.difficulty) {
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

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AIPlayer)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		final AIPlayer aiPlayer = (AIPlayer) o;
		return difficulty == aiPlayer.difficulty;
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), difficulty);
	}
}
