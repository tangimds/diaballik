package diaballik.resource;

import com.github.hanleyt.JerseyExtension;
import diaballik.model.*;
import diaballik.serialization.DiabalikJacksonProvider;

import java.net.URI;
import java.util.stream.IntStream;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestGameResource {
	@SuppressWarnings("unused")
	@RegisterExtension
	JerseyExtension jerseyExtension = new JerseyExtension(this::configureJersey);

	Game game;

	private Application configureJersey() {
		return new ResourceConfig(). //
				register(GameResource.class).
				register(MyExceptionMapper.class).
				register(JacksonFeature.class).
				register(DiabalikJacksonProvider.class).
				property("jersey.config.server.tracing.type", "ALL");
	}

	@BeforeEach
	void setUp() {
		PvCGameBuilder pvc = new PvCGameBuilder();
		game = pvc.buildGame("Tangi", "Taha", Scenario.STANDARD, Difficulty.NOOB);
	}

	@Test
	void testTemplate(final Client client, final URI baseUri) {
		client.register(JacksonFeature.class).register(DiabalikJacksonProvider.class);

        /*final Response res = client.
				target(baseUri).
                path("foo/bar").
                request().
                put(Entity.text(""));*/
	}

	@Test
	void testCreateGame(final Client client, final URI baseUri) {
		client.register(JacksonFeature.class).register(DiabalikJacksonProvider.class);
		final Response res = client.target(baseUri).path("game/newGamePVC/Tangi/STANDARD/NOOB").request().put(Entity.text(""));
		final Game g = res.readEntity(Game.class);
		IntStream.rangeClosed(1, 7).forEach(i -> {
			assertEquals(Color.WHITE, g.getBoard().getPiece(i, 1).getColor());
			assertEquals(Color.BLACK, g.getBoard().getPiece(i, 7).getColor());
			IntStream.rangeClosed(2, 6).forEach(j -> {
				assertEquals(null, g.getBoard().getPiece(i, j));
			});

		});
		assertEquals("Tangi", g.getPlayer1().getName());
		assertEquals(Color.WHITE, g.getPlayer1().getColor());
		assertEquals(Color.BLACK, g.getPlayer2().getColor());
		Assertions.assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
	}

	@Test
	void testMovePiece(final Client client, final URI baseUri) {
		client.register(JacksonFeature.class).register(DiabalikJacksonProvider.class);
		Response res = client.target(baseUri).path("game/newGamePVC/Tangi/STANDARD/NOOB").request().put(Entity.text(""));
		Piece p = res.readEntity(Game.class).getBoard().getPiece(2,1);
		res = client.target(baseUri).path("game/movePiece/2/1/2/2").request().put(Entity.text(""));
		Game g = res.readEntity(Game.class);
		assertEquals(p.getColor(),g.getBoard().getPiece(2,2).getColor());
		assertEquals(null,g.getBoard().getPiece(2,1));
	}

	@Test
	void testMoveBall(final Client client, final URI baseUri) {
		client.register(JacksonFeature.class).register(DiabalikJacksonProvider.class);
		Response res = client.target(baseUri).path("game/newGamePVC/Tangi/STANDARD/NOOB").request().put(Entity.text(""));
		Piece p = res.readEntity(Game.class).getBoard().getPiece(2,1);
		res = client.target(baseUri).path("game/moveBall/4/1/1/1").request().put(Entity.text(""));
		Game g = res.readEntity(Game.class);
		assertEquals(g.getBoard().getPiece(1,1),g.getBoard().getCurrentWhiteHolder());
		assertNotEquals(g.getBoard().getPiece(4,1),g.getBoard().getCurrentWhiteHolder());
	}

	@Test
	void testUndo(final Client client, final URI baseUri) { //TODO
		client.register(JacksonFeature.class).register(DiabalikJacksonProvider.class);
		client.target(baseUri).path("game/newGamePVC/Tangi/STANDARD/NOOB").request().put(Entity.text(""));
		Response res = client.target(baseUri).path("game/moveBall/4/1/1/1").request().put(Entity.text(""));
		Game g = res.readEntity(Game.class);
		assertEquals(g.getBoard().getPiece(1,1),g.getBoard().getCurrentWhiteHolder());
		assertNotEquals(g.getBoard().getPiece(4,1),g.getBoard().getCurrentWhiteHolder());

		Response res1 = client.target(baseUri).path("game/replay/undo").request().get();
		Game g1 = res1.readEntity(Game.class);
		assertEquals(g1.getBoard().getPiece(4,1),g1.getBoard().getCurrentWhiteHolder());
		assertNotEquals(g1.getBoard().getPiece(1,1),g1.getBoard().getCurrentWhiteHolder());
	}

	@Test
	void testRedo(final Client client, final URI baseUri) { //TODO
		client.register(JacksonFeature.class).register(DiabalikJacksonProvider.class);
		client.target(baseUri).path("game/newGamePVC/Tangi/STANDARD/NOOB").request().put(Entity.text(""));
		Response res = client.target(baseUri).path("game/moveBall/4/1/1/1").request().put(Entity.text(""));
		Game g = res.readEntity(Game.class);
		assertEquals(g.getBoard().getPiece(1,1),g.getBoard().getCurrentWhiteHolder());
		assertNotEquals(g.getBoard().getPiece(4,1),g.getBoard().getCurrentWhiteHolder());

		Response res1 = client.target(baseUri).path("game/replay/undo").request().get();
		System.out.println(res1);
		Game g1 = res1.readEntity(Game.class);
		assertEquals(g1.getBoard().getPiece(4,1),g1.getBoard().getCurrentWhiteHolder());
		assertNotEquals(g1.getBoard().getPiece(1,1),g1.getBoard().getCurrentWhiteHolder());

		Response res2 = client.target(baseUri).path("game/replay/redo").request().get();
		Game g2 = res2.readEntity(Game.class);
		assertEquals(g2.getBoard().getPiece(1,1),g2.getBoard().getCurrentWhiteHolder());
		assertNotEquals(g2.getBoard().getPiece(4,1),g2.getBoard().getCurrentWhiteHolder());
	}
}
