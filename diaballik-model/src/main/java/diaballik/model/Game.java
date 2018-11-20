package diaballik.model;

import java.util.Collection;

public class Game {

	private int nbTurn;

	private Scenario scenario;

	private int id;

	private Collection<Action> actions;

	public Game(Player p1, Player p2, Scenario mode) {

	}

	public Game(Player p1, Level lvl, Scenario mode) {

	}

	public Player getPlayer1() {
		return null;
	}

	public Player getPlayer2() {
		return null;
	}

	public Scenario getScenario() {
		return null;
	}

	public int getTurn() {
		return 0;
	}

	public int getNbActions() {
		return 0;
	}

	public void setTurn() {

	}

	public void playHuman() {

	}

	public void playAI() {

	}

	public Board nextAction(int numAction) {
		return null;
	}

	public Board previousAction(int numAction) {
		return null;
	}

	public void save() {

	}

}
