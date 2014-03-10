package progin.other;

import java.util.*;

public class WordSplit {
	
	public static void main(String[] args) {
		
		Set<String> dictionary = new HashSet<String>();
		dictionary.add("pea");
		dictionary.add("nut");
		//dictionary.add("peanut");
		dictionary.add("butter");
		
		List<String> words = split_naive("peanutbutter", dictionary);
		if (words != null) {
			for (String word : words) {
				System.out.println(word);
			}
		} else {
			System.out.println("No split possible");
		}
		
		System.out.println(split_dynamic("peanutbutter", dictionary));
		
	}
	
	public static List<String> split_naive(String str, Set<String> dictionary) {
		if (dictionary.contains(str)) {
			List<String> split = new ArrayList<String>();
			split.add(str);
			return split;
		}
		for (int i = str.length()-1; i >= 0; i--) {
			String right = str.substring(i);
			if (dictionary.contains(right)) {
				String left = str.substring(0, i);
				List<String> subSplit = split_naive(left, dictionary);
				if (subSplit != null) {
					// we have a solution!
					subSplit.add(right);
					return subSplit;
				}
			}
		}
		return null;
	}
	
	public static boolean split_dynamic(String str, Set<String> dictionary) {
		boolean[] store = new boolean[str.length()];
		for (int i = 0; i < store.length; i++) {
			store[i] = false;
		}
		for (int i = 0; i < str.length(); i++) {
			String sub_str = str.substring(0, i+1);
			if (dictionary.contains(sub_str)) {
				store[i] = true;
			} else {
				for (int j = i; j > 0; j--) {
					String right = str.substring(j, i+1);
					if (dictionary.contains(right) && store[j-1]) {
						store[i] = true;
						break;
					}
				}
			}
		}
		return store[store.length - 1];
	}

}
