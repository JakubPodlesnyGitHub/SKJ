import java.io.*;
import java.net.Socket;

public class FileThread extends Thread {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String fileName;

    public FileThread(Socket socket, String fileName) {
        this.socket = socket;
        try {
            this.fileName = fileName;
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            byte[] tablica = new byte[20];
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
