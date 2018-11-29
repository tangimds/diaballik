package diaballik.model;

import java.util.Collection;

public class Game {

	private int nbTurn;
	private Scenario scenario;
	private int id;
	private Collection<Action> actions;
	private Board board;
	private Level level;
	private Player p1;
	private Player p2;


	public Game(final Player p1, final Player p2, final Scenario mode) {
		this.p1 = p1;
		this.p2 = p2;
		this.scenario = mode;
	}

	// getters et setters
	public Player getPlayer1() {
		return p1;
	}

	public Player getPlayer2() {
		return p2;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public int getTurn() {
		return nbTurn;
	}

	public int getNbActions() {
		return actions.size();
	}

	public void setTurn(final int t) {
		nbTurn = t;
	}

	public void playHuman(Board b) {
		p1.play(b);
	}

	public void playAI(Board b) {
		p2.play(b);
	}

	public Board nextAction(final int numAction) {
		return null;
	}

	public Board previousAction(final int numAction) {
		return null;
	}

	public void save() {

	}

}
