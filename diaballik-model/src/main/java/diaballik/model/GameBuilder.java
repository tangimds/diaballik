package diaballik.model;

public abstract class GameBuilder {

	public abstract Game buildGame(String name1, String name2, int scenario, int difficulty);

	public abstract Game buildGame(int gameId);

}
