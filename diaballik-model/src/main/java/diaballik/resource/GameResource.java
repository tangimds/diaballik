package diaballik.resource;

import diaballik.model.Game;
import diaballik.model.Board;
import diaballik.model.Piece;
import diaballik.model.Action;
import diaballik.model.Difficulty;
import diaballik.model.GameBuilder;
import diaballik.model.PvPGameBuilder;
import diaballik.model.SavedGameBuilder;
import diaballik.model.PvCGameBuilder;
import diaballik.model.Scenario;
import diaballik.model.MovePiece;
import diaballik.model.MoveBall;
import io.swagger.annotations.Api;

import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Singleton
@Path("game")
@Api(value = "game", description = "Operations on Diabalik")
public class GameResource {
	private Optional<Game> game;

	public GameResource() {
		super();
		game = Optional.empty();
	}

	//Requêtes de construction de game
	//--------------------------------
	@PUT
	@Path("newGamePVP/{name1}/{name2}/{scenario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game newGamePVP(@PathParam("name1") final String name1,
						   @PathParam("name2") final String name2,
						   @PathParam("scenario") final String scenario) {
		final GameBuilder builder = new PvPGameBuilder();
		final Game game = builder.buildGame(name1, name2, Scenario.valueOf(scenario), null);
		this.game = Optional.of(game);
		return game;
	}

	@PUT
	@Path("newGamePVC/{name}/{scenario}/{difficulty}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game newGamePVC(@PathParam("name") final String name,
						   @PathParam("scenario") final String scenario,
						   @PathParam("difficulty") final String difficulty) {
		final GameBuilder builder = new PvCGameBuilder();
		final Game game = builder.buildGame(name, null, Scenario.valueOf(scenario), Difficulty.valueOf(difficulty));
		this.game = Optional.of(game);
		return game;
	}

	@PUT
	@Path("loadGame/{idGame}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response loadGame(@PathParam("idGame") final String idGame) {
		final GameBuilder builder = new SavedGameBuilder();
		final Game game = builder.buildGame(idGame);
		this.game = Optional.ofNullable(game);
		if (this.game.isPresent()) {
			return Response.ok(this.game.get()).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"La partie n'existe pas !\"}").build();
		}
	}

	@GET
	@Path("/savedGames/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response savedGames() {
		final JsonObjectBuilder[] response = {Json.createObjectBuilder()};
		final List<String> games = Game.getSavedGames();
		IntStream.rangeClosed(0, games.size() - 1).forEach(id -> {
			response[0] = response[0].add("id" + id, games.get(id));
		});
		/*Game.getSavedGames().forEach(s -> {
			int i = 0;
			response.add(""+ i++, s);
			System.out.println(s);
			System.out.println(i);
		});*/
		//System.out.println(response.build().toString());
		return Response.ok(response[0].build().toString()).build();
	}

	//Requêtes pour sauvegarder et quitter une partie
	//-----------------------------------------------
	@GET
	@Path("/save/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response save() {
		if (this.game.isPresent()) {
			try {
				this.game.get().save();
				return Response.ok().build();
			} catch (IOException e) {
				e.printStackTrace();
				return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Erreur lors de la sauvegarde de la partie !\"}").build();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Aucune partie en cours !\"}").build();

	}

	@GET
	@Path("/quit/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response quit() {
		this.game = Optional.empty();
		return Response.ok().build();
	}

	//Requêtes de replay
	@GET
	@Path("/replay/redo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response redo() {
		if (this.game.isPresent()) {
			if (this.game.get().nextAction()) {
				return Response.ok(this.game.get()).build();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@GET
	@Path("/replay/undo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response undo() {
		if (this.game.isPresent()) {
			if (this.game.get().previousAction()) {
				return Response.ok(this.game.get()).build();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	//Requêtes de déplacement
	@PUT
	@Path("movePiece/{x1}/{y1}/{x2}/{y2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response movePiece(@PathParam("x1") final String x1,
							  @PathParam("y1") final String y1,
							  @PathParam("x2") final String x2,
							  @PathParam("y2") final String y2) {
		if (game.isPresent()) {
			final Board board = game.get().getBoard();
			final Piece p = board.getPiece(Integer.parseInt(x1), Integer.parseInt(y1));
			final Action movePiece = new MovePiece(p, Integer.parseInt(x2), Integer.parseInt(y2));
			if (this.game.get().play(movePiece)) {
				return Response.ok(this.game.get()).build();
			}
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Coup non autorisé\"}").build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("moveBall/{x1}/{y1}/{x2}/{y2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response moveBall(@PathParam("x1") final String x1,
							 @PathParam("y1") final String y1,
							 @PathParam("x2") final String x2,
							 @PathParam("y2") final String y2) {
		if (game.isPresent()) {
			final Board board = game.get().getBoard();
			final Piece startingP = board.getPiece(Integer.parseInt(x1), Integer.parseInt(y1));
			final Piece endingP = board.getPiece(Integer.parseInt(x2), Integer.parseInt(y2));
			final Action moveBall = new MoveBall(startingP, endingP);
			if (this.game.get().play(moveBall)) {
				return Response.ok(this.game.get()).build();
			}
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Coup non autorisé\"}").build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@GET
	@Path("IAPlay")
	@Produces(MediaType.APPLICATION_JSON)
	public Response iaPlay() {
		if (game.isPresent()) {
			if (game.get().play()) {
				return Response.ok(this.game.get()).build();
			}
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Coup non autorisé\"}").build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
