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
	/**
	 * 
	 * @return All the available songs in the server
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
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
		return returnList;
		
	}
	/**
	 * 
	 * @param playList- Name of the desired playlist
	 * @return All the songs contained in the especified playlist
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static ArrayList<String> recievePlaylistSongs(String playList) throws URISyntaxException, InterruptedException, ExecutionException{
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"getPlaylistSongs"))
				  .header("ListName", playList)
				  .GET();
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		String[] list = response.body().split("#");
		ArrayList<String> returnList = new ArrayList<String>();
		for(int i = 0; i < list.length; i++) {
			returnList.add(list[i]);
		}
		return returnList;
	}
	
	/**
	 * 
	 * @param playList - name of the playlist to create
	 * @return if the playlist has been succesfully created
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static boolean createPlaylist(String playList) throws URISyntaxException, InterruptedException, ExecutionException{
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"createPlaylist"))
				  .header("ListName", playList)
				  .GET();
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		if(response.body() == "ok") {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param song - Name of the song to add to the specified playlist
	 * @param playList - Name of the playlist we want to add a song into
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void addSongToPlaylist(String song, String playList) throws URISyntaxException, InterruptedException, ExecutionException{
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"addSongToPlaylist"))
				  .header("ListName", playList)
				  .header("SongName", song)
				  .GET();
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		System.out.println(response.body());
	}
	/**
	 * 
	 * @return Names of all the existing playlists in the server
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static ArrayList<String> getPlaylists() throws URISyntaxException, InterruptedException, ExecutionException{
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"getPlaylists"))
				  .header("Playlist", "")
				  .GET();
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		ArrayList<String> returnList = new ArrayList<String>();
		String[] list = response.body().split("#");
		for(int i = 0; i < list.length; i++) {
			returnList.add(list[i]);
		}
		return returnList;
	}
	public static boolean deleteSong(String name) throws URISyntaxException, InterruptedException, ExecutionException {
		File f = new File("audios/"+name);
		f.delete();
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"DeleteSong"))
				  .header("Name", name)
				  .GET();
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		if(response.body() == "ok") {
			return true;
		}
		return false;
	}
	public static String deletePlaylist(String name) throws URISyntaxException, InterruptedException, ExecutionException{
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"DeletePlaylist"))
				  .header("Name", name)
				  .GET();
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		
		return response.body();
			
	}
	
	
	
	//HTTP for login/register
	/**
	 * 
	 * @param name - Name of the account for logging
	 * @param password - Password of the given account
	 * @return if the login has been correctly performed
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static boolean login(String name, String password) throws URISyntaxException, InterruptedException, ExecutionException{
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"Login"))
				  .header("Name", name)
				  .header("Password", password)
				  .GET();
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		if(response.body() == "ok") {
			return true;
		}
		return false;
	}
	//TODO posiblemente añadir más data
	/**
	 * 
	 * @param name - Name of the account to register
	 * @param password - Password of the account to register
	 * @return if the register has been correctly performed
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static boolean register(String name, String password) throws URISyntaxException, InterruptedException, ExecutionException{
		HttpRequest.Builder request = HttpRequest.newBuilder()
				  .uri(new URI(destination +"Register"))
				  .header("Name", name)
				  .header("Password", password)
				  .GET();
		HttpResponse<String> response = client.sendAsync(request.build(), BodyHandlers.ofString()).get();
		if(response.body() == "ok") {
			return true;
		}
		return false;
	}
	
}
