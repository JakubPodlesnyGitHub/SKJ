import java.io.IOException;
import java.net.*;
import java.util.List;

public class Client extends Thread {
    private InetAddress serverAddress;
    private List<Integer> ports;
    private DatagramSocket datagramSocket;

    public Client(InetAddress serverAddress, List<Integer> ports) throws SocketException {
        this.serverAddress = serverAddress;
        this.ports = ports;
        datagramSocket = new DatagramSocket();
        datagramSocket.setSoTimeout(5000);
    }

    @Override
    public void run() {
        for (int i = 0; i < ports.size(); i++) {
            try {
                byte[] firstWord = "SA".getBytes();
                DatagramPacket datagramFirstPacket = new DatagramPacket(firstWord, firstWord.length, serverAddress, ports.get(i));
                datagramSocket.send(datagramFirstPacket);

                byte[] secondWord = "SB".getBytes();
                DatagramPacket datagramSecondPacket = new DatagramPacket(secondWord, secondWord.length, serverAddress, ports.get(i));
                datagramSocket.send(datagramSecondPacket);

                byte[] thirdWord = "SC".getBytes();
                DatagramPacket datagramThirdPacket = new DatagramPacket(thirdWord, thirdWord.length, serverAddress, ports.get(i));
                datagramSocket.send(datagramThirdPacket);

                byte[] tmp = new byte[2000];
                DatagramPacket datagramTMPPacket = new DatagramPacket(tmp, tmp.length);
                datagramSocket.receive(datagramTMPPacket);
                String portTCPFromServer = new String(tmp, 0, datagramTMPPacket.getLength());
                System.out.println("NUMER PORTU TCP: " + portTCPFromServer);

                ClientConnectionThread clientConnectionThread = new ClientConnectionThread(Integer.parseInt(portTCPFromServer), serverAddress);
                clientConnectionThread.start();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
