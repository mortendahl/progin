package progin.chapter7_recursion;

import java.util.*;

public class TelephoneWords {

	/*
	 * Problem: generate all words (sequences) that translate from a string
	 * 
	 * Solution: recursively explore tree where a node at depth i has a branch for each translation of position i 
	 *  - note that all sub-trees are identical (suggesting that bottom-up is better -- fewer method calls)
	 *  - similar to combinations, but now the branches are not include/exclude but the possible translations
	 *  
	 */

	public static void main(String[] args) {

		List<List<String>> solutions = new ArrayList<List<String>>();

		//String input = "";
		//String input = "1";
		//String input = "12";
		//String input = "123";
		String input = "8662665";
		solutions.add(translationsRecursiveBottomUp(input));
		solutions.add(translationsRecursiveTopDown(input));
		solutions.add(translationIterativeBottomUp(input));
		solutions.add(translationIterativeTopDown(input));

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

	
	private static char[][] keyMap = { 
			{ '0' }, 			// 0
			{ '1' }, 			// 1
			{ 'a', 'b', 'c' },	// 2
			{ 'd', 'e', 'f' },	// 3
			{ 'g', 'h', 'f' },	// 4
			{ 'j', 'k', 'l' },	// 5
			{ 'm', 'n', 'o' },	// 6
			{ 'p', 'r', 's' },	// 7
			{ 't', 'u', 'v' },	// 8
			{ 'w', 'x', 'y' }	// 9
	};

	
	
	
	
	public static List<String> translationsRecursiveBottomUp(String input) {
		List<String> combs = new ArrayList<String>();
		if (input.length() == 0) {
			// base case
			combs.add("");
		} else {
			String head = input.substring(0, 1);
			String tail = input.substring(1);
			// recursive call may be outside loop since all sub-trees are identical
			List<String> tailTrans = translationsRecursiveBottomUp(tail);
			for (char headTran : keyMap[Integer.parseInt(head)]) {
				for (String tailTran : tailTrans) {
					combs.add(headTran + tailTran);
				}
			}
		}
		return combs;
	}
	
	
	
	
	
	public static List<String> translationIterativeBottomUp(String input) {
		List<String> trans = new ArrayList<String>();
		trans.add("");
		for (int i = input.length()-1; i >= 0; i--) {
			List<String> newTrans = new ArrayList<String>();
			String nextHead = input.substring(i, i+1);
			for (char nextTran : keyMap[Integer.parseInt(nextHead)]) {
				for (String head : trans) {
					newTrans.add(nextTran + head);
				}
			}
			trans = newTrans;
		}
		return trans;
	}

	
	
	
	
	public static List<String> translationsRecursiveTopDown(String input) {
		List<String> combs = new ArrayList<String>();
		translationsRecursiveTopDown(input, "", combs);
		return combs;
	}

	private static void translationsRecursiveTopDown(String input, String output, List<String> acc) {
		if (input.length() == 0) {
			// base case
			acc.add(output);
		} else {
			String head = input.substring(0, 1);
			String tail = input.substring(1);
			for (char headTran : keyMap[Integer.parseInt(head)]) {
				// note that call is now inside loop since 'output' is changing!
				translationsRecursiveTopDown(tail, output + headTran, acc);
			}
		}
	}
	
	
	
	
	
	public static List<String> translationIterativeTopDown(String input) {
		List<String> trans = new ArrayList<String>();
		trans.add("");
		for (int i = 0; i < input.length(); i++) {
			List<String> newTrans = new ArrayList<String>();
			String nextHead = input.substring(i, i+1);
			for (char nextTran : keyMap[Integer.parseInt(nextHead)]) {
				for (String head : trans) {
					newTrans.add(head + nextTran);
				}
			}
			trans = newTrans;
		}
		return trans;
	}
	
}
