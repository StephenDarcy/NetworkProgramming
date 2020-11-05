/* Offline Quiz TCP Client Q2) 
    Write a TCP Client Server Application that will perform the below operation.
    a) Client Send String “Register” to server and the server ask the client to send
    personal information i.e. name, age and phone number. Server also returns the
    result to client i.e. “The Client is successfully registered”. The client prints the
    output on screen.
    b) Client Send String “Login” to server and Server ask the client to send username
    and password. The server will check the username and password. If it is correct
    then Server sends message to client i.e. “You are successfully Login”. The client
    prints the output on screen. The default username is “DIT” and password is
    “TUDUBLIN”
    c) Client Send String “Modify” to server and Server ask the client to send new
    password. The server update the password and sends message to client i.e. “Your
    password is successfully updated”. The client prints the output on screen.
    d) Client Send String “bye” to server and Server send “bye” to client and shutdown.
    The Client also terminates after receiving “bye”. 

    Author: Stephen Darcy - C18490924
    Date: 05/11/2020
    Compilier: VSCode  
*/

import java.net.*;
import java.util.Scanner;
import java.io.*;


public class TCPClient {
    public static void main(String[] args) {
        try {
            //creating a client socket
            Socket client = new Socket("localhost", 5000);
            
            //creating streams to send and receive data on
            ObjectOutputStream outStream = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());

            //creating a string to store message from the server and a string to send a message
            String response = "";
            String message;

            //getting and printing first message from the Server
            message = (String) inStream.readObject();
            System.out.println(message);

            //Scanner to get input
            Scanner input = new Scanner(System.in);

            //String to display menu
            String menu  =  "a) Type 'Register' to create a new account \n" +
                            "b) Type 'Login' to login \n" +
                            "c) Type 'Modify' to change your password \n" +
                            "d) Type 'bye' to shutdown ";

            //printing menu
            System.out.println(menu);

            //asking user to input
            System.out.println("Enter your choice: ");

            //boolean to keep running until changed
            boolean state = true;

            //while loop to keep connection until user wishes to stop
            while (state == true)
            {
                
                response = input.nextLine();

                outStream.writeObject(response);

                //switch statement to execute code depending on which option they chose
                switch (response) {
                    //if user chooses to register
                    case "Register":
                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //getting user name and sending to server
                        String clientName = input.nextLine();
                        outStream.writeObject(clientName);

                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //getting user age and sending to server
                        int clientAge = input.nextInt();
                        outStream.writeObject(clientAge);

                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //getting user phone number and sending to server
                        double clientPhone = input.nextDouble();
                        outStream.writeObject(clientPhone);

                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //printing menu
                        System.out.println(menu);

                        //asking user to input
                        System.out.println("Enter your choice: ");

                        break;

                    //if user chooses to login
                    case "Login":
                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //getting username and sending to server
                        String clientUsername = input.nextLine();
                        outStream.writeObject(clientUsername);

                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //getting password and sending to server
                        String clientPassword = input.nextLine();
                        outStream.writeObject(clientPassword);

                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //printing menu
                        System.out.println(menu);

                        //asking user to input
                        System.out.println("Enter your choice: ");

                        break;
                    
                    case "Modify":
                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //getting new password and sending to server
                        String clientPasswordNew = input.nextLine();
                        outStream.writeObject(clientPasswordNew);

                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //printing menu
                        System.out.println(menu);

                        //asking user to input
                        System.out.println("Enter your choice: ");

                        break;

                    //if user choose bye
                    case "bye":
                        //getting and printing response from server
                        message = (String) inStream.readObject();
                        System.out.println(message);

                        //change loop condition
                        state = false;

                        //close the client connection
                        client.close();
                        break;
                    case "":
                        break;
                    default:
                        System.out.println("invalid choice");
                        break;
                }
            }
                //closing scanner
                input.close();
        }
        catch (Exception exception) {
            System.err.println(exception);
        }
    }
}