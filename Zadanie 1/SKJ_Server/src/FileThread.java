import java.io.*;
import java.net.Socket;

public class FileThread extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private File fileToSend;

    public FileThread(Socket socket,File fileToSend){
        this.socket = socket;
        this.fileToSend = fileToSend;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileToSend);
            byte[] tablica = new byte[69];// TWORZENIE TABLICY(PACZEK) KTORYMI WEDRUJE PLIK
            int length = fileInputStream.read(tablica);
            while (length > 0) {
                outputStream.write(tablica, 0, length);
                length = fileInputStream.read(tablica);
            }
            socket.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
