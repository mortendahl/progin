package progin.chapter6_arraysandstrings;

public class RemoveChars {

	/*
	 * Copied directly from book (included for the optimisation tricks).
	 *  
	 */
	
	public static void main(String[] args) {
		
		System.out.println(removeChars("Battle of the Vowels: Hawaii vs Grozny", "aeiou"));

	}
	
	public static String removeChars(String str, String remove) {
		
		char[] s = str.toCharArray();
		char[] r = remove.toCharArray();
		
		boolean[] flags = new boolean[128];
		
		for (int src = 0; src < r.length; src++) {
			flags[r[src]] = true;
		}
		
		int dst = 0;
		for (int src = 0; src < s.length; src++) {
			if (!flags[s[src]]) {
				s[dst++] = s[src];
			}
		}
		
		return new String(s, 0, dst);
	}

}
