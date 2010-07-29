package testsToRemove;
import java.util.Hashtable;
import javax.swing.*;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Hashtable<String,String> taula=new Hashtable<String,String>();
		taula.put("color", "red");
		taula.put("color", "blue");
		taula.put("color2", "red");
		for(String s:taula.keySet()) {
			System.out.println(s + " " +taula.get(s));
			System.out.println(new Double((double)System.nanoTime()*Math.pow(10,-6)).toString());
		}
	}

}
