package diaballik.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = MoveBall.class),
		@JsonSubTypes.Type(value = MovePiece.class)
})
public interface Action {

	public abstract void execute(Board board);

	public abstract boolean verifyAction(Board b);

	public abstract void redo(Board board);

	public abstract void undo(Board board);

}
