import java.util.concurrent.locks.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	static Lock lock1 = new ReentrantLock();
	static Lock lock2 = new ReentrantLock();

	public static void acquire(Lock firstLock, Lock secondLock) throws InterruptedException {
		while (true) {
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			} 
			finally {
				if (gotFirstLock && gotSecondLock) return;
				else if (gotFirstLock) firstLock.unlock();
				else if (gotSecondLock) secondLock.unlock();
			}
		}
	}
	
	public static void release(Lock firstLock, Lock secondLock) {
			firstLock.unlock();
			secondLock.unlock();
	}

	static class Thread1 extends Thread
	{
		public void run() {
			System.out.println("Thread T1: tentando adquirir os bloqueios");
			try {
				acquire(lock1, lock2);
				System.out.println("Thread T1: bloqueios adquiridos!");
				Thread.sleep(1);
				release(lock1, lock2);
			} catch (InterruptedException e) {}
			System.out.println("Thread T1: finalizou e liberou os bloqueios");
		}
	}

	static class Thread2 extends Thread
	{
		public void run() {
			System.out.println("Thread T2: tentando adquirir os bloqueios");
			try {
				acquire(lock2, lock1);
				System.out.println("Thread T2: bloqueios adquiridos!");
				Thread.sleep(1);
				release(lock2, lock1);
			} catch (InterruptedException e) {}
			System.out.println("Thread T2: finalizou e liberou os bloqueios");
		}
	}

	public static void main(String arg[]) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            executor.submit(new Thread1());
            executor.submit(new Thread2());
        }

        executor.shutdown();
		System.out.println("Terminou");
	}
}