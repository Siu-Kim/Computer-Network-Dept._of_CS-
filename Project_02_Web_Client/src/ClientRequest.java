import java.io.*;
import java.util.*;
import java.net.*;

final class ClientRequest {
   
   final int TIMEOUT_VALUE = 5000;
   
   public String getWebContentByGet(String urlString, final String charset, int timeout) throws IOException{
      
      if(urlString == null || urlString.length() == 0) {
         return null;
      }
      
      urlString = (urlString.startsWith("http://") || 
            urlString.startsWith("https://")) ? urlString : 
               ("http://" + urlString).intern();
      
      URL url = new URL(urlString);
      
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      
      conn.setRequestMethod("GET");
      conn.setRequestProperty("User-Agent", "2022090537/SIUKIM/WebClient/ComNet");
      conn.setRequestProperty("Accept", "test/html");
      conn.setConnectTimeout(timeout);
      
      
      try {
         if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            return null;
         }
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
      
      InputStream input = conn.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(input, charset));
      String line = null;
      StringBuffer sb = new StringBuffer();
      while((line = in.readLine()) != null) {
         sb.append(line).append("\r\n");
      }
      
      System.out.println("------------");
      System.out.println(line);
      System.out.println("------------");
      
      if (in != null) {
         in.close();
      }
      if (conn != null) {
         conn.disconnect();
      }
      return sb.toString();
   }
   
   public String getWebContentByPost(String urlString, String data, final String charset, int timeout) 
         throws IOException{
      if(urlString == null || urlString.length() == 0) {
         return null;
      }
      
      urlString = (urlString.startsWith("http://") || 
            urlString.startsWith("https://")) ? urlString : 
               ("http://" + urlString).intern();
      URL url = new URL(urlString);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setRequestMethod("POST");
      
      conn.setUseCaches(false);
      conn.setInstanceFollowRedirects(true);
      
      conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
      conn.setRequestProperty("User-Agent", "2022090537/SIUKIM/WebClient/ComNet");
      conn.setRequestProperty("Accept", "text/xml");
      
      conn.setConnectTimeout(timeout);
      conn.connect();
      
      DataOutputStream out = new DataOutputStream(conn.getOutputStream());
      
      byte[] content = data.getBytes("UTF-8");
      
      out.write(content);
      out.flush();
      out.close();
      
      try {
         if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            return null;
         }
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
      
      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),charset));
      String line;
      StringBuffer sb = new StringBuffer();
      while((line = in.readLine()) != null) {
         sb.append(line).append("\r\n");
      }
      if(in != null) {
         in.close();
      }
      if (conn != null) {
         conn.disconnect();
      }
      return sb.toString();
   }
   
}