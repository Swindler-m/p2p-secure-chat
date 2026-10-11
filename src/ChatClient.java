import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 1453;
        try {
            System.out.println("Connecting to server at " + serverAddress + ":" + port + "...");
            Socket socket = new Socket(serverAddress,port);
            System.out.println("Connected to the server succesfully!");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverMessage = in.readLine();
            System.out.println("Server says: " + serverMessage);
        }catch (Exception e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
