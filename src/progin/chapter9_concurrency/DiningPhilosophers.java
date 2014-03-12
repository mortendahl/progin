package progin.chapter9_concurrency;

import java.util.concurrent.*;

public class DiningPhilosophers {

	private static Object[] forks;
	private static Philosopher[] philosophers;
	
	static class Philosopher implements Runnable {
		
		private int id;
		private int firstFork;
		private int secondFork;
		
		public Philosopher(int id, int firstFork, int secondFork) {
			this.id = id;
			this.firstFork = firstFork;
			this.secondFork = secondFork;
		}
		
		public void run() {
			while (true) {
				// take the first fork we've been told to use
				synchronized(forks[firstFork]) {
					// take the second fork we've been told to use
					synchronized(forks[secondFork]) {
						// eat
						System.out.println("Philosopher " + id + " is eating");
						try { TimeUnit.SECONDS.sleep(2); } catch(InterruptedException itex) {}
						System.out.println("Philosopher " + id + " is done eating");
						// put down second fork
					}
					// put down first fork
				}
				// think
				try { TimeUnit.SECONDS.sleep(4); } catch(InterruptedException itex) {}
			}
		}
	}
	
	public static void main(String[] args) {
		final int numberOfPhilosophersAndForks = 5;
		// put forks on table
		forks = new Object[numberOfPhilosophersAndForks];
		for (int i = 0; i < numberOfPhilosophersAndForks; i++) {
			forks[i] = new Object();
		}
		// seat philosophers around table
		philosophers = new Philosopher[numberOfPhilosophersAndForks];
		for (int i = 0; i < numberOfPhilosophersAndForks; i++) {
			// naive strategy (with cyclic pick-up order)
			int firstFork = i;
			int secondFork = (i + 1) % numberOfPhilosophersAndForks;
			// deadlock-free strategy: swap for every other philosopher
			if (i % 2 == 0) {
				int tmp = firstFork;
				firstFork = secondFork;
				secondFork = tmp;
			}
			philosophers[i] = new Philosopher(i, firstFork, secondFork);
		}
		// say 'bon appetit'
		ExecutorService exec = Executors.newFixedThreadPool(numberOfPhilosophersAndForks);
		for (int i = 0; i < numberOfPhilosophersAndForks; i++) {
			exec.execute(philosophers[i]);
		}
	}
}
