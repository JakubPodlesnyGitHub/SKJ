package Connection;

import ServerUDP.ConnectionWordsUDP;

import java.net.InetAddress;

public class Connection {
    private int portClient;
    private InetAddress inetAddressClient;
    private String firstWord;
    private String secondWord;
    private String thirdWord;
    private int counter;

    public Connection(InetAddress inetAddressClient, int portClient) {
        this.portClient = portClient;
        this.inetAddressClient = inetAddressClient;
        counter = 0;
    }

    public boolean makeConnection() {
        if (counter == 3) {
            if (firstWord.startsWith(ConnectionWordsUDP.word1) && secondWord.startsWith(ConnectionWordsUDP.word2) && thirdWord.startsWith(ConnectionWordsUDP.word3)) {
                System.out.println("Nawiazno polaczenie z klientem: " + portClient);
                return true;
            } else {
                System.err.println("!!!BRAK WSZYSTKICH WYMAGANYCH PAKIETOW OD KLIENTA: " + portClient + "!!!");
                return false;
            }
        } else {
            return false;
        }
    }

    public int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        counter++;
    }

    public int getPortClient() {
        return portClient;
    }

    public InetAddress getInetAddressClient() {
        return inetAddressClient;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    public void setThirdWord(String thirdWord) {
        this.thirdWord = thirdWord;
    }
}
