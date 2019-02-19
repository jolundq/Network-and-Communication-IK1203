import java.net.*;
import java.io.*;

public class HTTPEcho {
    public static void main( String[] args) throws IOException{
        int port = Integer.parseInt(args[0]);
        ServerSocket welcomeSocket = new ServerSocket(port);
        
        String httpMessage = "HTTP/1.1 200 OK" + "\r\n";
        StringBuilder sbSentence = new StringBuilder();
        
       while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            connectionSocket.setSoTimeout(4000);
            System.out.println("Socket accepterad");
        
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream toClient = new DataOutputStream(connectionSocket.getOutputStream());

            String test =" ";
            while(test.length() != 0)
            {
                System.out.println(test);
                test = fromClient.readLine();
                sbSentence.append(test + "\r\n");
            }
                sbSentence = sbSentence.insert(0, "\r\n");
                sbSentence = sbSentence.insert(0, httpMessage);
                
                toClient.writeBytes(sbSentence.toString());
                System.out.println("Done!");

                toClient.close();
                fromClient.close();
                connectionSocket.close();
        }
    }
}
