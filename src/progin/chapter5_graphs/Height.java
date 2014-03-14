package progin.chapter5_graphs;

public class Height {
	
	public static void main(String[] args) {
		
		// figure 5.2
		TreeNode<String, Object> H = new TreeNode<String, Object>("H", null);
		TreeNode<String, Object> E = new TreeNode<String, Object>("E", null, null, H);
		TreeNode<String, Object> F = new TreeNode<String, Object>("F", null);
		TreeNode<String, Object> G = new TreeNode<String, Object>("G", null);
		TreeNode<String, Object> D = new TreeNode<String, Object>("D", null, F, G);
		TreeNode<String, Object> C = new TreeNode<String, Object>("C", null, D, E);
		TreeNode<String, Object> B = new TreeNode<String, Object>("B", null);
		TreeNode<String, Object> A = new TreeNode<String, Object>("A", null, B, C);
		
		System.out.println(height(A));
		
	}

	// note: that this is optimisation problem with optimal sub-structure, hence 
	// dynamic programming would apply; however, there's no overlap in subproblems 
	// so we wouldn't gain anything
	// note: not sure this is correct use of generics <?, ?>
	public static int height(TreeNode<?, ?> node) {
		if (node == null) { return -1; }
		return Math.max(height(node.left), height(node.right)) + 1;
	}
	
}
