package progin.chapter4_linkedlists;

public class SinglyLinkedList<T, U> {

	private final ListElement<T, U> sentinal;
	
	public SinglyLinkedList() {
		ListElement<T, U> sentinal = new ListElement<T, U>(null, null);
		sentinal.next = sentinal;
		this.sentinal = sentinal;
	}
	
	public void insertHead(ListElement<T, U> element) {
		element.next = sentinal.next;
		sentinal.next = element;
	}
	
	public void removeHead() {
		ListElement<T, U> oldHead = this.sentinal.next;
		this.sentinal.next = oldHead.next;
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
	
	public boolean isEmpty() {
		ListElement<T, U> head = this.sentinal.next;
		return (head == sentinal);
	}
	
	public ListElement<T, U> head() {
		ListElement<T, U> head = this.sentinal.next;
		return (head == sentinal ? null : head);
	}

}
