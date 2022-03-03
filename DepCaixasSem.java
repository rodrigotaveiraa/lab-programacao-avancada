
class Deposito {
    private int qtItens = 0;
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

public class DepCaixasSem {

    public static final int MAX = 5;

    static class Produtor implements Runnable {
        private Deposito dep;
        private int intervalo;

        Produtor(Deposito dep, int intervalo) {
            this.dep = dep;
            this.intervalo = intervalo;
        }

        public void run() {
            try {
                int i;
                for (i = 0; i < MAX ; i++) {
                    System.out.println("Tentando armazenar uma caixa");
                    dep.armazenar();
                    Thread.sleep(this.intervalo);
                }
            } catch (Exception e) {}
        }
    }

    static class Consumidor extends Thread {
        private Deposito dep;
        private int intervalo;

        Consumidor(Deposito dep, int intervalo) {
            this.dep = dep;
            this.intervalo = intervalo;
        }

        public void run() {
            try {
                int i;
                for (i = 0; i < MAX ; i++) {
                    System.out.println("Tentando retirar uma caixa");
                    dep.retirar();
                    Thread.sleep(this.intervalo);
                }
            } catch (Exception e) {}
        }
    }

    public static void main (String[] args) {
        Deposito dep = new Deposito(2);
        Thread prod = new Thread(new Produtor(dep, 100));
        Consumidor cons = new Consumidor(dep,200);
        prod.start();
        cons.start();
    }
} 