package progin.chapter5_graphs;

public class TreeNode<T, U> {

	public TreeNode<T, U> left;
	public TreeNode<T, U> right;
	public T key;
	public U data;
	
	public TreeNode() {}
	
	public TreeNode(T key, U data) {
		this.key = key;
		this.data = data;
	}
	
	public TreeNode(T key, U data, TreeNode<T, U> left, TreeNode<T, U> right) {
		this.key = key;
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
}
