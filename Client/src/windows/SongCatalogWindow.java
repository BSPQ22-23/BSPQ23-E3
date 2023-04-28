package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class SongCatalogWindow {

	private JFrame frmSongCatalog;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SongCatalogWindow window = new SongCatalogWindow();
					window.frmSongCatalog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SongCatalogWindow() {
		initialize();
	}

	private void initialize() {
		frmSongCatalog = new JFrame();
		frmSongCatalog.setTitle("Song Catalog");
		frmSongCatalog.setBounds(100, 100, 450, 300);
		frmSongCatalog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSongCatalog.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmSongCatalog.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SONG CATALOG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(176, 25, 127, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmSongCatalog.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton backButton = new JButton("BACK");
		backButton.setBackground(new Color(128, 128, 128));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBounds(179, 87, 89, 23);
		panel_1.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new UserWindow();
					frmSongCatalog.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frmSongCatalog.setVisible(true);
	}
}
