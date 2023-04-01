package remoteConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

public class HttpController {
	private static String destination;
	private static HttpClient client = HttpClient.newHttpClient();
	
	public static void setService(String ip, int port) throws IOException {
		URL url = new URL("http://"+ip+':'+port+'/');
		url.openConnection().connect();
		destination = url.toString();
		System.out.println(destination);
	}
	
	
	public static HttpResponse<String> sendRequest(String method) throws URISyntaxException, InterruptedException, ExecutionException {
		return sendRequest(method, Map.of());
		
	}
	public static HttpResponse<String> sendRequest(String method, Map<String, String> headers) throws URISyntaxException, InterruptedException, ExecutionException {
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination + method))
				  .GET();
		for(Entry<String, String> e : headers.entrySet()) {
			request.setHeader(e.getKey(), e.getValue());
		}
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		return response;
	}
	
	
	public static HttpResponse<String> sendFile(String file, String method) throws IOException, URISyntaxException, InterruptedException, ExecutionException {
		DataInputStream dis = new DataInputStream(new FileInputStream("audios/"+file));
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination + method))
				  .header("songName", new File(file).getName())
				  .POST(BodyPublishers.ofByteArray(Base64.getEncoder().encode(dis.readAllBytes())));	
		
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		dis.close();
		return response;
		
    }
	
	public static void recieveFile(String file, String method) throws IOException, URISyntaxException, InterruptedException, ExecutionException{
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination + method))
				  .header("songName", file)
				  .GET();
		
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("audios/"+file));
		
		dos.write(Base64.getDecoder().decode(response.body().getBytes()));
		dos.close();
		
	}
	
}
