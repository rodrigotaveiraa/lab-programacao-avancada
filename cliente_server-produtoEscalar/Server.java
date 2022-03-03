import java.net.*;
import java.io.*;
import java.lang.*;


class ClientRunnable implements Runnable{
    
    protected Socket clientSocket = null;
    private int indice;
    private Vetor obj;
    
    public ClientRunnable(Socket clientSocket, Vetor obj, int i) {
        this.clientSocket = clientSocket;
        this.obj = obj;
        this.indice = i;
    }
    
    public void run() {
        try {
            obj.setProduto(obj.getProduto() + obj.getX(this.indice) * obj.getY(this.indice));
            if (this.indice + 1 == 20) {
                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                output.writeObject(obj.getProduto());
                output.close();
            }
        } catch (Exception e) {}
    }
}

public class Server {
    public final static int PORT = 9000;
    protected static ServerSocket serverSocket = null;
    
    public static void main(String[] args) throws Exception {
        serverSocket = new ServerSocket(PORT);
        int i;
        while(true) {
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream entrada = new ObjectInputStream (clientSocket.getInputStream());
            Vetor array =  (Vetor) entrada.readObject();

            for(i = 0; i < 20; i++){
                new Thread(new ClientRunnable(clientSocket, array, i)).start();
            }
        }
    }
}    
    
