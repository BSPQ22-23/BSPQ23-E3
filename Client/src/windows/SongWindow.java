package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SongWindow {

	private JFrame frmSong;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SongWindow window = new SongWindow();
					window.frmSong.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SongWindow() {
		initialize();
	}

	private void initialize() {
		frmSong = new JFrame();
		frmSong.setTitle("Song");
		frmSong.setBounds(100, 100, 450, 300);
		frmSong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSong.getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmSong.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Title:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(101, 38, 48, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Artist:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(101, 63, 48, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Album:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(101, 88, 48, 14);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmSong.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton backButton = new JButton("BACK");
		backButton.setBackground(new Color(128, 128, 128));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBounds(170, 98, 89, 23);
		panel_1.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PlaylistWindow();
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
