import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientConnectionThread extends Thread {
    private Socket kkSocket;
    private int port;
    private InetAddress addressServer;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public ClientConnectionThread(int port, InetAddress addressServer) throws IOException {
        this.port = port;
        this.addressServer = addressServer;
        kkSocket = new Socket(addressServer, port);
        printWriter = new PrintWriter(kkSocket.getOutputStream(), true);
        bufferedReader = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String line1 = bufferedReader.readLine();
            System.out.println("POLECENIE OD SERWERA: " + line1);
            printWriter.println("CZESC");
            System.out.println("ODPOWIEDZ ZOSTALA WYSLANA: " + "CZESC");
            String line2 = bufferedReader.readLine();
            System.out.println("ODPOWIEDZ OD SERWERA: " + line2);
            kkSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
