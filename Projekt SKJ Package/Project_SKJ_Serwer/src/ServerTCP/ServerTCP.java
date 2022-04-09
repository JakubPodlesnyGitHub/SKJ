package ServerTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    private ServerSocket serverSocket;
    private int localPort;

    public ServerTCP() throws IOException {
        serverSocket = new ServerSocket(0);
        //time out serverSocket
        serverSocket.setSoTimeout(36000);
        localPort = serverSocket.getLocalPort();
    }

    public void start() throws IOException {
        Socket clientSocket;
        clientSocket = serverSocket.accept();
        ClientThreadTCP clientThreadTCP = new ClientThreadTCP(clientSocket);
        clientThreadTCP.start();
        serverSocket.close();
    }

    public int getLocalPort() {
        return localPort;
    }
}
