import java.io.*;
import java.net.Socket;

public class Client extends Thread{
    @Override
    public void run() {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost",7878);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String portServer = bufferedReader.readLine();
            takeFile(Integer.parseInt(portServer));
            printWriter.println("DZIEN DOBRY NAZWYWAM SIE JAKUB PODLESNY" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeFile(int portServer) {
        try {
            Socket socket = new Socket("localhost", portServer);
            InputStream inputPlik = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("APDF.pdf");
            byte[] tablica = new byte[60];
            int length = inputPlik.read(tablica);
            while (length > 0) {
                System.out.println(new String(tablica));
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
