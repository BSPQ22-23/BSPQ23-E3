package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;

public class MenuWindow {

	private JFrame frmMenu;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuWindow window = new MenuWindow();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuWindow() {
		initialize();
	}

	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle("Menu");
		frmMenu.setBounds(100, 100, 450, 300);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmMenu.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(128, 128, 128));
		loginButton.setBounds(174, 89, 115, 32);
		
		panel_1.add(loginButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmMenu.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton resgisterButton = new JButton("REGISTER");
		resgisterButton.setForeground(new Color(255, 255, 255));
		resgisterButton.setBackground(new Color(128, 128, 128));
		resgisterButton.setBounds(175, 11, 116, 31);
		panel.add(resgisterButton);
	}

}
