import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocketSerwer = new DatagramSocket(20005);
        byte [] reciveDATA = new byte[2000];

        while(true){
            DatagramPacket datagramRecivePacket = new DatagramPacket(reciveDATA,reciveDATA.length);
            //////////////////////////OTRZYMANIE DATAGRAM-PACKET/////////////////////////////////////
            datagramSocketSerwer.receive(datagramRecivePacket);
            /////////////////////////GET PORT I ADDSRESS//////////////////////////////////////////
            int port = datagramRecivePacket.getPort();
            InetAddress ipAddress = datagramRecivePacket.getAddress();
            ////////////////MOJA S////////////////////////////////////
            byte [] mySTAB = "20540".getBytes();
            DatagramPacket sendMySPacket = new DatagramPacket(mySTAB, mySTAB.length,ipAddress,port);
            datagramSocketSerwer.send(sendMySPacket);
            ////////////////NUMER////////////////////////////////
            byte [] singleNumberLine = "281363".getBytes();
            DatagramPacket sendMyNumber = new DatagramPacket(singleNumberLine, singleNumberLine.length,ipAddress,port);
            datagramSocketSerwer.send(sendMyNumber);
            ////////////////////OTRZYMANIE N///////////////////////
            datagramSocketSerwer.receive(datagramRecivePacket);
            byte [] receivedMessageN = datagramRecivePacket.getData();
            String numberN = new String(receivedMessageN,0,datagramRecivePacket.getLength());
            //////////////////OTRZYMYWANIE X/////////////////////
            datagramSocketSerwer.receive(datagramRecivePacket);
            byte [] receivedMessageX = datagramRecivePacket.getData();
            String lineX = new String(receivedMessageX,0,datagramRecivePacket.getLength());
            System.out.println(lineX+ " " + numberN);
        }
        //datagramSocketSerwer.close();
        //System.out.println("DatagramSocket: " +  datagramSocketSerwer.isClosed());
    }
}
