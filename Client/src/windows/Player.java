package windows;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import audioManagement.AudioPlayer;
import remoteConnection.HttpController;

public class Player extends JFrame{
	/**
	 * 
	 */
	private ArrayList<String> items = new ArrayList<String>();
	private Player p = null;
	private static final long serialVersionUID = 1L;
	private int song = -1;
	private JList<String> a;
	
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
        a = new JList<>(items.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(a);

        // Creación de los botones
        JButton deleteButton = new JButton("Delete");
        JButton stopButton = new JButton("Stop");
        JButton uploadSong = new JButton("Upload Songs");
        JButton downloadButton = new JButton("Download");
        JButton backButton = new JButton("Back");
        
        // Creación del contenedor para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(deleteButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(uploadSong);
        buttonPanel.add(downloadButton);

        // Añadir la lista y los botones al contenedor principal
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(a.getSelectedIndex()>=0) {
					if(AudioPlayer.playing()) {
						AudioPlayer.stopAudioClip();
						stopButton.setText("Stop");
					}
					song  = a.getSelectedIndex();
					File todelete = new File(listOfFiles[song].getAbsolutePath());
					if(todelete.delete()) {
						items.remove(song);
						DefaultListModel<String> model = new DefaultListModel<>();
						for (String element : items) {
				            model.addElement(element);
				        }
						a.setModel(model);
						song = -1;
					}else {
						System.out.println("ERROR - Could not delete the song");
					}
					
					
					
				}
				
			
	            
				//MAKE THAT SONG PLAYABLE
			}
		});
        stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(AudioPlayer.playing()) {
						AudioPlayer.stopAudioClip();
						stopButton.setText("Resume");
					}else {
						AudioPlayer.resumeAudioClip();
						stopButton.setText("Stop");
					}
				} catch (Exception e2) {
					System.out.println("ERROR!!! Not able to stop");
				}
				
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
        
        a.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
              
                	if(a.getSelectedIndex()>=0) {
    					song  = a.getSelectedIndex();
    				}else {
    					song = a.getFirstVisibleIndex();
    				}
    				
    				AudioPlayer.playNewAudioClip(listOfFiles[song].getAbsolutePath());
    				stopButton.setText("Stop");
                }
            }
        });
        // Mostrar la ventana
        setVisible(true);
        
	}
}
