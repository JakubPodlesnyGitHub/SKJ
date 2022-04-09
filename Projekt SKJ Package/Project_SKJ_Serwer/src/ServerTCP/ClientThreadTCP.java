package ServerTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThreadTCP extends Thread {
    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private boolean ifEnd = false;

    public ClientThreadTCP(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        while (!ifEnd) {
            try {
                printWriter.println("( TCP ) Przywitaj sie");
                String line = bufferedReader.readLine();
                System.out.println("( TCP) Odpowiedz od klienta o numerze portu: " + clientSocket.getPort() + " Odpowiedz: "  + line);
                printWriter.println("Witam");
                ifEnd = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (clientSocket != null) {
            try {
                Thread.sleep(5000);
                System.out.println("(TCP) Zamykanie klienta o numerze portu : " + clientSocket.getPort());
                clientSocket.close();
                System.out.println("(TCP) Klient: " + clientSocket.getPort() + " zostal zamkniety!!!");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
