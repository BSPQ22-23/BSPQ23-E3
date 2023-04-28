package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class PlaylistWindow {

	private JFrame frmPlaylist;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaylistWindow window = new PlaylistWindow();
					window.frmPlaylist.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PlaylistWindow() {
		initialize();
	}

	private void initialize() {
		frmPlaylist = new JFrame();
		frmPlaylist.setTitle("Playlist");
		frmPlaylist.setBounds(100, 100, 450, 300);
		frmPlaylist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPlaylist.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmPlaylist.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SONGS");
		lblNewLabel.setBounds(201, 5, 67, 14);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmPlaylist.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton backButton = new JButton("BACK");
		backButton.setBounds(101, 82, 96, 23);
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(128, 128, 128));
		panel_1.add(backButton);
		
		JButton deleteButton = new JButton("DELETE PLAYLIST");
		deleteButton.setBounds(207, 82, 154, 23);
		deleteButton.setForeground(new Color(255, 255, 255));
		deleteButton.setBackground(new Color(128, 128, 128));
		panel_1.add(deleteButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PlaylistsWindow();
					frmPlaylist.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frmPlaylist.setVisible(true);
	}

}
