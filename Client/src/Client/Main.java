package Client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

//curl 127.0.0.1:8000/test
public class Main {
		private static String destination;
		private static HttpClient client = HttpClient.newHttpClient();
		public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ExecutionException {
			setService("127.0.0.1",8000);
			HashMap<String, String> a =  new HashMap<String, String>() ;
			a.put("aa", "bb");
			sendRequest("Client", a);
			
		}
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
}
