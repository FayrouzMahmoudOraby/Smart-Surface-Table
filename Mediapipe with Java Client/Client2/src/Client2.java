import java.net.*;
import java.io.*;

public class Client2 {
    // initialize socket and input output streams

    private Socket socket = null;
    private BufferedReader in = null;

    // constructor to put ip address and port
    public Client2(String address, int port) {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            
         
        // string to read message from input
        
        System.out.println("reading...");
        String receivedMessage;
        // keep reading until "Over" is input
        while((receivedMessage = in.readLine()) != null){
        System.out.println("Received: " +receivedMessage);
        }
        }
         catch (IOException i) {
            System.out.println(i.getMessage());
        }finally{
            // close the connection
        try {
            if(in != null)in.close();
            if(socket != null)socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
        }

        
    }

    public static void main(String args[]) {
        Client2 client = new Client2("127.0.0.1", 65436);
    }
}
