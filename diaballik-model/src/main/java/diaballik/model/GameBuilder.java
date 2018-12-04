package diaballik.model;

public abstract class GameBuilder {

	public abstract Game buildGame(String name1, String name2, Scenario scenario, Difficulty difficulty);

	public abstract Game buildGame(int gameId);

}
