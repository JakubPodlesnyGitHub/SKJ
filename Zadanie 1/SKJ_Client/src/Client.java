import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    //private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    public Client(){
        try {
            socket = new Socket("localhost",7878);
            //printWriter = new PrintWriter(socket.getOutputStream(),true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("SERVER OSELSAL ODPOWIEDZ -- > "+ bufferedReader.readLine());
            System.out.println("SERVER OSELSAL ODPOWIEDZ -- > "+ bufferedReader.readLine());
            System.out.println("SERVER OSELSAL ODPOWIEDZ -- > "+ bufferedReader.readLine());
            String lineFromServerBufferedReaderPORT = bufferedReader.readLine();
            takeFile(Integer.parseInt(lineFromServerBufferedReaderPORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeFile(int portNumber) throws IOException {
        try {
            Socket socket = new Socket("localhost", portNumber);
            InputStream inputPlik = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("APDF.pdf");
            byte[] tablica = new byte[69];
            int length = inputPlik.read(tablica);
            while (length > 0) {
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
