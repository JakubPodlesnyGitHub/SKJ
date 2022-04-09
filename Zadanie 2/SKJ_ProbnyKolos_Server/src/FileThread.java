import java.io.*;
import java.net.Socket;

public class FileThread  extends Thread{
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String fileName = "APDF.pdf";
    public FileThread(Socket socket){
        this.socket = socket;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            byte [] tablica = new byte[60];
            int lengthFile = fileInputStream.read(tablica);
            while (lengthFile > 0){
                outputStream.write(tablica,0,lengthFile);
                lengthFile = fileInputStream.read(tablica);
            }
            socket.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
