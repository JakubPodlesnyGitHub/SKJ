import ServerUDP.ServerUDP;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        List<Integer> ports = new ArrayList<>();
        for (String arg : args) {
            ports.add(Integer.parseInt(arg));
        }
        ServerUDP serverUDP = new ServerUDP(ports, InetAddress.getByName("localhost"));
        serverUDP.start();
    }
}
