package progin.chapter4_linkedlists;

public class ListElement<T, U> {
	
	public T key;
	public U data;
	public ListElement<T, U> next;
	public ListElement<T, U> previous;
	
	public ListElement() {}
	
	public ListElement(T key, U data) {
		this.key = key;
		this.data = data;
	}

}
