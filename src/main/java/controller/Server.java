package controller;

import factories.ColorFactory;
import factories.FontFactory;
import model.FileModel;
import model.iGetModel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;

public class Server extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private final JLabel mainLabel;
    private final JLabel statusLabel;
    private final List<Client> clientsList = new LinkedList<>();
    private final iGetModel<String> model;
    private boolean isActive = false;
    public Server(iGetModel<String> model) {
        this.model = model;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setBackground(ColorFactory.getMain());
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 5, 80));
        mainLabel = new JLabel("Server", SwingConstants.CENTER);
        mainLabel.setFont(FontFactory.getHeaderFont());
        mainPanel.add(mainLabel);
        statusLabel = new JLabel("Server status: " + (isActive ? "on" : "off"));
        statusLabel.setFont(FontFactory.getMain());
        mainPanel.add(statusLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 2, 2));
        JButton buttonStart = new JButton("Start");
        buttonStart.setBackground(ColorFactory.getButtonsColor());
        buttonStart.setFocusPainted(false);
        buttonStart.addActionListener(e -> {
            isActive = true;
            statusLabel.setText("Server status: " + (isActive ? "on" : "off"));
        });
        buttonStart.setFont(FontFactory.getMain());

        JButton buttonStop = new JButton("Stop");
        buttonStop.setBackground(ColorFactory.getButtonsColor());
        buttonStop.setFocusPainted(false);
        buttonStop.addActionListener(e -> {
            isActive = false;
            statusLabel.setText("Server status: " + (isActive ? "on" : "off"));
        });
        buttonStop.setFont(FontFactory.getMain());
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);

        add(mainPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public List<String> getData() throws IOException {
        return model.getModel();
    }

    public boolean isActive() {
        return isActive;
    }

    public static void main(String[] args) throws IOException {
        iGetModel<String> model = new FileModel("src/main/resources/data.txt");
        Server server = new Server(model);
    }

    public void addClient(Client client) {

    }
}
