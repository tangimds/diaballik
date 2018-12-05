package diaballik.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Utf8;
import diaballik.serialization.DiabalikJacksonProvider;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SavedGameBuilder extends GameBuilder {

	@Override
	public Game buildGame(final String name1, final String name2, final Scenario scenario, final Difficulty difficulty) {
		return null;
	}

	@Override
	public Game buildGame(final String gameId) {
		final String dir = System.getProperty("user.home") + System.getProperty("file.separator") + "Diaballik" + System.getProperty("file.separator");
		final Path path = Paths.get(dir, "game_" + gameId + ".txt");

		byte[] encoded = new byte[0];
		try {
			encoded = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		final ObjectMapper mapper = new DiabalikJacksonProvider().getMapper();
		Object readValue = null;
		try {
			readValue = mapper.readValue(new String(encoded,UTF_8), Game.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (Game) readValue;

	}

}
