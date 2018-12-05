package diaballik.resource;

import diaballik.model.Game;
import io.swagger.annotations.Api;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

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
	@Path("newGamePVC/{name}/{scenario}/{level}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game newGamePVC(@PathParam("name") final String name,
						   @PathParam("scenario") final String scenario,
						   @PathParam("level") final String level) {
		return null;
	}

	@PUT
	@Path("loadGame/{idGame}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game loadGame(@PathParam("idGame") final String idGame) {
		final GameBuilder builder = new SavedGameBuilder();
		final Game game = builder.buildGame(idGame);
		this.game = Optional.of(game);
		return null;
	}

	@PUT
	@Path("replay/{idGame}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game replay(@PathParam("idGame") final String idGame) {
		return null;
	}

	@GET
	@Path("/savedGames/")
	@Produces(MediaType.APPLICATION_JSON)
	public Game savedGames() {
		return null;
	}

	//Requêtes pour sauvegarder et quitter une partie
	//-----------------------------------------------
	@GET
	@Path("/save/")
	@Produces(MediaType.APPLICATION_JSON)
	public Game save() {
		return null;
	}

	@GET
	@Path("/quit/")
	@Produces(MediaType.APPLICATION_JSON)
	public Game quit() {
		return null;
	}

	//Requêtes de replay
	@GET
	@Path("/replay/redo/")
	@Produces(MediaType.APPLICATION_JSON)
	public Game redo() {
		return null;
	}

	@GET
	@Path("/replay/undo")
	@Produces(MediaType.APPLICATION_JSON)
	public Game undo() {
		return null;
	}

	//Requêtes de déplacement
	@PUT
	@Path("movePiece/{x1}/{y1}/{x2}/{y2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game movePiece(@PathParam("x1") final String x1,
						  @PathParam("y1") final String y1,
						  @PathParam("x2") final String x2,
						  @PathParam("y2") final String y2) {
		if(game.isPresent()){
			final Board board = game.get().getBoard();
			final Piece p = board.getPiece(Integer.valueOf(x1), Integer.valueOf(y1));
			final Action movePiece = new MovePiece(p, Integer.valueOf(x2), Integer.valueOf(y2));
			if(movePiece.verifyAction(board)) {
				movePiece.execute(board);
				return game.get();
			}
		}
		return null;
	}

	@PUT
	@Path("moveBall/{x1}/{y1}/{x2}/{y2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game moveBall(@PathParam("x1") final String x1,
						 @PathParam("y1") final String y1,
						 @PathParam("x2") final String x2,
						 @PathParam("y2") final String y2) {
		if(game.isPresent()){
			final Board board = game.get().getBoard();
			final Piece startingP = board.getPiece(Integer.valueOf(x1), Integer.valueOf(y1));
			final Piece endingP = board.getPiece(Integer.valueOf(x2), Integer.valueOf(y2));
			final Action moveBall = new MoveBall(startingP, endingP);
			if(moveBall.verifyAction(board)) {
				moveBall.execute(board);
				return game.get();
			}
		}
		return null;
	}

	@GET
	@Path("IAPlay")
	@Produces(MediaType.APPLICATION_JSON)
	public Game iaPlay() {
		if(game.isPresent()){
			game.get().playAI();
			return game.get();
		}
		return null;
	}
}
