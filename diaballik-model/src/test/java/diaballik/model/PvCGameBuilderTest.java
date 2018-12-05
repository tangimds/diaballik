package diaballik.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PvCGameBuilderTest {
	@Test
	void testBuildGame() {

	}

	@Test
	void testBuildGame1() {
		PvCGameBuilder pvc = new PvCGameBuilder();
		Game g = pvc.buildGame("humain","ordi", Scenario.STANDARD, Difficulty.NOOB);

		Player p1 = new HumanPlayer("humain",Color.WHITE);
		Player p2 = new AIPlayer("ordi", Color.BLACK, Difficulty.NOOB);
		Game g1 = new Game(p1,p2, Scenario.STANDARD);

		assertEquals(g,g1,"builder");
	}

}