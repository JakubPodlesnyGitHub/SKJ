package StructureForClinets;

import Connection.Connection;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class StructureForClients {
    private List<Connection> connectionList;
    private int port;
    public StructureForClients(int port) {
        connectionList = new ArrayList<>();
        this.port = port;
    }

    public void addNewConnection(Connection connection) {
        if (!isContainsConnection(connection))
            connectionList.add(connection);
    }

    public void removeConnection(Connection connection) {
        if (isContainsConnection(connection)) {
            connectionList.remove(connection);
        } else {
            System.err.println("!!!NIE MOZNA USUNAC PONIEWAZ NIE MA TAKIEGO POLOCZENIA!!!");
        }
    }

    public boolean isContainsConnection(Connection connection) {
        if (connectionList.contains(connection)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        if (connectionList.isEmpty())
            return true;
        else
            return false;
    }

    public Connection findOneClient(InetAddress inetAddress,int port){
        for (Connection c :connectionList) {
            if(c.getInetAddressClient().equals(inetAddress) && c.getPortClient() == port){
                return c;
            }
        }
        return null;
    }

}
