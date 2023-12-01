package controller;

import javax.swing.*;

public class Client {
    private String login;
    private String password;
    private static boolean isConnectedToServer = false;

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEqual(Client other) {
        return this.getPassword().equals(other.getPassword()) && this.getLogin().equals(other.getLogin());
    }

    public void setStatus(boolean serverStatus) {
        isConnectedToServer = serverStatus;
    }

    public boolean isIsConnectedToServer() {
        return isConnectedToServer;
    }
}
