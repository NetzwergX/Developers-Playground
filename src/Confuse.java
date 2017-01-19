/**
 * @author Sebastian Teumert
 *
 */
public class Confuse {
	
	public static void main (String[] args) {
		
		int x = 10;
		while (x --> 0) // while x goes to zero
			System.out.println(x);
		
		x = 10;
		while (0 <-- x) // “while zero is approached by x”
			System.out.println(x);
		
	}
}
