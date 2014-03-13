package progin.chapter4_linkedlists;

public class Queue<T> {

	private DoublyLinkedList<T, Object> store = new DoublyLinkedList<T, Object>();
	
	public Queue() {}
	
	public void push(T element) {
		ListElement<T, Object> tail = new ListElement<T, Object>(element, null);
		store.insertTail(tail);
	}
	
	public T pop() {
		if (store.isEmpty()) { return null; }
		ListElement<T, Object> head = store.head();
		store.removeHead();
		return head.key;
	}
	
	public boolean isEmpty() {
		return store.isEmpty();
	}
	
}
