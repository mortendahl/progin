package progin.chapter4_linkedlists;

public class DoublyLinkedList<T, U> {

	private final ListElement<T, U> sentinal;
	
	public DoublyLinkedList() {
		ListElement<T, U> sentinal = new ListElement<T, U>();
		sentinal.next = sentinal;
		sentinal.previous = sentinal;
		this.sentinal = sentinal;
	}
	
	public void insert(ListElement<T, U> before, ListElement<T, U> element, ListElement<T, U> after) {
		element.next = after;
		element.previous = before;
		before.next = element;
		after.previous = element;
	}
	
	public void insertHead(ListElement<T, U> element) {
		ListElement<T, U> before = this.sentinal;
		ListElement<T, U> after = this.sentinal.next;
		insert(before, element, after);
	}
	
	public void insertTail(ListElement<T, U> element) {
		ListElement<T, U> before = this.sentinal.previous;
		ListElement<T, U> after = this.sentinal;
		insert(before, element, after);
	}
	
	public void remove(ListElement<T, U> element) {
		ListElement<T, U> before = element.previous;
		ListElement<T, U> after = element.next;
		before.next = after;
		after.previous = before;
	}
	
	public void removeHead() {
		remove(this.sentinal.next);
	}
	
	public void removeTail() {
		remove(this.sentinal.previous);
	}
	
	public ListElement<T, U> findFirst(T key) {
		ListElement<T, U> current = this.sentinal.next;
		while (current != this.sentinal) {
			if (current.key.equals(key)) {
				// found it
				return current;
			} else {
				current = current.next;
			}
		}
		return null;
	}
	
	public ListElement<T, U> findLast(T key) {
		ListElement<T, U> current = this.sentinal.previous;
		while (current != this.sentinal) {
			if (current.key.equals(key)) {
				// found it
				return current;
			} else {
				current = current.previous;
			}
		}
		return null;
	}
	
	public boolean isEmpty() {
		ListElement<T, U> head = this.sentinal.next;
		return (head == sentinal);
	}
	
	public ListElement<T, U> head() {
		ListElement<T, U> head = this.sentinal.next;
		return (head == sentinal ? null : head);
	}
	
	public ListElement<T, U> tail() {
		ListElement<T, U> tail = this.sentinal.previous;
		return (tail == sentinal ? null : tail);
	}
	
}
