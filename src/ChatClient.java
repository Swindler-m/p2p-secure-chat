import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            String serverMessage = in.readLine();
            System.out.println("Server says: " + serverMessage);
            new Thread(() ->{
                try {
                    while (true){
                        String incomingMessage = in.readLine();
                        if (incomingMessage == null){
                            System.out.println("The other side was disconnected. The chat was ended!");
                            break;
                        }
                        System.out.println("Server: " + incomingMessage);
                    }
                }catch (IOException e){
                    System.out.println("Server connection was lost!");
                }

            }).start();
            while (true){
                String usersMessage = keyboard.readLine();
                if ( usersMessage.equalsIgnoreCase("cikis")){
                    System.out.println("The conversation has ended. See you later! ");
                    socket.close();
               break;}
                out.println(usersMessage);
            }
        }catch (Exception e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
