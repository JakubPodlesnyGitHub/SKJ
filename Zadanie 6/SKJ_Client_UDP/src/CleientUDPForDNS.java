import java.io.IOException;
import java.net.*;

public class CleientUDPForDNS {
    public static void main(String[] args) throws IOException {
        log("Client starts");

        log("\n");
        log("Packet creation");
        byte[] doWyslania = {0x08, 0x54, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03, 0x77, 0x77, 0x77, 0x02, 0x77, 0x70, 0x02, 0x70, 0x6c, 0x00, 0x00, 0x01, 0x00, 0x01};
        String dnsName = "8.8.8.8";
        InetAddress dnsAddress = InetAddress.getByName(dnsName);
        int dnsPort = 53;

        //GOTOWA PACZKA
        DatagramPacket datagramPacketTOSend = new DatagramPacket(doWyslania, doWyslania.length, dnsAddress, dnsPort);

        log("\n");
        log("UDP (Datagram) SOCKET OPENING");
        DatagramSocket datagramSocket = new DatagramSocket();
        log("UDP (Datagram) SOCKET OPENED");

        log("\n");
        log("UDP (Datagram) PACKET SENDING");
        datagramSocket.send(datagramPacketTOSend);
        log("UDP (Datagram) PACKET SEND");

        log("\n");
        log("UDP (Datagram) RESPONSE RECIVING");
        DatagramPacket datagramPacketTORecive = new DatagramPacket(new byte[2000], 1500);
        datagramSocket.receive(datagramPacketTORecive);
        log("UDP (Datagram) RESPONSE RECIVED");

        log("\n");

        log("///////////////////////////////////////////////////////////////////////");
        log("UDP (Datagram) RESPONSE RECIVED FROM :" + datagramPacketTORecive.getAddress() + ": " + datagramPacketTORecive.getPort() );

        byte[] data = datagramPacketTORecive.getData();
        int length = datagramPacketTORecive.getLength();

        String dataStr = new String(data,0,length);
        String text = "";

        for (int i = 0; i < length ; i++) {
            text = text + " " + data[i];
        }

        log("DATE RECIVED: " + dataStr);
        log("DATE RECIVED BYTES: " + text);
        log("///////////////////////////////////////////////////////////////");

        log("\n");
        log("UDP (Datagram) SOCKET CLOSING");
        datagramSocket.close();
        log("UDP (Datagram) SOCKET CLOSED");

        log("\n");
        log("Client ends");
    }

    public static void log(String message) {
        System.out.println("[C]: " + message);
    }
}

