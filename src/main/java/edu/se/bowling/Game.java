package edu.se.bowling;

public class Game {

	private static final int NUMBER_OF_FRAMES = 10;
	private static final int MAX_NUMBER_OF_ROLLS = 21;
	private static final int NUMBER_OF_PINS = 10;
	private int[] rolls = new int[MAX_NUMBER_OF_ROLLS];
	private int currentRoll = 0;

	public void roll(int pins) {
    	validatePins(pins);

    	if (isGameOver()) {
        	throw new IllegalStateException("Cannot roll after game is over");
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

private void validatePins(int pins) {
    if (pins < 0) {
        throw new IllegalArgumentException("Pins cannot be negative");
    }

    if (pins > 10) {
        throw new IllegalArgumentException("Pins cannot be greater than 10");
    }

    int rollIndex = 0;

    // Solo validamos los 9 primeros frames aquí
    for (int frame = 0; frame < 9; frame++) {
        if (rollIndex == currentRoll) {
            return; // primera tirada de este frame
        }

        if (rolls[rollIndex] == 10) {
            rollIndex++; // strike
        } else {
            if (rollIndex + 1 == currentRoll) {
                // estamos metiendo la segunda tirada de este frame
                if (rolls[rollIndex] + pins > 10) {
                    throw new IllegalArgumentException("Frame cannot exceed 10 pins");
                }
                return;
            }
            rollIndex += 2;
        }
    }

    // Si llegamos aquí, estamos en el décimo frame o en bonus rolls.
    // De momento no aplicamos la regla de suma <= 10 aquí,
    // porque en el décimo frame tras strike puede haber 10 + 10 + 10.
}

	private boolean isGameOver() {
    	int rollIndex = 0;

    	for (int frame = 0; frame < 9; frame++) {
        	if (rollIndex >= currentRoll) {
            	return false;
        	}

        	if (rolls[rollIndex] == 10) {
            	rollIndex++;
        	} else {
            	if (rollIndex + 1 >= currentRoll) {
                	return false;
            	}	
            	rollIndex += 2;
        	}
    	}

    // Décimo frame
    	if (rollIndex >= currentRoll) {
        	return false;
    	}

    	int firstRoll = rolls[rollIndex];

    	if (firstRoll == 10) {
        	return currentRoll >= rollIndex + 3;
    	}

    	if (rollIndex + 1 >= currentRoll) {
        	return false;
    	}

    	int secondRoll = rolls[rollIndex + 1];

    	if (firstRoll + secondRoll == 10) {
        	return currentRoll >= rollIndex + 3;
    	}

    	return currentRoll >= rollIndex + 2;
	}

}
