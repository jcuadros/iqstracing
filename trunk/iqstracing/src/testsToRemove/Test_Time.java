package testsToRemove;

import java.util.*;
import java.text.*;

public class Test_Time {
	static String time;
	public static void main (String[]args){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss zzz", Locale.getDefault());
		time = dateFormat.format(new Date());
		System.out.println(time);
	}
}
