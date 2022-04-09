import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread  extends Thread{
    private PrintWriter printWriterOUT;//WYSYLANIE WIADOMOSCI DO KLIENTA
    private BufferedReader bufferedReaderIN;//ODBIERANIE WIADOMOSCI
    private String fileName = "1-podlesny.jpg";//NAZWA PLIKU
    File fileToSend = new File(fileName);//TWORZENIE PLIKU
    public ClientThread(Socket clientSocket) throws IOException {
        printWriterOUT = new PrintWriter(clientSocket.getOutputStream(), true);
        bufferedReaderIN = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            giveFileToClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void giveFileToClient() throws IOException {
        ServerSocket serverSocket = new ServerSocket(0);//STWORZENIE NOWEGO SERVERSOCEKETU DLA PRZESYLANIA PLIKU NA PORTCIE 0
        //System.out.println("Server: (4554454545) " + serverSocket);
        // Wysylanie danych z (polecenia) servera do klienta
        printWriterOUT.println("DANE:");
        printWriterOUT.println("NAZWA PLIKU : " + fileName);
        printWriterOUT.println("DLUGOSC PLIKU : " + fileToSend.length());
        //wsyslanie portu lokalnego do klienta aby mogl odzczytac wiadomosci
        printWriterOUT.println(serverSocket.getLocalPort());
        //Stworzenie POLACZENIA POMIEDZY KLINETEM A SERWEREM
        Socket connection = serverSocket.accept();
        FileThread fileThread = new FileThread(connection,fileToSend);
        fileThread.start();
    }
}
