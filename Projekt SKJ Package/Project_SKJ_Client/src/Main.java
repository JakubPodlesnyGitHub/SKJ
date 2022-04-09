import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        InetAddress serverAddress = InetAddress.getByName(args[0]);
        List<Integer> ports = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            ports.add(Integer.parseInt(args[i]));
        }
        Client client1 = new Client(serverAddress,ports);
        client1.start();
        //Client client2 = new Client(serverAddress,ports);
        //client2.start();
        //Client client3 = new Client(serverAddress,ports);
        //client3.start();
    }
}
