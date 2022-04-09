package Connection;

import ServerTCP.ServerTCP;
import StructureForClinets.StructureForClients;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ConnectionThreadUDP extends Thread {
    private DatagramSocket datagramSocket;
    private StructureForClients structureForClients;
    private byte[] reciveData;

    public ConnectionThreadUDP(DatagramSocket datagramSocket, StructureForClients structureForClients) {
        this.datagramSocket = datagramSocket;
        this.structureForClients = structureForClients;
        reciveData = new byte[100];
    }

    @Override
    public void run() {
        DatagramPacket datagramReceivePacket = new DatagramPacket(reciveData, reciveData.length);
        while (true) {
            System.out.println("(UDP) SERVER OCZEKUJE NA KLIENTA NA PORCIE: " + datagramSocket.getLocalPort());
            try {
                ///////////OTRZYMANIE PAKIETU///////////////////////
                datagramSocket.receive(datagramReceivePacket);
                /////////SPRAWDZENIE JEDNEGO PAKIETU i JESLI SEI WSZYSTKO ZGADZA UTWORZENIE SERWERA TCP////////
                checkOnePacket(datagramReceivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void receivedSetFirstWord(DatagramPacket datagramPacket, Connection connection) throws IOException {
        byte[] receivedFIRST = datagramPacket.getData();
        String receivedFirstWord = new String(receivedFIRST, 0, datagramPacket.getLength());
        connection.setFirstWord(receivedFirstWord);
    }

    public void receivedSetSecondWord(DatagramPacket datagramPacket, Connection connection) throws IOException {

        byte[] receivedSecond = datagramPacket.getData();
        String receivedSecondWord = new String(receivedSecond, 0, datagramPacket.getLength());
        connection.setSecondWord(receivedSecondWord);
    }

    public void receivedSetThirdWord(DatagramPacket datagramPacket, Connection connection) throws IOException {
        byte[] receivedThird = datagramPacket.getData();
        String receivedThirdWord = new String(receivedThird, 0, datagramPacket.getLength());
        connection.setThirdWord(receivedThirdWord);
    }

    public void createTCPServer(Connection connection) {
        ServerTCP serverTCP = null;
        try {
            serverTCP = new ServerTCP();
            System.out.println(serverTCP.getLocalPort());
            String tcpPort = String.valueOf(serverTCP.getLocalPort());
            byte[] tcpPortToSend = tcpPort.getBytes();
            datagramSocket.send(new DatagramPacket(tcpPortToSend, tcpPortToSend.length, connection.getInetAddressClient(), connection.getPortClient()));
            serverTCP.start();
        } catch (IOException e) {
            System.err.println("!!!SERWER NIE POWSTAL Z POWODU ZAJETEGO SOCKETU!!!");
            e.printStackTrace();
        }
    }

    public void checkOnePacket(DatagramPacket datagramPacket) throws IOException {
        InetAddress inetClientAddress = datagramPacket.getAddress();
        int clientPort = datagramPacket.getPort();
        Connection particularClient = structureForClients.findOneClient(inetClientAddress, clientPort);
        if (particularClient == null) {
            particularClient = new Connection(inetClientAddress, clientPort);
            structureForClients.addNewConnection(particularClient);
        }
        byte[] receivedData = datagramPacket.getData();
        String receivedWord = new String(receivedData, 0, datagramPacket.getLength());
        System.out.println("Pakiet od klienta o numerze portu: " + clientPort + ". Pakiet: " + receivedWord);
        particularClient.increaseCounter();
        switch (particularClient.getCounter()) {
            case 1:
                receivedSetFirstWord(datagramPacket, particularClient);
                break;
            case 2:
                receivedSetSecondWord(datagramPacket, particularClient);
                break;
            case 3:
                receivedSetThirdWord(datagramPacket, particularClient);
                break;
        }
        if (particularClient.makeConnection()) {
            createTCPServer(particularClient);
            structureForClients.removeConnection(particularClient);
        }
    }
}
