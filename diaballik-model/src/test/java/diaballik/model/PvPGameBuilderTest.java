package diaballik.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PvPGameBuilderTest {
	@Test
	void testBuildGame() {
		PvPGameBuilder pvp = new PvPGameBuilder();
		Game g = pvp.buildGame("humain1","humain2", Scenario.STANDARD, Difficulty.NOOB);

		Player p1 = new HumanPlayer("humain1",Color.WHITE);
		Player p2 = new HumanPlayer("humain2", Color.BLACK);
		Game g1 = new Game(p1,p2, Scenario.STANDARD);

		assertEquals(g,g1,"builder");
	}

}