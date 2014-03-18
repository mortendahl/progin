package progin.chapter6_arraysandstrings;

import java.util.HashMap;

public class FirstNonRepeated {

	/*
	 * Copied directly from book (included for the optimisation tricks).
	 *  
	 */
	
	public static void main(String[] args) {
		
		System.out.println(firstNonRepeatedChar("total"));
		System.out.println(firstNonRepeatedChar("teeter"));

	}
	
	public static String firstNonRepeatedChar(String str) {
		
		final Object seenOnce = new Object();     // flag indicating character has been seen only once 
		final Object seenMultiple = new Object(); // flag indicating character has been seen at least twice
		
		HashMap<Integer, Object> charCount = new HashMap<Integer, Object>();
		
		for (int i = 0; i < str.length(); ) {
			final int cp = str.codePointAt(i);
			
			Object flag = charCount.get(cp);
			if (flag == null) {
				// not seen before
				charCount.put(cp, seenOnce);
			} else if (flag == seenOnce) {
				// seen once before
				charCount.put(cp, seenMultiple);
			}
			
			// manual increment of i since each char may have different length in Unicode
			i += Character.charCount(i);
		}
		
		for (int i = 0; i < str.length(); ) {
			final int cp = str.codePointAt(i);
			
			Object flag = charCount.get(cp);
			if (flag == seenOnce) {
				// not seen before
				return new String(Character.toChars(cp));
			}
			
			// manual increment of i since each char may have different length in Unicode
			i += Character.charCount(i);
		}
		
		return null;
	}

}
