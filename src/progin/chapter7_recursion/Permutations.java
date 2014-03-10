package progin.chapter7_recursion;

import java.util.*;

public class Permutations {
	
	/*
	 * Problem: generate all permutations (sequences) of a string
	 * 
	 * Solution: recursively explore tree where a node at depth i decides which (unused) character goes at slot i
	 *  - note that sub-trees are all different (suggesting that top-down is better -- fewer list allocations)   
	 * 
	 */

	public static void main(String[] args) {
		
		List<List<String>> solutions = new ArrayList<List<String>>();
		
		solutions.add(allPermutationsRecursiveBottomUp("abcde"));
		solutions.add(allPermutationsRecursiveTopDown("abcde"));
		solutions.add(allPermutationsRecursiveTopDownOptimised("abcde"));
		
		for (List<String> solution : solutions) {
			System.out.print(solution.size() + "  ");
		}
		System.out.println();
		
		// assume they all have the same length; otherwise we may get index out of bounds exception
		for (int i = 0; i < solutions.get(0).size(); i++) {
			for (int j = 0; j < solutions.size(); j++) {
				System.out.print(solutions.get(j).get(i) + "  ");
			}
			System.out.println();
		}
		
	}
	
	
	
	
	
	// bottom-up recursive approach
	public static List<String> allPermutationsRecursiveBottomUp(String input) {
		List<String> perms = new ArrayList<String>();
		if (input.length() == 0) {
			// base case
			perms.add("");
		} else {
			for (int i = 0; i < input.length(); i++) {
				String left = input.substring(0, i);
				String curChar = input.substring(i, i+1);  // get one char
				String right = input.substring(i+1);
				
				List<String> subPerms = allPermutationsRecursiveBottomUp(left + right);
				for (String subPerm : subPerms) {
					perms.add(curChar + subPerm);
				}
			}
		}
		return perms;
	}
	
	
	
	
	
	// top-down recursive approach using accumulator 
	public static List<String> allPermutationsRecursiveTopDown(String input) {
		List<String> perms = new ArrayList<String>();
		allPermutationsRecursiveTopDown(input, "", perms);
		return perms;
	}
	
	private static void allPermutationsRecursiveTopDown(String input, String output, List<String> acc) {
		if (input.length() == 0) {
			// base case
			acc.add(output);
		} else {
			for (int i = 0; i < input.length(); i++) {
				String left = input.substring(0, i);
				String curChar = input.substring(i, i+1);  // get one char
				String right = input.substring(i+1);
				
				allPermutationsRecursiveTopDown(left + right, output + curChar, acc);
			}
		}
	}
	
	
	
	
	
	// avoid splitting up input string
	// .. same trick could be used for the bottom-up approach (a wrapper method is needed then)
	// another optimisation would be to use a StringBuilder
	public static List<String> allPermutationsRecursiveTopDownOptimised(String input) {
		List<String> perms = new ArrayList<String>();
		boolean[] used = new boolean[input.length()];  // default value is false
		allPermutationsRecursiveTopDownOptimised(input, used, "", perms);
		return perms;
	}
	
	private static void allPermutationsRecursiveTopDownOptimised(String input, boolean[] used, String output, List<String> acc) {
		boolean done = true;
		for (int i = 0; i < input.length(); i++) {
			if (!used[i]) {
				done = false;
				used[i] = true;
				allPermutationsRecursiveTopDownOptimised(input, used, output + input.substring(i,i+1), acc);
				used[i] = false;
			}
		}
		if (done) {
			// base case
			// .. moved here since we cannot determine it without scanning the 'used' array
			acc.add(output);
		}
	}

}
