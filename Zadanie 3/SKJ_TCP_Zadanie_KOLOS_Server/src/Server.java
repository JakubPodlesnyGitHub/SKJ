import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() throws IOException {
        log("Stworzenie gniazda serwera i przypisanie mu portu");

        ServerSocket serverSocket = new ServerSocket(20056);
        while (true) {
            Socket socket = serverSocket.accept();

            System.out.println(serverSocket.getInetAddress());

            log("Klient połączony");

            log("Zapis do strumienia");
            PrintWriter printWriter1 = new PrintWriter(socket.getOutputStream());
            PrintWriter printWriter2 = new PrintWriter(socket.getOutputStream());

            printWriter1.println("20741");
            printWriter1.flush();

            printWriter2.println("421061");
            printWriter2.flush();


            log("Odczyt danych ze strumienia");
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(in);
            String N = bufferedReader.readLine();
            String X = bufferedReader.readLine();
            System.out.println("Klient: " + N);
            System.out.println("Klient: " + X);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(N + X);
            printWriter.flush();
        }

    }

    public static void log(String text) {
        System.out.println("[S]: " + text);
    }

}
