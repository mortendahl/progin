package progin.chapter9_concurrency;

class UnprotectedStack {
	
	private Integer[] store;
	private int maxStackSize;
	private int stackSize;
	
	public UnprotectedStack(int maxStackSize) {
		this.maxStackSize = maxStackSize;
		this.store = new Integer[this.maxStackSize];
		this.stackSize = 0;
	}
	
	public boolean isEmpty() {
		return (stackSize == 0);
	}
	
	public boolean isFull() {
		return (stackSize == maxStackSize-1);
	}
	
	public void push(Integer element) {
		if (isFull()) {	return; }
		store[stackSize] = element;
		stackSize += 1;
	}
	
	// note: returns null if stack is empty
	public Integer pop() {
		if (isEmpty()) { return null; }
		stackSize -= 1;
		return store[stackSize];
		// return store[stackSize--];
	}
}