
public class Main {

    public static void main(String[] args) {
        Relogio relogio = new Relogio(10);
        Thread thread = new Thread(relogio);
        thread.start();
    }
}