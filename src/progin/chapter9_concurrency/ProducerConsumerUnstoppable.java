package progin.chapter9_concurrency;

import java.util.concurrent.TimeUnit;

public class ProducerConsumerUnstoppable {

	private static UnprotectedStack stack = new UnprotectedStack(5);
	private static Object sharedLock = new Object();
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread producer = new Thread(new Runnable() {
			public void run() {
				int counter = 0;
				while (true) {
					// produce new element (outside lock since it could be long computation)
					Integer element = counter;
					counter += 1;
					System.out.println("Producing: " + element); 
					try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) {}
					System.out.println("Produced: " + element);
					// push element on stack when there's room
					synchronized(sharedLock) {
						// wait until there is room in the stack
						while (stack.isFull()) {
							try {
								sharedLock.wait();  // releases lock
							}
							catch (InterruptedException inex) {}
						}
						// at this point we took the lock, and found that the stack was not full; ie it remains not full
						stack.push(element);
						// inform waiting threads that the stack's state changed (notifyAll in case there are several consumers) 
						sharedLock.notifyAll();
					}
				}
			}
		});
		
		Thread consumer = new Thread(new Runnable() {
			public void run() {
				while (true) {
					Integer element;
					// pop element when there's any
					synchronized(sharedLock) {
						// wait until there is something in the stack
						while (stack.isEmpty()) {
							try {
								sharedLock.wait();  // releases lock
							}
							catch (InterruptedException inex) {}
						}
						// at this point we took the lock, and found that the stack was not empty, ie it remains not empty
						element = stack.pop();
						// inform waiting threads that the stack's state changed (notifyAll in cases there are several producers)
						sharedLock.notifyAll();
						// release lock before doing (long) computation on element
					}
					// consume element
					System.out.println("Consuming: " + element);
					try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {}
					System.out.println("Consumed: " + element);
				}
				
			}
		});
		
		consumer.start();
		producer.start();
		
		// keep the economy running indefinitely
		consumer.join();
		producer.join();
		
	}
	
	

}

