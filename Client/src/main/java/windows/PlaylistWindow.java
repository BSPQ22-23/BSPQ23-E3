package main.java.windows;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.Client.InterText;
import main.java.remoteConnection.HttpController;

public class PlaylistWindow extends JFrame{
	/**
	 * 
	 */
	private ArrayList<String> items = new ArrayList<String>();
	private static final long serialVersionUID = 1L;
	private int num = -1;
	private JList<String> a;
	private String totem;
	private ArrayList<String> allsongs;
	private ArrayList<File> listOfFiles;
	
//	public static void main(String[] args) {
//		
//	}
	public PlaylistWindow(String totem) {
		listOfFiles = new ArrayList<File>();
		this.totem = totem;
		setTitle(InterText.getString("app_title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);

        // Creación del contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Creación de la lista
        File folder = new File("audios");
        allsongs = new ArrayList<String>();
		File[] list = folder.listFiles();
		for(File i: list) {
			listOfFiles.add(i);
		}
		
		for(File i: listOfFiles) {
			allsongs.add(i.getName());
		}
		for(String i: allsongs) {
			String[] partes = i.split("_");
			if(!items.contains(partes[0])) {
				items.add(partes[0]);
			}
		    
		}
        a = new JList<>(items.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(a);

        // Creación de los botones
        JButton deleteButton = new JButton(InterText.getString("delete"));
        JButton backButton = new JButton(InterText.getString("back"));
        
        // Creación del contenedor para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(backButton);
        buttonPanel.add(deleteButton);

        // Añadir la lista y los botones al contenedor principal
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(a.getSelectedIndex()>=0) {
					num  = a.getSelectedIndex();
					if(num > -1) {
							try {
								HttpController.deletePlaylist(items.get(num), totem);
								
							} catch (URISyntaxException | InterruptedException | ExecutionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<String> copy = new ArrayList<String>();
							copy.addAll(allsongs);
							Boolean temp = false;
							for(String i: copy) {
								String[] partes = i.split("_");
								//System.out.println("Items: " + items.get(num) + "Parte[0]: " + partes[0]);
								if(items.get(num).equals(partes[0])&&allsongs.contains(i)) {
									
									File todelete = new File(listOfFiles.get(allsongs.indexOf(i)).getAbsolutePath());
									//System.out.println("Canción: " + i + " Index: " + allsongs.indexOf(i) + " Archivo: " + todelete.getName());
									if(todelete.delete()) {
										temp = true;
										
										listOfFiles.remove(allsongs.indexOf(i));
										allsongs.remove(allsongs.indexOf(i));
									}else {
										System.out.println("ERROR - Could not delete the song");
									}
								}
							}
							if(temp) {
								items.remove(num);
								DefaultListModel<String> model = new DefaultListModel<>();
								for (String element : items) {
						            model.addElement(element);
						        }
								a.setModel(model);
								num = -1;
							}
							
							
							
						
						
					}else {
						System.out.println("ERROR - Could not delete the song");
					}
				}
				
			
	            
				//MAKE THAT SONG PLAYABLE
			}
		});
        backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserWindow(totem);
				
			}
		});
        
        a.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
              
                	if(a.getSelectedIndex()>=0) {
                		dispose();
        				new PlaylistSongs(totem, items.get(a.getSelectedIndex()));
    				}
                }
            }
        });
        // Mostrar la ventana
        setVisible(true);
        
	}
}