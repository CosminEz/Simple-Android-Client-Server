/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.part;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cosmin
 */
public class ServerPart {

    /**
     * @param args the command line arguments
     */
    
    private final static int PORT= 60123;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        ServerSocket serverSocket=null;
        try 
        {
            
            serverSocket=new ServerSocket(PORT);
            
            //Client Connecting
            System.out.println("Waiting for clients to connect");
            Socket socket=serverSocket.accept();
            System.out.println("A client has connected");
            
            //Send message to client
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("Hello fromm SERVER");
            bw.newLine();
            bw.flush();
            
            //Receive message from client
            String data;
            
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            while((data=br.readLine())!=null)
            System.out.println("Message from the client:"+data);
            
            //The End
            System.out.println("Server has ended");
            
            
        }      
        catch (IOException ex) 
        {
            Logger.getLogger(ServerPart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
