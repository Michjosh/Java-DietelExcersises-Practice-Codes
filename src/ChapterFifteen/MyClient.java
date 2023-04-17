package ChapterFifteen;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient {
    private static InetAddress host;
    private static final int PORT = 1234;


    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        }catch (UnknownHostException uhEx){
            System.out.println("Host ID not found");
            System.exit(1);
        }
        accessServer();

    }
    private static void accessServer(){
        Socket link = null;

        try {
            link = new Socket(host, PORT);
            Scanner input = new Scanner(link.getInputStream());

            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            Scanner userEntry = new Scanner(System.in);

            String message, response;
            do {
                System.out.println("Enter message: ");
                message = userEntry.nextLine();
                output.println(message);
                response = input.nextLine();
                System.out.println("Server " + response);
            }while (!message.equals("**CLOSE**"));
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        finally {
            try {
                System.out.println("Closing connection...");
                link.close();
            }catch (IOException ioException){
                System.out.println("Unable to disconnect");
                System.exit(1);
            }
        }



    }
}