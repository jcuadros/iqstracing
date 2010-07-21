package iqstracing;

import java.util.*;

public class RandomGenerator {
	public static String generate(int numberCharacters){
		char[] result  = new char[numberCharacters];
		Random rand = new Random();
		int randomNumber;

		List<Character> base36 = new ArrayList<Character>();
		for (int i = 48; i <= 57; i++){
			base36.add((char)i);
		}
		for (int i = 65; i <= 90; i++){
			base36.add((char)i);
		}
		for (int i = 0; i < numberCharacters; i++){
			randomNumber = rand.nextInt(36);
			result[i] = (char)base36.get(randomNumber);
		}
		System.out.println(new String (result));
		return new String (result);
	}
	public static void main (String[]args){
		generate(6);
	}
}
