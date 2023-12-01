package controller;

import factories.ColorFactory;
import factories.FontFactory;
import model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ClientFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private final JTextField messageField = new JTextField(20);
    private final JTextArea areaForMessages;
    private final Server server;
    private final Client client;

    public ClientFrame(Client client, Server server) throws IOException {
        this.server = server;
        this.client = client;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);


        JLabel label = new JLabel("Chat", SwingConstants.CENTER);
        label.setFont(FontFactory.getMain());
        add(label, BorderLayout.NORTH);
        areaForMessages = new JTextArea();
        areaForMessages.setEditable(false);
        areaForMessages.setFont(FontFactory.getMain());
        update();
        JScrollPane jsp = new JScrollPane(areaForMessages);
        add(jsp, BorderLayout.CENTER);

        JPanel formMessage = new JPanel();
        add(formMessage, BorderLayout.SOUTH);
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(ColorFactory.getButtonsColor());
        sendButton.setFocusPainted(false);
        sendButton.setFont(FontFactory.getMain());
        sendButton.addActionListener(e -> {
            if(!checkStatus()) return;
            if(messageField.getText().isEmpty()) return;
            server.addMessage(new Message(client.getLogin(), messageField.getText()));
            messageField.setText("");
            try {
                update();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        formMessage.add(sendButton, BorderLayout.EAST);
        messageField.setFont(FontFactory.getMain());
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(!checkStatus()) return;
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(messageField.getText().isEmpty()) return;
                    server.addMessage(new Message(client.getLogin(), messageField.getText()));
                    messageField.setText("");
                    try {
                        update();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(ClientFrame.this, "Problem with uploading messages.", "Server error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        formMessage.add(messageField, BorderLayout.CENTER);

        setVisible(true);
    }

    private void update() throws IOException {
        areaForMessages.setText(server.getMessages());
    }

    private boolean checkStatus() {
        if (!client.isIsConnectedToServer()) {
            JOptionPane.showMessageDialog(this, "Can't connect to server.", "Server error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
