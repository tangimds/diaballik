package diaballik.model;

public interface Action {

	public abstract void execute(Board board);

	public abstract boolean verifyAction(Board b);

	public abstract void redo(Board board);

	public abstract void undo(Board board);

}
