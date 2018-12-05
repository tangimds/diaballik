package diaballik.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
	@Test
	void testIsBetween1() {
		Piece p1 = new Piece(Color.BLACK, 1, 1);
		Piece p2 = new Piece(Color.BLACK, 1, 5);
		Piece p3 = new Piece(Color.BLACK, 1, 7);

		assertTrue(p2.isBetween(p1, p3));
	}

	@Test
	void testIsBetween2() {
		Piece p1 = new Piece(Color.BLACK, 1, 1);
		Piece p2 = new Piece(Color.BLACK, 1, 5);
		Piece p3 = new Piece(Color.BLACK, 1, 7);

		assertFalse(p1.isBetween(p2, p3));
	}

	@Test
	void testIsBetween3() {
		Piece p1 = new Piece(Color.BLACK, 1, 1);
		Piece p2 = new Piece(Color.BLACK, 2, 5);
		Piece p3 = new Piece(Color.BLACK, 1, 7);

		assertFalse(p2.isBetween(p1, p3));
	}

	@Test
	void testHashCode() {
		final Piece p1 = new Piece(Color.WHITE,1,1);
		final Piece p2 = new Piece(Color.WHITE,1,1);
		assertEquals(p1.hashCode(),p2.hashCode());
	}

}