package main.java.windows;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.Client.InterText;

public class UserWindow {

	private JFrame frmUser;
	String token;

	//	public static void main(String[] args) {
	//EventQueue.invokeLater(new Runnable() {
	//	public void run() {
	//		try {
	//			UserWindow window = new UserWindow();
	//			window.frmUser.setVisible(true);
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}
	//});
	//	}

	public UserWindow(String token) {
		this.token = token;
		initialize();
	}

	private void initialize() {
		frmUser = new JFrame();
		frmUser.setTitle(InterText.getString("app_title"));
		frmUser.setBounds(100, 100, 450, 300);
		frmUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUser.getContentPane().setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmUser.getContentPane().add(panel);
		
		JButton myPlaylistsButton = new JButton(InterText.getString("playlist"));
		myPlaylistsButton.setBounds(159, 32, 155, 34);
		myPlaylistsButton.setForeground(new Color(255, 255, 255));
		myPlaylistsButton.setBackground(new Color(128, 128, 128));
		myPlaylistsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.setLayout(null);
		panel.add(myPlaylistsButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(128, 0, 64));
		panel_3.setForeground(new Color(0, 0, 0));
		frmUser.getContentPane().add(panel_3);
		
		JButton songCatalogButton = new JButton(InterText.getString("song_catalog"));
		songCatalogButton.setBounds(159, 21, 155, 34);
		songCatalogButton.setBackground(new Color(128, 128, 128));
		songCatalogButton.setForeground(new Color(255, 255, 255));
		panel_3.setLayout(null);
		panel_3.add(songCatalogButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmUser.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton languageButton = new JButton(InterText.getString("language"));
		languageButton.setBounds(160, 11, 155, 32);
		languageButton.setBackground(new Color(128, 128, 128));
		languageButton.setForeground(new Color(255, 255, 255));
		panel_1.add(languageButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 0, 64));
		frmUser.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton logOutButton = new JButton(InterText.getString("log_out"));
		logOutButton.setBounds(187, 11, 104, 23);
		logOutButton.setForeground(new Color(255, 255, 255));
		logOutButton.setBackground(new Color(128, 128, 128));
		panel_2.add(logOutButton);
		
		myPlaylistsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PlaylistWindow(token);
					frmUser.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		songCatalogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Player(token);
					frmUser.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		languageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(InterText.lan.equals("en")) {
						InterText.setlan("es");
					}else {
						InterText.setlan("en");
					}
					new UserWindow(token);
					frmUser.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new MenuWindow();
					frmUser.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frmUser.setVisible(true);
	}
}
