import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private boolean ifEnd = false;

    public ClientThread(Socket clientSocket) throws IOException {
        printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (ifEnd == false) {
                String line = bufferedReader.readLine();
                checkLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void checkLine(String line) {
        if (line.startsWith(CommandGetFile.getKeyWord())) {
            sendFILE(line);
        } else if (line.startsWith(CommandEnd.getKeyWord())) {
            ifEnd = true;
        }
    }

    public void sendFILE(String line) {
        String[] tablicaFile;
        tablicaFile = line.split(" ");
        String fileName = tablicaFile[1];
        CommandGetFile comandGetFile = new CommandGetFile(fileName);
        giveFileToClinet(comandGetFile);
    }

    public void giveFileToClinet(Commands comands) {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            System.out.println("Server:  " + serverSocket);
            printWriter.println(serverSocket.getLocalPort());
            Socket connection = serverSocket.accept();
            FileThread fileThread = new FileThread(connection, comands.getArgument());
            fileThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
