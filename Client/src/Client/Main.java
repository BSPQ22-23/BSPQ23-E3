package Client;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import audioManagement.AudioPlayer;
import remoteConnection.HttpController;
import windows.MenuWindow;
import windows.Player;
import windows.RegisterWindow;
import windows.UserWindow;

//curl 127.0.0.1:8000/test
public class Main {
		
		public static void main(String[] args){
			System.out.println(System.getProperty("user.dir"));
			try {
				Files.createDirectories(Paths.get("./audios"));
				HttpController.setService("127.0.0.1",8000);
				ArrayList<String> a = new ArrayList<String>();
				for(String i: HttpController.recieveAvilableSongNames()) {
					a.add(i);
					System.out.println(i);
				}
				//HttpController.register("USER", "1");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			new MenuWindow();
//			try {
//				
//				HttpController.setService("127.0.0.1",8000);//Create Connection
//				//Test
//				HashMap<String, String> a =  new HashMap<String, String>() ;
//				a.put("user", "popipo"); //header + valor
//				HttpController.sendRequest("test", a); //llamar a una funcion, Unicamente llamar a lo de despues de la barra
//				//TestEnd
//				
//				//TODO call UI, from UI call to the controller with the data/values
//				HttpController.sendFile("ds.wav", "audioSend");
//				
//				HttpController.recieveFile("popipo.wav", "audioAsk");
//				
//				AudioPlayer.playNewAudioClip("audios/popipo.wav");
//				try {
//					Thread.sleep(10000);
//				}catch(Exception e) {
//					
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			
		}
//		
}
