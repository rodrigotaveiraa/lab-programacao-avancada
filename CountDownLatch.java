import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

class Processor implements Runnable {
    private CountDownLatch latch;
    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }
    public void run() {
        System.out.println("abriu o ferrolho - " + latch.getCount());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        latch.countDown();
        System.out.println("nao abriu o ferrolho - " + latch.getCount());
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Digite o valor inicial do ferrolho:");
        input = new Scanner(System.in);
        int numberLatch = input.nextInt();

        CountDownLatch latch = new CountDownLatch(numberLatch);

        System.out.println("Thread principal antes do await...");
        ExecutorService executor = Executors.newFixedThreadPool(2*numberLatch);
        for (int i = 0; i < numberLatch ; i++) {
            executor.submit(new Processor(latch));
        }
        executor.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread principal depois do await...");
    }
}