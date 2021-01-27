package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

// Server class
public class Server
{
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 8000
        ServerSocket ss = new ServerSocket(8000);
        HashSet<String> names = new HashSet<String>();

        System.out.println("Server is running in port " +  Integer.toString(ss.getLocalPort()));

        // running infinite loop for getting client request
        while (true)
        {
            Socket s = null;

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();
                names.add(Integer.toString(s.getPort()));
                System.out.println("Connected list of ports are: " + names);

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}
