import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private BufferedReader bufferedReaderIN;
    private PrintWriter printWriterOUT;

    public ClientThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        bufferedReaderIN = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        printWriterOUT = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = bufferedReaderIN.readLine();
                System.out.println("Client: " + clientSocket + "przeysla message: " + message);
                fileToClient();
            } catch (IOException e) {
                System.err.println("!!! ROZLACZENIE KLIENTA !!!" + e.getMessage());
                try {
                    clientSocket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }


    public void fileToClient() throws IOException {
        ServerSocket newServerSocket = new ServerSocket(0);
        printWriterOUT.println(newServerSocket.getLocalPort());
        Socket socektConnect = newServerSocket.accept();
        FileThread fileThread = new FileThread(socektConnect);
        fileThread.start();
    }
}