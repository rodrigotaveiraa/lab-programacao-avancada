public class Relogio implements Runnable {
    private ContadorTempo contadorTempo;
    private int qtTicks;
    private int inicialTick;

    Relogio(int qtTicks, int inicialTick) {
        this.qtTicks = qtTicks;
        this.inicialTick = inicialTick;
        contadorTempo = new ContadorTempo(inicialTick);
    }

    Relogio(int qtTicks) {
        this.qtTicks = qtTicks;
        this.inicialTick = 0;
        contadorTempo = new ContadorTempo();
    }

    Relogio() {
        this.qtTicks = 0;
        this.inicialTick = 0;
        contadorTempo = new ContadorTempo();
    }

    public void run() {
        int i;
        System.out.println(contadorTempo.getTick());
        try {
            for (i = 0; i < this.qtTicks; i++) {
                contadorTempo.nextTick();
                Thread.sleep(1000);
                System.out.println(contadorTempo.getTick());
            }
        } catch (InterruptedException e) {}
    }
}