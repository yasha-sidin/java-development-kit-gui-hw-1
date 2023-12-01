package controller;

import factories.ColorFactory;
import factories.FontFactory;
import model.FileModel;
import model.iGetModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_POSX = 600;
    private static final int WINDOW_POSY = 400;
    private JTextField textFieldLogin;
    private JPasswordField textFieldPassword;
    private Server server;
    public LoginFrame(Server server) {
        this.server = server;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setBackground(ColorFactory.getMain());

        JLabel label = new JLabel("<html><center>Set your login and password to connect the server</center></html>", SwingConstants.CENTER);
        label.setFont(FontFactory.getMain());
        add(label, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        JLabel labelToTextFieldLogin = new JLabel("Login: ");
        labelToTextFieldLogin.setFont(FontFactory.getMain());
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        fieldsPanel.add(labelToTextFieldLogin, cs);
        textFieldLogin = new JTextField(10);
        textFieldLogin.setFont(FontFactory.getMain());
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        fieldsPanel.add(textFieldLogin, cs);
        JLabel labelToTextFieldPassword = new JLabel("Password: ");
        labelToTextFieldPassword.setFont(FontFactory.getMain());
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        fieldsPanel.add(labelToTextFieldPassword, cs);
        textFieldPassword = new JPasswordField(10);
        textFieldPassword.setFont(FontFactory.getMain());
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        fieldsPanel.add(textFieldPassword, cs);

        add(fieldsPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        JButton buttonLogin = new JButton("Login");
        buttonLogin.setFont(FontFactory.getMain());
        buttonLogin.setFocusPainted(false);
        buttonLogin.setBackground(ColorFactory.getButtonsColor());
        buttonLogin.addActionListener(e -> {
            initClient();
        });
        buttonsPanel.add(buttonLogin);
        JButton buttonExit = new JButton("Exit");
        buttonExit.setFont(FontFactory.getMain());
        buttonExit.setFocusPainted(false);
        buttonExit.setBackground(ColorFactory.getButtonsColor());
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        buttonsPanel.add(buttonExit);
        add(buttonsPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void initClient() {
        if (!server.isActive()) {
            JOptionPane.showMessageDialog(this, "Can't connect to server.", "Server error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getSelectedText();
        textFieldLogin.setText("");
        textFieldPassword.setText("");
        if(login.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pls input date to both fields.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (server.connect(login, password)) {
            setVisible(false);
            Client client = new Client(login, password);
            ClientFrame clientFrame = new ClientFrame(client, server);
            return;
        }
        JOptionPane.showMessageDialog(this, "Wrong login or password.", "Login error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) throws IOException {
        iGetModel<String> model = new FileModel("src/main/resources/data.txt");
        Server server = new Server(model);
        LoginFrame loginFrame = new LoginFrame(server);
    }
}
