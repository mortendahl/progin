package progin.chapter7_recursion;

import java.util.*;

public class Combinations {
	
	/*
	 * Problem: generate all combinations (subsets) of a string
	 * 
	 * Solution: recursively explore tree where a node at depth i decides if character at position i is included or not
	 *  - note that both sub-trees are identical (suggesting that bottom-up is better -- fewer method calls)   
	 * 
	 */

	public static void main(String[] args) {
		
		List<List<String>> solutions = new ArrayList<List<String>>();
		
		//String input = "";
		//String input = "a";
		//String input = "ab";
		String input = "abcde";
		solutions.add(allCombinationsRecursiveBottomUp(input));
		solutions.add(allCombinationsRecursiveTopDown(input));
		solutions.add(allCombinationsIterativeBottomUp(input));
		solutions.add(allCombinationsIterativeTopDown(input));
		
		
		for (List<String> solution : solutions) {
			System.out.print(solution.size() + "  ");
		}
		System.out.println();
		
		// assume they all have the same length; otherwise we may get index out of bounds exception
		for (int i = 0; i < solutions.get(0).size(); i++) {
			for (int j = 0; j < solutions.size(); j++) {
				System.out.print("'" + solutions.get(j).get(i) + "'  ");
			}
			System.out.println();
		}
	}
	
	
	
	
	
	public static List<String> allCombinationsRecursiveBottomUp(String input) {
		List<String> combs = new ArrayList<String>();
		if (input.length() == 0) {
			combs.add("");
		} else {
			String curChar = input.substring(0, 1);
			List<String> subCombs = allCombinationsRecursiveBottomUp(input.substring(1));
			// cases where curChar is not included 
			combs.addAll(subCombs);
			// cases where curChar is included
			for (String comb : subCombs) {
				combs.add(curChar + comb);
			}
		}
		return combs;
	}
	
	
	
	
	
	public static List<String> allCombinationsIterativeBottomUp(String input) {
		List<String> combs = new ArrayList<String>();
		combs.add("");
		for (int i = input.length()-1; i >= 0; i--) {
			int previousLength = combs.size();
			for (int j = 0; j < previousLength; j++) {
				combs.add(input.substring(i, i+1) + combs.get(j));
			}
		}
		return combs;
	}
	
	
	
	
	
	public static List<String> allCombinationsRecursiveTopDown(String input) {
		List<String> combs = new ArrayList<String>();
		allCombinationsRecursiveTopDown(input, "", combs);
		return combs;
	}
	
	private static void allCombinationsRecursiveTopDown(String input, String output, List<String> acc) {
		if (input.length() == 0) {
			// base case
			acc.add(output);
		} else {
			String curChar = input.substring(0, 1);
			// cases where curChar is not included
			allCombinationsRecursiveTopDown(input.substring(1), output, acc);
			// cases where curChar is included
			allCombinationsRecursiveTopDown(input.substring(1), output + curChar, acc);
		}
	}
	
	
	
	
	
	public static List<String> allCombinationsIterativeTopDown(String input) {
		List<String> combs = new ArrayList<String>();
		combs.add("");
		for (int i = 0; i < input.length(); i++) {
			int previousLength = combs.size();
			for (int j = 0; j < previousLength; j++) {
				combs.add(combs.get(j) + input.substring(i, i+1));
			}
		}
		return combs;
	}

}
