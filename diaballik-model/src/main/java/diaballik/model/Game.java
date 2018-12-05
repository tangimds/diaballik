package diaballik.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import diaballik.serialization.DiabalikJacksonProvider;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

public class Game {

	private int nbTurn;
	private Scenario scenario;
	private long id;
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
		this.actions = new ArrayList<>();
		this.id = System.currentTimeMillis();

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


	public void playHuman(final Board b) {
		p1.play(b);
	}

	public void playAI() {
		p2.play(board).execute(board);
	}

	public Board nextAction(final int numAction) {
		return null;
	}

	public Board previousAction(final int numAction) {
		return null;
	}

	public void save() throws IOException {
		final ObjectMapper mapper = new DiabalikJacksonProvider().getMapper();
		final String serializedObject = mapper.writeValueAsString(this);
		final String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "Diaballik" + System.getProperty("file.separator");
		new File(path).mkdir();
		try (PrintWriter out = new PrintWriter(	path + "game_"+this.id+".txt")) {
			out.println(serializedObject);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Game)) {
			return false;
		}
		Game game = (Game) o;
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
}
