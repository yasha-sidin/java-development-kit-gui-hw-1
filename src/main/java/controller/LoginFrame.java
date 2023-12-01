package controller;

import factories.ColorFactory;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 350;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private Server server;
    public LoginFrame(Server server) {
        this.server = server;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setBackground(ColorFactory.getMain());
        setLayout(new GridLayout(3, 1));


    }
}
