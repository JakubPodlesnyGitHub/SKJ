import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public Server() throws IOException {
        serverSocket = new ServerSocket(7878);
    }
    public void startServer() throws IOException {
        //Tworze socket dla klienta (SOCKET  - POLACZENIE POMIEDZY KLIENTEM A SERWEREM)
        Socket clientSocket;
        while(true){ // SERVER DZIALA CALY CZAS (NASLUCHUJE)
            System.out.println("!!SERVER CZEKA NA POLACZENIE!!");
            clientSocket = serverSocket.accept(); // OCZEKIWANIE NA POLACZENIE I ZAKCEPTOWANIE SOCKETU(KLIENTA)
            ClientThread clientThread = new ClientThread(clientSocket);//TWORZENIE WATKU DLA AKCJI KLIENTA
            clientThread.start();
        }
    }

}
