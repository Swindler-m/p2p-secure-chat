import java.net.ServerSocket;

public class ChatServer {
    public static void main(String[] args) {
        int port=1453;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server has been started. Waiting for a client on port " + port + "...");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
