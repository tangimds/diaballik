package diaballik.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import diaballik.model.*;
import diaballik.serialization.DiabalikJacksonProvider;

import java.awt.Color;
import java.io.IOException;
import java.security.DigestInputStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMarshalling {
    static Stream<Object> getInstancesToMarshall() {
		final GameBuilder gameBuilder = new PvCGameBuilder();
		final Game game = gameBuilder.buildGame("Taha", "Glados", Scenario.STANDARD.getValue(), Difficulty.NOOB.getValue());

		//Difficulty diff = Difficulty.NOOB;

		return Stream.of(game); //p1); // Add other marshallable elements
    }

    @ParameterizedTest
    @MethodSource("getInstancesToMarshall")
    void testMarshall(final Object objectToMarshall) throws IOException {
        final ObjectMapper mapper = new DiabalikJacksonProvider().getMapper();
        final String serializedObject = mapper.writeValueAsString(objectToMarshall);
        System.out.println(serializedObject);
        final Object readValue = mapper.readValue(serializedObject, objectToMarshall.getClass());
        assertEquals(objectToMarshall, readValue);
    }
}
