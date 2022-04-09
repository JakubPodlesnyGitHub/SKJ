import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public Server() throws IOException {
        serverSocket = new ServerSocket(7878);
    }
    public void startServer() throws IOException {
        Socket clientSoceket = null;
        while(true){
            System.out.println("!!!SERVER CZEKA NA POLACZENIE ...!!!");
            clientSoceket = serverSocket.accept();
            System.out.println("!!! SERVER ODEBRAL NOWE POLACZENIE/KLIENT  !!!" + clientSoceket);
            ClientThread clientThread = new ClientThread(clientSoceket);
            clientThread.run();
        }
    }
}
