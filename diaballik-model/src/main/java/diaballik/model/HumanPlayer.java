package diaballik.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HumanPlayer extends Player {

	@JsonCreator
	public HumanPlayer(@JsonProperty("name") final String name, @JsonProperty("color") final Color color) {
		super(name, color);
	}

	@Override
	public Action play(final Board board) {
		return null;
	}
}
