import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

class Deposito {
    private int qtItens;
    private int capacidade;

    Deposito(int capacidade) {
        this.capacidade = capacidade;
    }

    synchronized void armazenar() {
        if (this.qtItens < this.capacidade) {
            this.qtItens += 1;
            System.out.println("Armazenado - Caixas no Deposito: "+ this.getQtItens());
            System.out.println("Caixa armazenada");
            System.out.println("");
        } else {
            System.out.println("Nao consegui armazenar a caixa");
            System.out.println("");
        }
    }

    synchronized void retirar() {
        if (this.qtItens > 0) {
            this.qtItens -= 1;
            System.out.println("Retirada - Caixas no Deposito: "+ this.getQtItens());
            System.out.println("Caixa retirada");
            System.out.println("");
        } else {
            System.out.println("Nao consegui retirar a caixa");
            System.out.println("");
        }
    }

    int getQtItens() {
        return this.qtItens;
    }
}

class Produtor implements Runnable {
    private Deposito dep;
    private int intervalo;

    Produtor(Deposito dep, int intervalo) {
        this.dep = dep;
        this.intervalo = intervalo;
    }

    public void run() {
        try {
            System.out.println("Tentando armazenar uma caixa");
            dep.armazenar();
            Thread.sleep(this.intervalo);
        } catch (Exception e) {}
    }
}

class Consumidor implements Runnable {
    private Deposito dep;
    private int intervalo;

    Consumidor(Deposito dep, int intervalo) {
        this.dep = dep;
        this.intervalo = intervalo;
    }

    public void run() {
        try {
            System.out.println("Tentando retirar uma caixa");
            dep.retirar();
            Thread.sleep(this.intervalo);
        } catch (Exception e) {}
    }
}

public class Main {

    public static void main (String[] args) {
        Random random = new Random();
        Deposito dep = new Deposito(4);

        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        for (int i = 0; i < 10; i++) {
            executor.submit(new Produtor(dep,random.nextInt(200)));
        }

        for (int i = 0; i < 10; i++) {
            executor.submit(new Consumidor(dep,random.nextInt(200)));
        }

        for (int i = 0; i < 10; i++) {
            executor.submit(new Produtor(dep,random.nextInt(200)));
        }

        for (int i = 0; i < 10; i++) {
            executor.submit(new Consumidor(dep,random.nextInt(200)));
        }
        executor.shutdown(); 
    }
} 