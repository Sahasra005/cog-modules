import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running...");

        Socket socket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String clientMsg, serverMsg;

        while ((clientMsg = in.readLine()) != null) {
            System.out.println("Client: " + clientMsg);
            System.out.print("Server: ");
            serverMsg = keyboard.readLine();
            out.println(serverMsg);
        }

        socket.close();
        serverSocket.close();
    }
}
