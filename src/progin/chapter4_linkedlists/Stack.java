package progin.chapter4_linkedlists;

public class Stack<T> {
	
	private SinglyLinkedList<T, Object> store = new SinglyLinkedList<T, Object>();
	
	public Stack() {}
	
	public void push(T element) {
		ListElement<T, Object> top = new ListElement<T, Object>(element, null);
		store.insertHead(top);
	}
	
	public T pop() {
		if (store.isEmpty()) { return null; }
		ListElement<T, Object> top = store.head();
		store.removeHead();
		return top.key;
	}
	
	public boolean isEmpty() {
		return store.isEmpty();
	}

}
