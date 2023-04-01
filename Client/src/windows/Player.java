package windows;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Player extends JFrame{
	/**
	 * 
	 */
	ArrayList<String> items = new ArrayList<String>();
	private static final long serialVersionUID = 1L;

	
	public Player() {
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

        // Creación del contenedor para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(playButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(refreshButton);

        // Añadir la lista y los botones al contenedor principal
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        

        // Mostrar la ventana
        setVisible(true);
	}
}
