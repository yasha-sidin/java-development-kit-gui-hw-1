import controller.Client;
import controller.LoginFrame;
import controller.Server;
import model.FileModel;
import model.iGetModel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        iGetModel<String> model = new FileModel("src/main/resources/data.txt");
        Server server = new Server(model);
        server.addClient(new Client("Jake", "123456"));
        server.addClient(new Client("Mery", "123456"));
        LoginFrame loginFrame = new LoginFrame(server);
    }
}
