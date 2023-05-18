package main.java.windows;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.Client.InterText;
import main.java.audioManagement.AudioPlayer;
import main.java.audioManagement.SongPlayer;

public class PlaylistSongs extends JFrame implements SongPlayer{
	/**
	 * 
	 */
	private ArrayList<String> items = new ArrayList<String>();
	private static final long serialVersionUID = 1L;
	private int song = -1;
	private JList<String> a;
	private ArrayList<String> allsong;
	private String totem;
	private String playlist;
	private PlaylistSongs p;
	private File[] listOfFiles;
	private JButton stopButton;
//	public static void main(String[] args) {
//		
//	}
	public PlaylistSongs(String totem, String playlist) {
		this.p = this;
		this.totem = totem;
		this.playlist = playlist;
		setTitle(InterText.getString("app_title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);

        // Creación del contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Creación de la lista
        File folder = new File("audios");
        allsong = new ArrayList<String>();
		listOfFiles = folder.listFiles();
		for(File i: listOfFiles) {
			allsong.add(i.getName());
		}
		for(String i: allsong) {
			String[] partes = i.split("_");
			System.out.println(partes[0] + " " + partes[1]);
			if(partes[0].equals(playlist)&&!items.contains(partes[1])) {
				items.add(partes[1]);
			}
		}
        a = new JList<>(items.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(a);

        // Creación de los botones
        stopButton = new JButton(InterText.getString("stop"));
        JButton backButton = new JButton(InterText.getString("back"));
        
        // Creación del contenedor para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(stopButton);
        buttonPanel.add(backButton);

        // Añadir la lista y los botones al contenedor principal
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(AudioPlayer.playing()) {
						AudioPlayer.stopAudioClip();
						stopButton.setText(InterText.getString("resume"));
					}else {
						AudioPlayer.resumeAudioClip();
						stopButton.setText(InterText.getString("stop"));
					}
				} catch (Exception e2) {
					System.out.println("ERROR!!! Not able to stop");
				}
				
			}
		});
        backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new PlaylistWindow(totem);
				
			}
		});
        
        a.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                	if(a.getSelectedIndex()>=0) {
    					song  = a.getSelectedIndex();
    				}else {
    					song = a.getFirstVisibleIndex();
    				}
    				for(String i: allsong) {
    					String[] partes = i.split("_");
    					if(partes[1].equals(items.get(song))) {
    						
    						AudioPlayer.playNewAudioClip(listOfFiles[allsong.indexOf(i)].getAbsolutePath(), p);
    					}
    				}
    				
    				stopButton.setText(InterText.getString("stop"));
                }
            }
        });
        // Mostrar la ventana
        setVisible(true);
        
	}
	public void nextsong() {
		// TODO Auto-generated method stub
		song++;
		
		if(song >= a.getModel().getSize()) {
			song = a.getFirstVisibleIndex();
		}
		System.out.println(song);
		for(String i: allsong) {
			String[] partes = i.split("_");
			if(partes[1].equals(a.getModel().getElementAt(song))) {
				AudioPlayer.playNewAudioClip(listOfFiles[song].getAbsolutePath(), p);
				stopButton.setText(InterText.getString("stop"));
				break;
			}
		}
		
	}
}