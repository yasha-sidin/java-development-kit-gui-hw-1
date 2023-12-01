package controller;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

public class ClientFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private static boolean isConnectedToServer = false;
    private final Server server;
    private final Client client;

    public ClientFrame(Client client, Server server) {
        this.server = server;
        this.client = client;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
    }

    public void setStatus(boolean serverStatus) {
        isConnectedToServer = serverStatus;
    }

    private void checkStatus() {
        if (!isConnectedToServer) J
    }
}
