import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        log("Client starts");
        ///////////////////////////////////////////////////////////////
        String serverIP = "172.21.48.174";
        int serverPort =10005;
        InetAddress inetAddressServer = InetAddress.getByName(serverIP);
        ////////////////////DANE////////////////////////////////
        String dataToSEND = "471615";
        byte [] dataToSendByteTab = dataToSEND.getBytes();
        ////////////////STWORZENIE PACZKI DO WYSLANIA//////////////////////////
        DatagramPacket datagramPacketTOSEND = new DatagramPacket(dataToSendByteTab, dataToSendByteTab.length,inetAddressServer,serverPort);
        //////////////OTWARCIE SOCKETU/////////////////////////
        log("UDP (Datagram) SOCKET OPENING");
        DatagramSocket datagramSocket = new DatagramSocket();
        log("UDP (Datagram) SOCKET OPENED");
        /////////////WYSYLANIE PACZKI DO SERVERA//////////////////////////
        log("UDP (Datagram) PACKET SENDING");
        datagramSocket.send(datagramPacketTOSEND);
        log("UDP (Datagram) PACKET SEND");
        //////////////OTRZYMANIE WIADOMOSCI Z SERVERA////////////////////////
        log("UDP (Datagram) RESPONSE RECIVING");
        DatagramPacket datagramPacketTORecive = new DatagramPacket(new byte[100], 100);
        datagramSocket.receive(datagramPacketTORecive);
        log("UDP (Datagram) RESPONSE RECIVED");
        //////////////////WYPISANIE WIADOMOSCI NA EKRAN///////////////////////////////
        byte [] messageFROMServer = datagramPacketTORecive.getData();
        String receivedTEXT = new String(messageFROMServer,0,datagramPacketTORecive.getLength());
        System.out.println("\nWIADOMOSC OD SERVERA: " + receivedTEXT);
        ///////////////////////////////////////////////////////////////////////
        log("UDP (Datagram) SOCKET CLOSING");
        datagramSocket.close();
        log("UDP (Datagram) SOCKET CLOSED");

        log("Client ends");
    }
    public static void log(String message) {
        System.out.println("\nMESSAGE: " + message);
    }
}
