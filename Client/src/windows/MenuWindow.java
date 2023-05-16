package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.interText;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import java.awt.Color;

public class MenuWindow {
	private JFrame frmMenu;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuWindow window = new MenuWindow();
//					window.frmMenu.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public MenuWindow() {
		initialize();
	}

	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle(interText.getString("app_title"));
		frmMenu.setBounds(100, 100, 450, 300);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmMenu.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton loginButton = new JButton(interText.getString("login"));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(128, 128, 128));
		loginButton.setBounds(174, 89, 115, 32);
		
		panel_1.add(loginButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmMenu.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton registerButton = new JButton(interText.getString("register"));
		registerButton.setForeground(new Color(255, 255, 255));
		registerButton.setBackground(new Color(128, 128, 128));
		registerButton.setBounds(175, 11, 116, 31);
		panel.add(registerButton);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new LoginWindow();
					frmMenu.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new RegisterWindow();
					frmMenu.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frmMenu.setVisible(true);
	}
}
