package diaballik.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIPlayerTest {
	AIPlayer p;
	@BeforeEach
	void setUp() {
		this.p = new AIPlayer("ordi", Color.BLACK, Difficulty.NOOB);
	}

	@Test
	void testPlay() {
	}

}