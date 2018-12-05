package diaballik.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = AIPlayer.class),
		@JsonSubTypes.Type(value = HumanPlayer.class)
})

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public abstract class Player {

	protected String name;
	protected Color color;

	@JsonCreator
	public Player(@JsonProperty("name") final String name, @JsonProperty("color") final Color color) {
		this.name = name;
		this.color = color;
	}

	public abstract Action play(Board board);

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Player)) {
			return false;
		}
		final Player player = (Player) o;
		return Objects.equals(getName(), player.getName()) &&
				getColor() == player.getColor();
	}

	@Override
	public int hashCode() {

		return Objects.hash(getName(), getColor());
	}
}
