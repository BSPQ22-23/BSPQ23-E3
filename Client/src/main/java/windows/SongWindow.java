package main.java.windows;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.Client.InterText;

public class SongWindow {

	private JFrame frmSong;
	private String token;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SongWindow window = new SongWindow();
//					window.frmSong.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public SongWindow(String token) {
		this.token = token;
		initialize();
	}

	private void initialize() {
		frmSong = new JFrame();
		frmSong.setTitle(InterText.getString("app_title"));
		frmSong.setBounds(100, 100, 450, 300);
		frmSong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSong.getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmSong.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(InterText.getString("title"));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(101, 38, 48, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(InterText.getString("artist"));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(101, 63, 48, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(InterText.getString("album"));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(101, 88, 48, 14);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmSong.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton backButton = new JButton(InterText.getString("back"));
		backButton.setBackground(new Color(128, 128, 128));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBounds(170, 98, 89, 23);
		panel_1.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PlaylistWindow(token);
					frmSong.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frmSong.setVisible(true);
	}
}
