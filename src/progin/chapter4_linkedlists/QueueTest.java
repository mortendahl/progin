package progin.chapter4_linkedlists;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {

	@Test
	public void testPushPop() {
		Queue<Integer> queue = new Queue<Integer>();
		
		assertTrue(queue.isEmpty());
		
		for (int i = 0; i < 5; i++) {
			queue.push(i);
		}
		
		while (!queue.isEmpty()) {
			Integer head = queue.pop();
			System.out.println(head);
		}
		
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void testEmptyPop() {
		Queue<Integer> queue = new Queue<Integer>();
		
		assertNull(queue.pop());
		
	}


}
