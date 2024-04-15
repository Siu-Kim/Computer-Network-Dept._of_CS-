import java.io.*;
import java.util.*;
import java.net.*;

public class WebClient {
   public static void main(String args[]) throws Exception {
      Scanner in = new Scanner(System.in);
      
      try {
    	  
    	 ClientRequest client = new ClientRequest();
    	 
         int _method = 3;
         String response;
         while(_method != 0){
            
            System.out.print("Select command (1: GET, 2: POST, 0: exit): ");
            _method = in.nextInt();
            in.nextLine();
            
            String url;
            
            switch(_method) {
            case(1):
               System.out.print("Insert URL: ");
               url = in.nextLine();
               
               response = client.getWebContentByGet(url, "UTF-8", client.TIMEOUT_VALUE);
               System.out.println(response);
               
               break;
            case(2):
               System.out.print("Insert URL: ");
               url = in.nextLine();
               System.out.print("Insert data: ");
               String _data = in.nextLine();
               
               StringTokenizer st = new StringTokenizer(_data, "/");
               response = client.getWebContentByPost(url, _data, "UTF-8", client.TIMEOUT_VALUE);
               
               System.out.println(response);
            
            case(0):
               break;
            }
         }
         
         return;
      }
      catch(IOException e) {
         System.out.println(e.getMessage());
      }
      catch(Exception e) {
         System.out.println(e.getMessage());
      }
   }
}