/* Offline Quiz TCP Q4) 
    Write a TCP client program that will performed the below operation.
    a) It ask the user to enter the number. If the number is “1” then it will scan all
    the local TCP ports. The client prints the output on screen.
    b) If the number is “2”then it connects with NIST Server and retrieves the
    time. Server address is time.nist.gov and port is 13.The client prints the
    output on screen.
    c) If the number is “3” then it connects with whois Server and retrieves the
    domain information. Server address is whois.internic.net and port is 43. The
    client prints the output on screen.
    
    Author: Stephen Darcy - C18490924
    Date: 05/11/2020
    Compilier: VSCode  
*/



import java.net.*;
import java.util.Scanner;
import java.io.*;
import org.apache.commons.net.*;


public class TCP {
    public static void main(String[] args) {
        
        //menu to display option to user
        String menu =   "Choose an option: \n" +
                        "1 - Scan local TCP Ports\n" +
                        "2 - Get time from NIST server\n" +
                        "3 - Get domain info from whois server\n" +
                        "4 - Exit";
        
        //scanner for user input
        Scanner input = new Scanner(System.in);

        //String to hold user choice
        int choice = 0; 

        //while loop to display menu until user exits
        while(choice != 4) {
            //printing menu
            System.out.println(menu);
            choice = input.nextInt();

            //switch statement to execute code based of user choice
            switch (choice){
                case 1:
                    //for loop to iterate through all the ports
                    for(int i=1; i<=65535; i++) {
                        try {
                            //new socket
                            Socket socket = new Socket("127.0.0.1", i);
                            //printing out if socket is in use
                            System.out.println(i + " is in use\n");
                            socket.close();
                        }
                        catch (Exception ex) {
                            //printing out if socket not in use
                           System.out.println("Port is not in use: " + i);
                        }
                    }
                    break;
                case 2:
                    try {
                        //creating a daytimeTCP client
                        DaytimeTCPClient client = new DaytimeTCPClient();
                        //connecting to time.nist.give
                        client.connect("time-a.nist.gov");
                        //printing time
                        System.out.println(client.getTime());
                        client.disconnect();
                    }
                    catch (Exception ex) {
                        //printing out error
                        System.out.println(ex);
                    }

                    break;
                case 3:
                try {
                    int c;
                    //Create a socket connected to internic.net, port 43.
                    Socket s = new Socket("whois.internic.net", 43);
                    //Obtain input and output streams.
                    InputStream in = s.getInputStream();
                    OutputStream out = s.getOutputStream();
                    //create a string
                    String str = "google.com"+ "\n";
                    out.write(str.getBytes());
                    //while loop for duration of input stream
                    while ((c = in.read())!= -1) {
                        //printing out the strea
                        System.out.print((char)c);
                    }
                    //closing socket
                    s.close();
                } 
                catch (IOException ex) {
                    System.err.println(ex);
                } 
                    break;
                case 4:
                    choice = 4;
                    break;
            }
        }
    }
}
