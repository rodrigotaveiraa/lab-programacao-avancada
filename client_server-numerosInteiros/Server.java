import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.Random;

public class Server {

  public final static int PORT = 2222;

  public static void main(String[] args) throws Exception {
    ServerSocket server = new ServerSocket(PORT);

    Random rand = new Random();

    int f = rand.nextInt();

    Socket client = server.accept();
    InetAddress clientAddr = client.getInetAddress();
    System.out.println("Cliente conectado: " + clientAddr.getHostAddress());
    ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
    ObjectInputStream in = new ObjectInputStream(client.getInputStream());
    System.out.println(f);
    out.writeObject(f);
    int clientData = 0;
    clientData = (int)in.readObject();
    System.out.println(clientData);
    out.writeObject((clientData + 2));
    System.out.println(clientData + 2);
    out.close();
    in.close();
    client.close();
  }
} 