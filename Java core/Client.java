public import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        String userMsg, serverMsg;
        while (true) {
            System.out.print("Client: ");
            userMsg = keyboard.readLine();
            out.println(userMsg);
            serverMsg = in.readLine();
            System.out.println("Server: " + serverMsg);
        }
    }
}