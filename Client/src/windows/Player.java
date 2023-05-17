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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Client.interText;
import audioManagement.AudioPlayer;
import audioManagement.SongPlayer;
import remoteConnection.HttpController;

public class Player extends JFrame implements SongPlayer{
	/**
	 * 
	 */
	private ArrayList<String> items = new ArrayList<String>();
	private Player p = null;
	private static final long serialVersionUID = 1L;
	private int song = -1;
	private JList<String> a;
	private String totem;
	private boolean change = false;
	private File[] listOfFiles;
	private JButton stopButton;
//	public static void main(String[] args) {
//		
//	}
	
	
	
	public Player(String totem) {
		this.totem = totem;
		p = this;
		setTitle("AudioPlayer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);

        // Creación del contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Creación de la lista
        File folder = new File("audios");
        ArrayList<String> tem = new ArrayList<String>();
		listOfFiles = folder.listFiles();
		for(File i: listOfFiles) {
			tem.add(i.getName());
		}
		for(String i: tem) {
			String[] partes = i.split("_");
		    items.add(partes[1]);
		}
        a = new JList<>(items.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(a);

        // Creación de los botones
        JButton deleteButton = new JButton(interText.getString("delete"));
        stopButton = new JButton(interText.getString("stop"));
        JButton uploadSong = new JButton(interText.getString("upload_song"));
        JButton downloadButton = new JButton(interText.getString("download"));
        JButton backButton = new JButton(interText.getString("back"));
        
        // Creación del contenedor para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(deleteButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(uploadSong);
        buttonPanel.add(downloadButton);
        buttonPanel.add(backButton);

        // Añadir la lista y los botones al contenedor principal
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(a.getSelectedIndex()>=0) {
					if(AudioPlayer.playing()) {
						AudioPlayer.stopAudioClip();
						
						AudioPlayer.CloseClip();
						stopButton.setText(interText.getString("stop"));
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
						stopButton.setText(interText.getString("resume"));
					}else {
						AudioPlayer.resumeAudioClip();
						stopButton.setText(interText.getString("stop"));
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
				new SongUploaderWd(p, totem);
				
			}
		});
        downloadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new DownloadWd(totem);
				} catch (URISyntaxException | InterruptedException | ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
        backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AudioPlayer.stopAudioClip();
				AudioPlayer.CloseClip();
				dispose();
				new UserWindow(totem);
				
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
    				
    				AudioPlayer.playNewAudioClip(listOfFiles[song].getAbsolutePath(), p);
    				
    				stopButton.setText(interText.getString("stop"));
                }
            }
        });
        
         
        
        // Mostrar la ventana
        setVisible(true);
        
	}



	@Override
	public void nextsong() {
		// TODO Auto-generated method stub
		song++;
		if(song >= a.getModel().getSize()) {
			song = a.getFirstVisibleIndex();
		}
		AudioPlayer.playNewAudioClip(listOfFiles[song].getAbsolutePath(), p);
		stopButton.setText(interText.getString("stop"));
	}
}
