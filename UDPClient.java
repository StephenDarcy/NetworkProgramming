/* Offline Quiz UDP Server Q3)
    Create UDP Daytime client server program. The Client send request to the Server
    and the Server will return the current time to the client. You have to write the code
    for both the Client and the Server.
    
    Author: Stephen Darcy - C18490924
    Date: 05/11/2020
    Compilier: VSCode  
*/

import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String args[]) {
        try {
            //setting up socket to receive/send
            DatagramSocket socket = new DatagramSocket();

            //creating an InetAdddres for local machine
            InetAddress localhost = InetAddress.getByName("localhost");

            //creating string for loop
            String message = "";

            //scanner for input
            Scanner input = new Scanner(System.in);

            //menu for user
            String menu =   "1) Enter 'time' to get current time\n" +
                            "2) Enter 'exit' to close";

            //while loop until user exits
            while (message != "exit")
            {
                //printing menu
                System.out.println(menu);
                //getting user input
                message = input.nextLine();

                //creating byte the size of users input
                byte[] size = message.getBytes();

                //creating and sending packet to UDP server
                DatagramPacket sendPacket = new DatagramPacket(size, size.length, localhost, 5000);
                socket.send(sendPacket);

                //receiving reply
                DatagramPacket replyPacket = new DatagramPacket(new byte[1024], 1024);
                socket.receive(replyPacket);

                //putting reply into String
                byte[] reply = replyPacket.getData();
                String currentTime = new String(reply,0, replyPacket.getLength());

                //print current time
                System.out.println(currentTime);
            }
            socket.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}