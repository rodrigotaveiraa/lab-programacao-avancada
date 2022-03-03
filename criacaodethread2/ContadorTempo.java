public class ContadorTempo {
    private int tick;

    ContadorTempo(int tick) {
        this.tick = tick;
    }

    ContadorTempo() {
        this.tick = 0;
    }

    void nextTick() {
        this.tick += 1;
    }

    int getTick() {
        return this.tick;
    }
}