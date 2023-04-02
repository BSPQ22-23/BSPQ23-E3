package windows;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Player extends JFrame{
	/**
	 * 
	 */
	private ArrayList<String> items = new ArrayList<String>();
	private Player p = null;
	private static final long serialVersionUID = 1L;
	private int song = -1;
	Clip clip;
//	public static void main(String[] args) {
//		
//	}
	public Player() {
		p = this;
		setTitle("AudioPlayer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);

        // Creación del contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Creación de la lista
        File folder = new File("audios");
		File[] listOfFiles = folder.listFiles();
		for(File i: listOfFiles) {
			items.add(i.getName());
		}
        JList<String> a = new JList<>(items.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(a);

        // Creación de los botones
        JButton playButton = new JButton("Play");
        JButton nextButton = new JButton("Stop");
        JButton refreshButton = new JButton("Refresh");
        JButton uploadSong = new JButton("Upload Songs");
        JButton downloadButton = new JButton("Download");
        // Creación del contenedor para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(playButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(uploadSong);
        buttonPanel.add(downloadButton);

        // Añadir la lista y los botones al contenedor principal
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(listOfFiles.length);
//				System.out.println("PLAY PULSADO: "+ listOfFiles[song].getName());
//				if(a.getSelectedIndex()>=0) {
//					song  = a.getSelectedIndex();
//				}else {
//					song = a.getFirstVisibleIndex();
//				}
//				
//				try {
//					clip = AudioSystem.getClip();
//					clip.open(AudioSystem.getAudioInputStream(listOfFiles[song]));
//				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
	            
				//MAKE THAT SONG PLAYABLE
			}
		});
        nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(song>=0) {
					if(0==1/*Temporal, here should go someting like "if the list, size() < song+=1*/) {
						song+=1;
					}else {
						song = 0;
					}
				}else {
					JOptionPane op = new JOptionPane("No song has been choose", ERROR);
				}
			}
		});
        refreshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// DONT KNOW WHAT TO DO HERE.
				
			}
		});
        uploadSong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SongUploaderWd(p);
				
			}
		});
        downloadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new DownloadWd();
				} catch (URISyntaxException | InterruptedException | ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
        // Mostrar la ventana
        setVisible(true);
        
	}
}
