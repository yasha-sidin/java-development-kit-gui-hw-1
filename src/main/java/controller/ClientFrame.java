package controller;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class ClientFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private static List<String> messages = new LinkedList<>();
    private final Server server;
    private final Client client;

    public ClientFrame(Client client, Server server) {
        this.server = server;
        this.client = client;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);


        pack();
        setVisible(true);
    }



    private void checkStatus() {
        if (!client.isIsConnectedToServer()) JOptionPane.showMessageDialog(this, "Can't connect to server.", "Server error", JOptionPane.ERROR_MESSAGE);
    }
}
