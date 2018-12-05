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
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Arrays;
import java.util.Objects;


import static java.nio.charset.StandardCharsets.UTF_8;

public class Game {

	private int nbTurn;
	private int nbActions;
	private Scenario scenario;
	private long id;
	private ArrayList<Action> actions;
	private Board board;
	private Player p1;
	private Player p2;
	private String winner;

	@JsonCreator
	public Game(@JsonProperty("p1") final Player p1, @JsonProperty("p2") final Player p2, @JsonProperty("scenario") final Scenario scenario) {

		//initialization of the arguments
		this.winner = "NONE";
		this.p1 = p1;
		this.p2 = p2;
		this.scenario = scenario;
		this.nbTurn = 0;
		this.nbActions = 0;
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

	public long getId() {
		return id;
	}

	public String getWinner() {
		return winner;
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

	public boolean play(final Action a) {
		if (!a.verifyAction(board)) {
			return false;
		}
		if ((nbTurn & 1) == 0 || (p2 instanceof HumanPlayer)) {
			actions.add(a);
			a.execute(board);
			nbActions++;
			if (nbActions % 3 == 0) {
				nbTurn++;
			}
			if (board.win().isPresent()) {
				final Locale l = Locale.ENGLISH;
				this.winner = board.win().get().name().toUpperCase(l);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean play() {
		if ((nbTurn & 1) != 0 && (p2 instanceof AIPlayer)) {
			final Action action = p2.play(board);
			actions.add(action);
			action.execute(board);
			nbActions++;
			if (nbActions % 3 == 0) {
				nbTurn++;
			}
			if (board.win().isPresent()) {
				final Locale l = Locale.ENGLISH;
				this.winner = board.win().get().name().toUpperCase(l);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean nextAction() {
		if (nbActions == actions.size()) {
			return false;
		}
		final Action a = actions.get(nbActions);
		System.out.println("a : " + a);
		a.redo(board);
		if (board.win().isPresent()) {
			final Locale l = Locale.ENGLISH;
			this.winner = board.win().get().name().toUpperCase(l);
		}
		nbActions++;
		if (nbActions % 3 == 0) {
			nbTurn++;
		}
		return true;
	}

	public boolean previousAction() {
		if (nbActions == 0) {
			return false;
		}
		final Action a = actions.get(nbActions - 1);
		System.out.println("a : " + a);
		a.undo(board);
		this.winner = "NONE";
		nbActions--;
		if (nbActions % 3 == 0) {
			nbTurn--;
		}
		return true;
	}

	public void save() throws IOException {
		final ObjectMapper mapper = new DiabalikJacksonProvider().getMapper();
		final String serializedObject = mapper.writeValueAsString(this);
		final String path = System.getProperty("user.home") + System.getProperty("file.separator") + "Diaballik" + System.getProperty("file.separator");
		if (new File(path).mkdir()) {
			System.out.println("Directory created");
		}
		try (PrintWriter out = new PrintWriter(path + "game_" + this.id + ".txt", UTF_8.name())) {
			out.println(serializedObject);
		}
	}

	@JsonIgnore
	public static List<String> getSavedGames() {
		final String path = System.getProperty("user.home") + System.getProperty("file.separator") + "Diaballik" + System.getProperty("file.separator");
		final List<String> results = new ArrayList<>();
		final Optional<File[]> files = Optional.ofNullable(new File(path).listFiles());
		files.ifPresent(files1 -> Arrays.stream(files1).forEach(f -> {
			results.add(f.getName().replaceAll("\\D", ""));
		}));
		return results;
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
