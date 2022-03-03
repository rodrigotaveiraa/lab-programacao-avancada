import java.net.*;
import java.io.*;

public class Client {

    public final static int PORT = 2222;

    public static void main(String[] args) throws Exception {

        String hostname = args.length > 0 ? args[0] : "localhost";
        Socket server = new Socket(hostname, PORT);
        ObjectInputStream entrada = new ObjectInputStream(server.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
        int serverData = 0;
        serverData = (int) entrada.readObject();
        System.out.println(serverData);
        out.writeObject((serverData + 1));
        System.out.println(serverData + 1);
        serverData = (int) entrada.readObject();
        System.out.println(serverData);
        out.close();
        entrada.close();
        server.close();
    }
}