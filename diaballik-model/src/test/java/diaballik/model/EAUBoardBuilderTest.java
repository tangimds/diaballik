package diaballik.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EAUBoardBuilderTest {
	@Test
	void testBuildBoard() {
		EAUBoardBuilder eauBoardBuilder = new EAUBoardBuilder();
		assertTrue(eauBoardBuilder.buildBoard() != null);
	}
}