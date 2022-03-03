import java.net.*;
import java.io.*;

public class Client {
  public final static int PORT = 9000;

  public static void main(String[] args) throws Exception {
    String hostname = args.length > 0 ? args[0] : "localhost";
    Socket server = new Socket(hostname, PORT);
    int[] vetA = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    int[] vetB = {20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
    ObjectOutputStream saida = new ObjectOutputStream (server.getOutputStream());
    saida.writeObject(new Vetor(vetA, vetB));
    ObjectInputStream entrada = new ObjectInputStream (server.getInputStream());
    int serverData = (int) entrada.readObject();
    System.out.println(serverData);
    entrada.close();
    saida.close();
    server.close();
  }
}