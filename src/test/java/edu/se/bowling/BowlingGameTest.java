package edu.se.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BowlingGameTest {

	private Game g;

	@BeforeEach
	public void setUp() throws Exception {
		g = new Game();
	}

	@Test
	public void gutterGameScoresZero() {
		rollMany(20, 0);
		assertEquals(0, g.score());
	}

	@Test
	public void allOneGameScoresTwenty() {
		rollMany(20, 1);
		assertEquals(20, g.score());
	}

	@Test
	public void perfectGameScores300() {
		rollMany(12, 10);
		assertEquals(300, g.score());
	}
	
	@Test
	public void spareScoresBonusNextRoll() {
		rollSpare();
		g.roll(3);
		rollMany(17, 0);
		assertEquals(16, g.score());
	}

	@Test
	public void strikeScoresBonusTwoNextRolls() {
		rollStrike();
		g.roll(4);
		g.roll(3);
		rollMany(16, 0);
		assertEquals(24, g.score());
	}

	private void rollStrike() {
		g.roll(10);
	}

	private void rollSpare() {
		g.roll(5);
		g.roll(5);
	}

	private void rollMany(int rolls, int pins) {
		for (int i = 0; i < rolls; i++) {
			g.roll(pins);
		}
	}

	@Test
	void testSpareInLastFrame() {
    	Game game = new Game();

    	for (int i = 0; i < 9; i++) {
        	game.roll(0);
        	game.roll(0);
    	}

    	game.roll(5);
    	game.roll(5);
    	game.roll(7);

    	assertEquals(17, game.score());
	}

	@Test
	void testStrikeInLastFrame() {
    	Game game = new Game();

    	for (int i = 0; i < 9; i++) {
        	game.roll(0);
        	game.roll(0);
    	}

    	game.roll(10);
    	game.roll(7);
    	game.roll(2);

    	assertEquals(19, game.score());
	}

	@Test
	void testNegativeRollThrowsException() {
    	Game game = new Game();

    	assertThrows(IllegalArgumentException.class, () -> game.roll(-1));
	}

	@Test
	void testRollGreaterThanTenThrowsException() {
    	Game game = new Game();
    	assertThrows(IllegalArgumentException.class, () -> game.roll(11));
	}
}
