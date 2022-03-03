import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CarroMontanhaRussa extends Thread {
	private CyclicBarrier barrier;
	
	public CarroMontanhaRussa (CyclicBarrier barrier) {
        this.barrier = barrier;
    }
	
	public void run() {
        String threadName = Thread.currentThread().getName();
        try {
          System.out.println(threadName + " esperando: " + barrier.getNumberWaiting());
          barrier.await();
          System.out.println(threadName + " entrou no carro");
        } catch (InterruptedException | BrokenBarrierException e) { }
     }
}

public class Main {
    public static final int C = 10;
    public static final int N = 20;


	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(C);
		for (int i = 0; i < N; i++) {
            Thread worker = new CarroMontanhaRussa(barrier);
            worker.setName("Thread " + i);
            worker.start();
		}
		System.out.println("iniciando a volta...");
		try { Thread.sleep(5000); } catch(InterruptedException e) {}
		System.out.println("volta foi completada");
	}

    /*          thread pool
    public static void main(String[] args) throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(C);
		ExecutorService executor = Executors.newFixedThreadPool(N);

        for (int i = 0; i < N; i++) {
            executor.submit(new CarroMontanhaRussa(barrier));
        }

        executor.shutdown();
		System.out.println("iniciando a volta...");
		try { Thread.sleep(5000); } catch(InterruptedException e) {}
		System.out.println("volta foi completada");
	}
    */

    /*      valor de K
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(C);
		ExecutorService executor = Executors.newFixedThreadPool(15);
        
        for (int i = 0; i < 15; i++) {
            executor.submit(new CarroMontanhaRussa(barrier));
        }
        
        executor.shutdown();
		System.out.println("iniciando a volta...");
		try { Thread.sleep(5000); } catch(InterruptedException e) {}
		System.out.println("volta foi completada");
	}
    */

}
