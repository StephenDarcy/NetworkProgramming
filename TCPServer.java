/* Offline Quiz TCP Server Q2) 
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
import java.io.*;


public class TCPServer {
    public static void main(String[] args) {
        try {
            //setting default username and password
            String username = "DIT";
            String password = "TUDUBLIN";

            //creating a server socket
            ServerSocket server = new ServerSocket(5000,10);

            //message to show server is running
            System.out.println("Server Running");

            //accepting a connection from a client
            Socket connection = server.accept();

            //creating streams to send and receive data on
            ObjectOutputStream outStream = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(connection.getInputStream());

            //creating a string to store message from the client and a string to send a message
            String response;
            String message;

            //sending message to client to let them know status of connection
            message = "Connected to Server";
            outStream.writeObject(message);

            //boolean to exit while loop
            boolean state = true;

            //while loop so server runs until response is 'bye'
            while (state == true)
            {            
                //getting response from the client
                response = (String) inStream.readObject();
                //switch statement to execute code based on clients response
                switch (response) {
                    //if client sends "register"
                    case "Register":
                        //asking client to enter name
                        message = "Please enter your name: ";
                        outStream.writeObject(message);
    
                        //getting the clients name
                        String clientName = (String) inStream.readObject();
                        
                        //asking client to enter age
                        message = "Please enter your age: ";
                        outStream.writeObject(message);
    
                        //getting clients age
                        int clientAge = (int) inStream.readObject();
    
                        //asking client to enter phone number
                        message = "Please enter your phone number: ";
                        outStream.writeObject(message);
    
                        //getting the clients phone number
                        double clientPhone = (double) inStream.readObject();
    
                        //telling client they are registered
                        message  = "The Client is successfully registered";
                        outStream.writeObject(message);
    
                        break;
                    
                    case "Login":
                        //asking client to enter username
                        message = "Please enter your username: ";
                        outStream.writeObject(message);

                        //getting the clients username
                        String clientUsername = (String) inStream.readObject();

                        //asking client to enter password
                        message = "Please enter your password: ";
                        outStream.writeObject(message);

                        //getting the clients username
                        String clientPassword = (String) inStream.readObject();

                        //checking to see if username and password match
                        if (clientUsername.equals(username) && clientPassword.equals(password))
                        {
                            message = "You have succesfully logged in";
                            outStream.writeObject(message);
                        }
                        else {
                            message = "Incorrect username/password combination";
                            outStream.writeObject(message);
                        }
                    
                        break;
    
                    case "Modify":
                        //asking client to enter new password
                        message = "Please enter your new password: ";
                        outStream.writeObject(message);

                        //getting the clients new password
                        String clientPasswordNew = (String) inStream.readObject();  
                        
                        //changing password and telling Client
                        password = clientPasswordNew;
                        message = "Password changed!";
                        outStream.writeObject(message);
                      
                        break;
    
                    case "bye":
                        //telling client server is closing
                        message = "Server is shutting down, bye ";
                        outStream.writeObject(message);

                        //exiting while loop after case
                        state = false;
                        break;
                }
            }
            //closing server
            server.close();
        }
        catch (Exception exception) {
            System.err.println(exception);
        }
    }
}