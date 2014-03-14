package progin.chapter6_arraysandstrings;

public class ReverseSentence {

	public static void main(String[] args) {
	
		char[] str = "Hej med dig!".toCharArray();
		
		System.out.println(str);
		
		// first reverse entire string
		reverseRange(str, 0, str.length-1);
		
		// then reverse each word, putting them back in correct order individually
		int start = 0;
		int end = 0;
		while (end < str.length) {
			if (str[end] == ' ') {
				reverseRange(str, start, end-1);
				start = end + 1;
			}
			end += 1;
		}
		// reverse last word
		reverseRange(str, start, end-1);
		
		System.out.println(str);
	}
	
	public static void reverseRange(char[] str, int lower, int upper) {
		while (lower < upper) {
			// swap char at lower and upper
			char tmp = str[lower];
			str[lower] = str[upper];
			str[upper] = tmp;
			// move pointer up and down
			lower += 1;
			upper -= 1;
		}
	}

}
