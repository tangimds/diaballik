package diaballik.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Objects;

public class Game {

	private int nbTurn;
	private int nbActions;
	private Scenario scenario;
	//private int id;
	private ArrayList<Action> actions;
	private Board board;
	private Player p1;
	private Player p2;

	@JsonCreator
	public Game(@JsonProperty("p1") final Player p1, @JsonProperty("p2") final Player p2, @JsonProperty("scenario") final Scenario scenario) {

		//initialization of the arguments
		this.p1 = p1;
		this.p2 = p2;
		this.scenario = scenario;
		this.nbTurn = 0;
		this.nbActions = 0;
		this.actions = new ArrayList<>();

		switch (scenario) {
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
	@JsonIgnore
	public Player getPlayer1() {
		return p1;
	}

	@JsonIgnore
	public Player getPlayer2() {
		return p2;
	}

	public Scenario getScenario() {
		return scenario;
	}

	@JsonIgnore
	public int getTurn() {
		return nbTurn;
	}

	@JsonIgnore
	public int getNbActions() {
		return actions.size();
	}

	public Board getBoard() {
		return board;
	}

	public void setTurn(final int t) {
		nbTurn = t;
	}

	public void play(final Action a) {
		if ((nbTurn & 1) == 0) {
			actions.add(a);
			a.execute(board);
			nbActions++;
			if (nbActions % 3 == 0) {
				nbTurn++;
			}
		} else if ((nbTurn & 1) != 0) {
			if (p2 instanceof AIPlayer) {
				final Action action = p2.play(board);
				actions.add(action);
				action.execute(board);
				nbActions++;
				if (nbActions % 3 == 0) {
					nbTurn++;
				}
			} else {
				actions.add(a);
				a.execute(board);
				nbActions++;
				if (nbActions % 3 == 0) {
					nbTurn++;
				}
			}
		}
	}

	public void nextAction() {
		//TODO : maj nbTurn
		final Action a = actions.get(nbActions);
		System.out.println("a : " + a);
		a.redo(board);
		nbActions++;
	}

	public void previousAction() {
		//TODO : maj nbTurn
		final Action a = actions.get(nbActions - 1);
		System.out.println("a : " + a);
		a.undo(board);
		nbActions--;
	}

	public void save() {
		//TODO : save

	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Game)) {
			return false;
		}
		final Game game = (Game) o;
		return nbTurn == game.nbTurn &&
				//id == game.id &&
				getScenario() == game.getScenario() &&
				Objects.equals(actions, game.actions) &&
				Objects.equals(getBoard(), game.getBoard()) &&
				Objects.equals(p1, game.p1) &&
				Objects.equals(p2, game.p2);
	}

	@Override
	public int hashCode() {

		//return Objects.hash(nbTurn, getScenario(), id, actions, getBoard(), p1, p2);
		return Objects.hash(nbTurn, getScenario(), actions, getBoard(), p1, p2);
	}

	@Override
	public String toString() {
		return "nbTurn : " + nbTurn + "\n"
				+ "nbActions : " + nbActions + "\n"
				+ ">" + actions.toString() + "\n"
				+ board.toStringColor() + "\n\n";
	}
}
