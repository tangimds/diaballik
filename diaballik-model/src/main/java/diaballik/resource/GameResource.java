package diaballik.resource;

import diaballik.model.Game;
import io.swagger.annotations.Api;

import javax.inject.Singleton;
import javax.ws.rs.*;
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

	}

	@PUT
	@Path("newGamePVC/{name}/{scenario}/{level}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game newGamePVC(@PathParam("name") final String name,
						   @PathParam("scenario") final String scenario,
						   @PathParam("level") final String level) {

	}

	@PUT
	@Path("loadGame/{idGame}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game loadGame(@PathParam("idGame") final String idGame) {

	}

	@PUT
	@Path("replay/{idGame}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game replay(@PathParam("idGame") final String idGame) {

	}

	@GET
	@Path("/savedGames/")
	@Produces(MediaType.APPLICATION_JSON)
	public Game savedGames(){

	}

	//Requêtes pour sauvegarder et quitter une partie
	//-----------------------------------------------
	@GET
	@Path("/save/")
	@Produces(MediaType.APPLICATION_JSON)
	public Game save(){

	}

	@GET
	@Path("/quit/")
	@Produces(MediaType.APPLICATION_JSON)
	public Game quit(){

	}

	//Requêtes de replay
	@GET
	@Path("/replay/redo/")
	@Produces(MediaType.APPLICATION_JSON)
	public Game redo(){

	}

	@GET
	@Path("/replay/undo")
	@Produces(MediaType.APPLICATION_JSON)
	public Game undo(){

	}

	//Requêtes de déplacement
	@PUT
	@Path("movePiece/{color}/{x1}/{y1}/{x2}/{y2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game movePiece(@PathParam("color") final String color,
						   @PathParam("x1") final String x1,
						   @PathParam("y1") final String y1,
						   @PathParam("x2") final String x2,
						   @PathParam("y2") final String y2) {

	}

	@PUT
	@Path("moveBall/{color}/{x1}/{y1}/{x2}/{y2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game moveBall(@PathParam("color") final String color,
						   @PathParam("x1") final String x1,
						   @PathParam("y1") final String y1,
						   @PathParam("x2") final String x2,
						   @PathParam("y2") final String y2) {

	}
}
