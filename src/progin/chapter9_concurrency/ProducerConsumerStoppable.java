package progin.chapter9_concurrency;

import java.util.concurrent.TimeUnit;

public class ProducerConsumerStoppable {

	private static UnprotectedStack stack = new UnprotectedStack(5);
	private static Object sharedLock = new Object();
	private static volatile boolean keepRunning = true;  // must be volatile to guarantee flip to reach other threads
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread producer = new Thread(new Runnable() {
			public void run() {
				int counter = 0;
				while (true) {
					if (!keepRunning) { return; }
					// produce new element
					// note: once in a while during production we should check if we should keep running (mostly ignored here) 
					System.out.println("Producing..");
					Integer element = counter;
					counter += 1;
					try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) {}
					System.out.println("Produced: " + element);
					// check if we should keep running before entering loop, in case the interrupted flag got swallowed during production 
					if (!keepRunning) { return; }
					// push element on stack when there's room
					synchronized(sharedLock) {
						// wait until there is room in the stack
						while (stack.isFull()) {
							try {
								sharedLock.wait();  // releases lock
								// note: wait will throw exception even if interrupt was called on thread *before* we called wait
							}
							catch (InterruptedException inex) {
								if (!keepRunning) { return; }
							}
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
								// note: wait will throw exception even if interrupt was called on thread *before* we called wait
							}
							catch (InterruptedException inex) {
								if (!keepRunning) { return; }
							}
						}
						// at this point we took the lock, and found that the stack was not empty, ie it remains not empty
						element = stack.pop();
						// inform waiting threads that the stack's state changed (notifyAll in cases there are several producers)
						sharedLock.notifyAll();
						// release lock before doing (long) computation on element
					}
					if (!keepRunning) { return; }
					// consume element
					// note: once in a while during consumption we should check if we should keep running (mostly ignored here)
					System.out.println("Consuming..");
					try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {}
					System.out.println("Consumed: " + element);
					// check if we should keep running before repeating, in case the interrupted flag got swallowed during consumption 
					if (!keepRunning) { return; }
				}
			}
		});
		
		consumer.start();
		producer.start();
		
		// keep the economy running for a while before stopping it
		TimeUnit.MINUTES.sleep(10);
		// shut it down
		System.out.println("Stopping..");
		// flip flag
		keepRunning = false;
		// interrupt all threads in case they were waiting 
		consumer.interrupt();
		producer.interrupt();
		// wait for all threads to run out
		consumer.join();
		System.out.println("Consumers stopped");
		producer.join();
		System.out.println("Producer stopped");
		
	}
}