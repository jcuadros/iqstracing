package testsToRemove;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUserGenerator {
	public static void main (String[]args){
		char[] user  = new char[6];
		Random rand = new Random();
		int randomNumber;

		List<Character> base36 = new ArrayList<Character>();
		for (int i = 48; i <= 57; i++){
			base36.add((char)i);
		}
		for (int i = 65; i <= 90; i++){
			base36.add((char)i);
		}
		//System.out.println(base36);
		for (int i = 0; i < 6; i++){
			randomNumber = rand.nextInt(base36.size());
			user[i] = (char)base36.get(randomNumber);
		}
		System.out.println("user: " + new String(user));
	}
}
