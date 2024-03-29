package main.java.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ArtistCatalogWindow {

	private JFrame frmArtistCatalog;
	private String token;
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ArtistCatalogWindow window = new ArtistCatalogWindow();
//					window.frmArtistCatalog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public ArtistCatalogWindow(String token) {
		this.token = token;
		initialize();
	}

	private void initialize() {
		frmArtistCatalog = new JFrame();
		frmArtistCatalog.setTitle("Artist Catalog");
		frmArtistCatalog.setBounds(100, 100, 450, 300);
		frmArtistCatalog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArtistCatalog.getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmArtistCatalog.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ARTIST CATALOG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(165, 29, 133, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmArtistCatalog.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton backButton = new JButton("BACK");
		backButton.setBackground(new Color(128, 128, 128));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBounds(182, 85, 89, 23);
		panel_1.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new UserWindow(token);
					frmArtistCatalog.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frmArtistCatalog.setVisible(true);
	}
}
