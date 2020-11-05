/* Offline Quiz UDP Server Q3)
    Create UDP Daytime client server program. The Client send request to the Server
    and the Server will return the current time to the client. You have to write the code
    for both the Client and the Server.
    
    Author: Stephen Darcy - C18490924
    Date: 05/11/2020
    Compilier: VSCode  
*/

import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class UDPServer {
    public static void main(String args[]) {
        try {
            //creating a datagram socket
            DatagramSocket socket = new DatagramSocket(5000);

            //packet to receive incoming data
            DatagramPacket request = new DatagramPacket(new byte[1024], 1024);

            //message displaying server status
            System.out.println("Server is running");

            //string for loop condition
            String message = "";

            //while loop 
            while (message != "exit"){
                //socket receiving request from client
                socket.receive(request);
                byte[] incomingData = request.getData();

                //putting the incoming data into a string
                message = new String(incomingData,0,request.getLength());

                //if statement for if the client wants the time
                if (message.equals("time"))
                {
                    String timeReturn = getTime();

                    //creating a datagram packet to send the time
                    DatagramPacket packet = new DatagramPacket(timeReturn.getBytes(), 
                            timeReturn.getBytes().length, request.getAddress(),request.getPort());
                    socket.send(packet);
                }
            }
        }
        //catch to get any exceptions
        catch (Exception ex) {
            System.err.println(ex);
        }
    }

    //method to get time
    public static String getTime() 
    {
        Date time = new java.util.Date(System.currentTimeMillis());
        String timeReturn = new SimpleDateFormat("HH:mm:ss").format(time);

        return timeReturn;
    }
}

