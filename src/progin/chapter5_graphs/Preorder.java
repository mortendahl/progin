package progin.chapter5_graphs;

public class Preorder {

	public static void main(String[] args) {
		
		// figure 5.6
		TreeNode<Integer, Object> node175 = new TreeNode<Integer, Object>(175, null);
		TreeNode<Integer, Object> node110 = new TreeNode<Integer, Object>(110, null);
		TreeNode<Integer, Object> node125 = new TreeNode<Integer, Object>(125, null, node110, null);
		TreeNode<Integer, Object> node150 = new TreeNode<Integer, Object>(150, null, node125, node175);
		TreeNode<Integer, Object> node75 = new TreeNode<Integer, Object>(75, null);
		TreeNode<Integer, Object> node25 = new TreeNode<Integer, Object>(25, null);
		TreeNode<Integer, Object> node50 = new TreeNode<Integer, Object>(50, null, node25, node75);
		TreeNode<Integer, Object> node100 = new TreeNode<Integer, Object>(100, null, node50, node150);
		
		preorder(node100);
		
	}
	
	public static void preorder(TreeNode<?, ?> node) {
		if (node == null) { return; }
		System.out.println(node.key);
		preorder(node.left);
		preorder(node.right);
	}
	
}
