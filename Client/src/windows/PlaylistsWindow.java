package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class PlaylistsWindow {

	private JFrame frmMyPlaylists;
	private String token;
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PlaylistsWindow window = new PlaylistsWindow();
//					window.frmMyPlaylists.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public PlaylistsWindow(String token) {
		this.token = token;
		initialize();
	}

	private void initialize() {
		frmMyPlaylists = new JFrame();
		frmMyPlaylists.setTitle("My playlists");
		frmMyPlaylists.setBounds(100, 100, 450, 300);
		frmMyPlaylists.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyPlaylists.getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmMyPlaylists.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MY PLAYLISTS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(179, 23, 129, 25);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmMyPlaylists.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton backButton = new JButton("BACK");
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(128, 128, 128));
		backButton.setBounds(99, 81, 75, 23);
		panel_1.add(backButton);
		
		JButton newPlaylistButton = new JButton("CREATE NEW PLAYLIST");
		newPlaylistButton.setBackground(new Color(128, 128, 128));
		newPlaylistButton.setForeground(new Color(255, 255, 255));
		newPlaylistButton.setBounds(198, 81, 189, 23);
		panel_1.add(newPlaylistButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new UserWindow(token);
					frmMyPlaylists.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frmMyPlaylists.setVisible(true);
	}
}
