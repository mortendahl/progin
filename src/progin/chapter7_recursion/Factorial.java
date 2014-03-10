package progin.chapter7_recursion;

public class Factorial {

	public static void main(String[] args) {
		
		for (int n = 0; n < 10; n++) {		
			System.out.println(factorialRecursive(n));
			System.out.println(factorialTailRecursive(n));
			System.out.println(factorialIterative(n));
			
			System.out.println();
		}
	}
	
	// straight-forward recursive approach
	public static int factorialRecursive(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return n*factorialRecursive(n-1);
		}
	}
	
	// recursive approach with tail recursion
	public static int factorialTailRecursive(int n) {
		return factorialTailRecursive(n, 1);
	}
	
	private static int factorialTailRecursive(int n, int acc) {
		if (n <= 1) {
			return acc;
		} else {
			return factorialTailRecursive(n-1, acc*n);
		}
	}
	
	// iterative approach from tail recursive
	public static int factorialIterative(int n) {
		int acc = 1;
		for (int x = n; x > 1; x--) {
			acc *= x;
		}
		return acc;
	}
	
}
