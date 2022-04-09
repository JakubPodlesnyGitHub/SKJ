import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private Scanner scanner;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public Client() {
        try {
            this.socket = new Socket("localhost", 7878);
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessagesToServer() {

        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Podaj wiadomosc dla servera: ");
            String message = scanner.nextLine();
            printWriter.println(message);
            try {
                String lineFromServerBufferedReader = bufferedReader.readLine();
                //System.out.println(lineFromServerBufferedReader);
                takeFile(Integer.parseInt(lineFromServerBufferedReader),message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void takeFile(int portNumber,String fileName) {
        try {
            Socket socket = new Socket("localhost", portNumber);
            InputStream inputPlik = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            byte[] tablica = new byte[20];
            int length = inputPlik.read(tablica);
            while (length > 0) {
                //System.out.println(new String(tablica));
                fileOutputStream.write(tablica, 0, length);
                length = inputPlik.read(tablica);
            }
            socket.close();
            fileOutputStream.close();
            inputPlik.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
