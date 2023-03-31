package Server;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
public class Main {
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");
        while (true) {
            try (Socket socket = server.accept()) {
                Date today = new Date(System.currentTimeMillis());
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                socket.getOutputStream()
                      .write(httpResponse.getBytes("UTF-8"));
            }
        }
	}

}
