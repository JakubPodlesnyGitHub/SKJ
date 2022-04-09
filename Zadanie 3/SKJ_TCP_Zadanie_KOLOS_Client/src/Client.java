import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public Client() throws IOException {
        log("Stworzenie gniazda serwera i przypisanie mu portu");
        Socket clientSocket = new Socket("172.21.48.181", 20003);


        log("Zapis do strumienia");
        PrintWriter printWriter1 = new PrintWriter(clientSocket.getOutputStream());
        PrintWriter printWriter2 = new PrintWriter(clientSocket.getOutputStream());

        printWriter1.println("20741");
        printWriter1.flush();
        printWriter2.println("172.23.129.152:20056");
        printWriter2.flush();

    }

    public static void log(String text) {
        System.out.println("[C]: " + text);
    }


}
