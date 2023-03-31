package Client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Main {
	  public static void main(String[] args) throws IOException, InterruptedException {


	       HttpClient client = HttpClient.newHttpClient();
	       HttpRequest request = HttpRequest.newBuilder()
	           .uri(URI.create("https://www.developer.com/"))
	           .GET()
	           .build();


	       HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());


	       File fileObj = new File("developer.html");
	       fileObj.createNewFile();


	       FileWriter fileWriterObj = new FileWriter("developer.html");
	       fileWriterObj.write(response.body());
	      
	   }
}
