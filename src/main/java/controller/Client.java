package controller;

import javax.swing.*;

public class Client extends JFrame {
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private final Server server;

    public Client(Server server) {
        this.server = server;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);

    }

    public void setStatus() {

    }
}
