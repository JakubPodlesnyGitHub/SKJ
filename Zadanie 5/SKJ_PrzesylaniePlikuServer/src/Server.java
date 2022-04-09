import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public Server() throws IOException {
        serverSocket = new ServerSocket(7878);
    }
    public void startServer() {
        Socket clientSocket = null;
        while(true){
            try {
                clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
