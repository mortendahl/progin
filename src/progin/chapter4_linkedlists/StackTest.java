package progin.chapter4_linkedlists;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {

	@Test
	public void testPushPop() {
		Stack<Integer> stack = new Stack<Integer>();
		
		assertTrue(stack.isEmpty());
		
		for (int i = 0; i < 5; i++) {
			stack.push(i);
		}
		
		while (!stack.isEmpty()) {
			Integer top = stack.pop();
			System.out.println(top);
		}
		
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void testEmptyPop() {
		Stack<Integer> stack = new Stack<Integer>();
		
		assertNull(stack.pop());
		
	}

}
