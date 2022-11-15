import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * This program demonstrates a simple TCP/IP socket client that reads input
 * from the user and prints echoed message from the server.
 *
 * @author www.codejava.net
 */
public class Clientside {

    public static void main(String[] args) {

        int port = 6868;

        try (Socket socket = new Socket("localhost", port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);


            Scanner sc = new Scanner(System.in);
            String text;

            while (true) {
                text = sc.nextLine();

                writer.println(text);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();

                System.out.println(time);

                if(text.equals("bye"))
                {
                    socket.close();

                    break;
                }

            }




        }
        //if server is not up
        catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}