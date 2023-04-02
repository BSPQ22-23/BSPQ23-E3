package windows;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	
	public Player() {
		p = this;
		setTitle("AudioPlayer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Creación del contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Creación de la lista

        JList<String> a = new JList<>(items.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(a);

        // Creación de los botones
        JButton playButton = new JButton("Play");
        JButton nextButton = new JButton("Stop");
        JButton refreshButton = new JButton("Refresh");
        JButton uploadSong = new JButton("Uploaad Songs");
        // Creación del contenedor para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(playButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(refreshButton);

        // Añadir la lista y los botones al contenedor principal
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(a.getSelectedIndex()>=0) {
					song  = a.getSelectedIndex();
				}else {
					song = a.getFirstVisibleIndex();
				}
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
				new SongUploaderWd(p);
				
			}
		});
        // Mostrar la ventana
        setVisible(true);
	}
}
