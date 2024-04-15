
import java.util.*;
import java.net.*;
import java.io.*;



public class WebServer {

	public static void main(String args[]) throws Exception {
		try {
			ServerSocket serverSocket = new ServerSocket(8989);
			
			while (true) {
				Socket socket = serverSocket.accept();
				
				HttpRequest request = new HttpRequest(socket);
				
				Thread thread = new Thread(request);
				thread.start();
				
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
