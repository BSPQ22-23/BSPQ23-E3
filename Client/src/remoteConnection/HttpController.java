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
import java.util.ArrayList;
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
	
	/**
	 * 
	 * @param file Path of the selected file
	 * @param album Name of the album of the song
	 * @return String with the response from the server
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	
	public static HttpResponse<String> sendFile(String file, String album) throws IOException, URISyntaxException, InterruptedException, ExecutionException {
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination + "audioSend"))
				  .header("songName", new File(file).getName())
				  .header("songAlbum", album)
				  .POST(BodyPublishers.ofByteArray(Base64.getEncoder().encode(dis.readAllBytes())));	
		
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		dis.close();
		return response;
		
    }
	
	/**
	 * 
	 * @param file Name of the desired file
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void recieveFile(String file) throws IOException, URISyntaxException, InterruptedException, ExecutionException{
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"audioAsk"))
				  .header("songName", file)
				  .GET();
		
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("audios/"+file));
		
		dos.write(Base64.getDecoder().decode(response.body().getBytes()));
		dos.close();
		
	}
	
	public static ArrayList<String> recieveAvilableSongNames() throws URISyntaxException, InterruptedException, ExecutionException {
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"avilableSongSend"))
				  .header("Try", "Sending Songs")
				  .GET();
		
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		String[] list = response.body().split("#");
		ArrayList<String> returnList = new ArrayList<String>();
		for(int i = 0; i < list.length; i++) {
			returnList.add(list[i]);
		}
		System.out.println(returnList.get(1));
		return returnList;
		
	}
	
}
