package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient 
{
    public static String askServer(String hostname, int port, String ToServer) throws  IOException 
    { 
        if(ToServer == null)
       {
           return askServer(hostname, port);
       }

        int lineCounter = 1;
        Socket socketClient = new Socket(hostname, port);
        socketClient.setSoTimeout(4000);
       
       DataOutputStream fromClient = new DataOutputStream(socketClient.getOutputStream());
       BufferedReader toClient = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
       fromClient.writeBytes(ToServer + '\n');

        StringBuilder sbFromServer = new StringBuilder(); 
        String stringFromServer; 
        try {
            while(lineCounter <= 50 && (stringFromServer = toClient.readLine()) != "\n" && stringFromServer != null)
            {
                sbFromServer.append(stringFromServer + '\n');
                lineCounter++;
            }
                socketClient.close();
                return sbFromServer.toString();

        } catch (IOException e) {
            socketClient.close();
            return sbFromServer.toString();
        }
    }

    public static String askServer(String hostname, int port) throws  IOException 
    {
        int lineCounter = 1;
        Socket socketClient = new Socket(hostname, port); 
        socketClient.setSoTimeout(4000);

        StringBuilder sbFromServer = new StringBuilder(); 
        BufferedReader toClient = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        
        String stringFromServer; 
            while(lineCounter <= 50 && (stringFromServer = toClient.readLine()) != "\n" && stringFromServer != null)
            {
                sbFromServer.append(stringFromServer + '\n');          
                lineCounter++;
            }
             socketClient.close();
             return sbFromServer.toString();   
        }
    }
