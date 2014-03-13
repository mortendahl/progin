package progin.chapter4_linkedlists;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoublyLinkedListTest {

	@Test
	public void testAddRemove() {
		DoublyLinkedList<Integer, Object> list = new DoublyLinkedList<Integer, Object>();
				
		for (int i = 5; i < 10; i++) {
			list.insertHead(new ListElement<Integer, Object>(i, null));
		}
		
		for (int i = 4; i >= 0; i--) {
			list.insertTail(new ListElement<Integer, Object>(i, null));
		}
		
		for (int i = 0; i < 10; i++) {
			ListElement<Integer, Object> current = list.head();
			if (current != null) {
				System.out.println(current.key);
				list.removeHead();
			}
		}
	}
	
	@Test
	public void testEmpty() {
		DoublyLinkedList<Integer, Object> list = new DoublyLinkedList<Integer, Object>();
		list.removeHead();
	}
	
	@Test
	public void testFind() {
		DoublyLinkedList<Integer, Object> list = new DoublyLinkedList<Integer, Object>();
				
		for (int i = 0; i < 5; i++) {
			list.insertHead(new ListElement<Integer, Object>(i, null));
		}
		
		for (int i = 0; i < 5; i++) {
			Integer keyToLookFor = new Integer(i); 
			ListElement<Integer, Object> element = list.findFirst(keyToLookFor);
			assertEquals(element.key, keyToLookFor);
		}
		
		Integer nonExistingKey = new Integer(-1);
		assertNull(list.findFirst(nonExistingKey));
	}

}
