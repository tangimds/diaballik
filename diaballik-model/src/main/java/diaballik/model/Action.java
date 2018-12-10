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

	public abstract Color getColor(); //TODO
	/*
	moveBall et MovePiece auront leur propre getColor, qui retournera la couleur des pions liés à la commmande
	cette couleur pourra alors être vérifiée lors du Game.play(Action a)
	cela sert à verifier que le joueur bouge bien SES pions
	 */

}
