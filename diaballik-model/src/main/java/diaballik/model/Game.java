package diaballik.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game {

	private int nbTurn;
	private Scenario scenario;
	private int id;
	private ArrayList<Action> actions;
	private Board board;
	private Difficulty difficulty;
	private Player p1;
	private Player p2;


	public Game(final Player p1, final Player p2, final Scenario mode) {

		//initialization of the arguments
		this.p1 = p1;
		this.p2 = p2;
		this.scenario = mode;
		this.nbTurn = 0;
		this.actions = new ArrayList<>();

		switch (Scenario.values()[mode.ordinal()]) {
			case STANDARD:
				final StandardBoardBuilder standardBoardBuilder = new StandardBoardBuilder();
				this.board = standardBoardBuilder.buildBoard();
				break;
			case RANDOM:
				final RandomBoardBuilder randomBoardBuilder = new RandomBoardBuilder();
				this.board = randomBoardBuilder.buildBoard();
				break;
			case EAU:
				final EAUBoardBuilder eauBoardBuilder = new EAUBoardBuilder();
				this.board = eauBoardBuilder.buildBoard();
				break;
			default:
				this.board = null;
				break;
		}
	}

	// getters and setters
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

	public Board getBoard() {
		return board;
	}

	public void setTurn(final int t) {
		nbTurn = t;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty= difficulty;
	}

	public void playHuman(Board b) {
		p1.play(b);
	}

	public void playAI(Board b) {
		p2.play(b).execute(b);
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
