package ServerUDP;

import Connection.ConnectionThreadUDP;
import StructureForClinets.StructureForClients;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;

public class ServerUDP {
    private List<Integer> ports;
    private InetAddress inetAddress;

    public ServerUDP(List<Integer> ports, InetAddress inetAddress) {
        this.ports = ports;
        this.inetAddress = inetAddress;
    }

    public void start() {
        DatagramSocket datagramSocket;
        for (int i = 0; i < ports.size() ; i++) {
            try {
                datagramSocket = new DatagramSocket(ports.get(i),inetAddress);
                ConnectionThreadUDP connectionThreadUDP = new ConnectionThreadUDP(datagramSocket,new StructureForClients(ports.get(i)));
                connectionThreadUDP.start();
            } catch (SocketException e) {
                System.err.println("!!!SOCKET JEST ZAJETY. Z TEGO POWODU DatagramSocket NIE UTWORZYL SIE!!!");
                e.printStackTrace();
            }

        }
    }
}
