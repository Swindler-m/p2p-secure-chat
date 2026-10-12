import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        int port=1453;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server has been started. Waiting for a client on port " + port + "...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Welcome to the 1453 Server");
            System.out.println("Please enter a user nickname: ");
            String nickname =keyboard.readLine();
            new Thread(()->{
                try {
                while(true){
                    String incomingMessage = in.readLine();
                    if (incomingMessage == null){
                        System.out.println("The other side was disconnected. The chat was ended!");
                        clientSocket.close();
                        break;
                    }
                    System.out.println(incomingMessage);
                }
                }catch (IOException e){
                    System.out.println("Client connection was lost");
                }
            }).start();
            while (true){
                String CusersMessage = keyboard.readLine();
                if (CusersMessage.equalsIgnoreCase("cikis")){
                    System.out.println("The conversation has ended. See you later! ");
                    clientSocket.close();
                    break;
                }
                if (CusersMessage.trim().isEmpty()){
                    continue;
                }
                out.println(nickname + ": " + CusersMessage );
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
