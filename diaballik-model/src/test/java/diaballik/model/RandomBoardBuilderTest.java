package diaballik.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomBoardBuilderTest {
	@Test
	void testBuildBoard() {
		RandomBoardBuilder randomBoardBuilder = new RandomBoardBuilder();
		assertTrue(randomBoardBuilder.buildBoard() != null);
	}

}