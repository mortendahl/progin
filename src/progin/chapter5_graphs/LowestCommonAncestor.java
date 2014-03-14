package progin.chapter5_graphs;

public class LowestCommonAncestor {

	public static void main(String[] args) {
		
		// figure 5.7
		TreeNode<Integer, Object> node22 = new TreeNode<Integer, Object>(22, null);
		TreeNode<Integer, Object> node14 = new TreeNode<Integer, Object>(14, null);
		TreeNode<Integer, Object> node10 = new TreeNode<Integer, Object>(10, null);
		TreeNode<Integer, Object> node12 = new TreeNode<Integer, Object>(12, null, node10, node14);
		TreeNode<Integer, Object> node4 = new TreeNode<Integer, Object>(4, null);
		TreeNode<Integer, Object> node8 = new TreeNode<Integer, Object>(8, null, node4, node12);
		TreeNode<Integer, Object> node20 = new TreeNode<Integer, Object>(20, null, node8, node22);
		
		System.out.println(lca(node4, node14, node20).key);
		
	}
	
	// assume node1.key <= node2.key
	// generics and comparable ignored here for simplicity
	public static TreeNode<Integer, Object> lca(TreeNode<Integer, Object> node1, TreeNode<Integer, Object> node2, TreeNode<Integer, Object> root) {
		while (root != null) {
			if (node1.key <= root.key && root.key <= node2.key) {
				// found splitting point
				return root;
			} else {
				// still on same side
				//  - root.key < node1.key or node2.key < root.key from negation
				//  - and node1.key <= node2.key from assumption
				//  - so if root.key < node1.key then root.key < node2.key, meaning both on right
				//  - and if node2.key < root.key then node1.key < root.key, meaning both on left
				if (node2.key < root.key) {
					root = root.left;
				} else {
					root = root.right;
				}
			}
		}
		return null;
	}
}
