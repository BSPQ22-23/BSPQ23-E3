package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class ArtistCatalogWindow {

	private JFrame frmArtistCatalog;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArtistCatalogWindow window = new ArtistCatalogWindow();
					window.frmArtistCatalog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ArtistCatalogWindow() {
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
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(182, 85, 89, 23);
		panel_1.add(btnNewButton);
	}

}
