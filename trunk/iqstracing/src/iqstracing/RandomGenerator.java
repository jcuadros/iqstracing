package iqstracing;

import java.util.ArrayList;
import java.util.Random;

class RandomGenerator {
	protected static String generate(){
		int numberCharacters = 6;
		char[] result  = new char[numberCharacters];
		Random rand = new Random();
		int randomNumber;

		ArrayList<Character> base36 = new ArrayList<Character>();
		for (int i = 48; i <= 57; i++) {
			base36.add((char) i);
		}
		for (int i = 65; i <= 90; i++){
			base36.add((char) i);
		}
		for (int i = 0; i < numberCharacters; i++) {
			randomNumber = rand.nextInt(36);
			result[i] = (char)  base36.get(randomNumber);
		}
		return new String(result);
	}
}
