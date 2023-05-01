package windows;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import remoteConnection.HttpController;

public class DownloadWd extends JFrame{
	/**
	 * 
	 */
	private ArrayList<String> items = new ArrayList<String>();

	private static final long serialVersionUID = 1L;
	private String totem;
//	public static void main(String[] args) {
//		
//	}
	public DownloadWd(String totem) throws URISyntaxException, InterruptedException, ExecutionException {
		this.setTotem(totem);
		setTitle("Download Song");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);

        // Creación del contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Creación de la lista
        for(String i: HttpController.recieveAvilableSongNames()) {
        	items.add(i);
        }
        JList<String> a = new JList<>(items.toArray(new String[0]));
        
        JScrollPane scrollPane = new JScrollPane(a);

        // Creación de los botones
        JButton downloadButton = new JButton("Download");
        JButton backButton = new JButton("Back");
        // Creación del contenedor para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(downloadButton);
        buttonPanel.add(backButton);
        // Añadir la lista y los botones al contenedor principal
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        downloadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// DONT KNOW WHAT TO DO HERE.
				try {
					if(a.getSelectedValue()!=null) {
						HttpController.recieveFile(a.getSelectedValue()); //TODO
					}
				} catch (IOException | URISyntaxException | InterruptedException | ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// DONT KNOW WHAT TO DO HERE.
				dispose();
				new Player(totem);
				
			}
		});
        setVisible(true);
        
	}
	public String getTotem() {
		return totem;
	}
	public void setTotem(String totem) {
		this.totem = totem;
	}
}