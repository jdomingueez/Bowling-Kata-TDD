package edu.se.bowling;

public class Game {

	private static final int NUMBER_OF_FRAMES = 10;
	private static final int MAX_NUMBER_OF_ROLLS = 21;
	private static final int NUMBER_OF_PINS = 10;
	private int[] rolls = new int[MAX_NUMBER_OF_ROLLS];
	private int currentRoll = 0;

	public void roll(int pins) {
    	if (pins < 0) {
        	throw new IllegalArgumentException("Pins cannot be negative");
    	}
		if (pins > 10) {
        	throw new IllegalArgumentException("Pins cannot be greater than 10");
    	}
    	if (currentRoll % 2 == 1 && rolls[currentRoll - 1] != 10 && rolls[currentRoll - 1] + pins > 10) {
        	throw new IllegalArgumentException("Frame cannot exceed 10 pins");
    	}
    	rolls[currentRoll++] = pins;
	}

	public int score() {
		int score = 0;
		int rollIdx = 0;
		for (int frameIdx = 0; frameIdx < NUMBER_OF_FRAMES; frameIdx++) {
			if (isStrike(rollIdx)) {
				score += NUMBER_OF_PINS + strikeBonus(rollIdx);
				rollIdx++;
			} else if (isSpare(rollIdx)) {
				score += NUMBER_OF_PINS + spareBonus(rollIdx);
				rollIdx += 2;
			} else {
				score += rolls[rollIdx] + rolls[rollIdx + 1];
				rollIdx += 2;
			}
		}
		return score;
	}

	private int strikeBonus(int rollIdx) {
		return rolls[rollIdx + 1] + rolls[rollIdx + 2];
	}

	private boolean isStrike(int rollIdx) {
		return rolls[rollIdx] == NUMBER_OF_PINS;
	}

	private int spareBonus(int frameRollIdx) {
		return rolls[frameRollIdx + 2];
	}

	private boolean isSpare(int frameRollIdx) {
		return rolls[frameRollIdx] + rolls[frameRollIdx + 1] == NUMBER_OF_PINS;
	}

}
