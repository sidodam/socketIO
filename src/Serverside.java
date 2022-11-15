import java.io.*;
import java.net.*;

/**
 * This program demonstrates a simple TCP/IP socket server that echoes every
 * message from the client in reversed form.
 * This server is single-threaded.
 *
 * @author www.codejava.net
 */
public class Serverside {

    public static void main(String[] args) {

        int port = 6000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            int connectionNumber = 1;
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");


                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                writer.println("Connection number " + connectionNumber);
                String text= "";

                while (!text.equals("bye")) {
                    text = reader.readLine();
                    System.out.println(text);
                    writer.println("Server: " + text);
                }
                connectionNumber++;
                socket.close();
                serverSocket.close();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
