package main.java.windows;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.java.Client.InterText;
import main.java.remoteConnection.HttpController;

public class LoginWindow {

	private JFrame frmLogin;
	private JTextField textField;
	private JTextField passwordField_1;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginWindow window = new LoginWindow();
//					window.frmLogin.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public LoginWindow() {
		initialize();
	}

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle(InterText.getString("app_title"));
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(InterText.getString("username"));
		lblNewLabel.setBounds(122, 63, 78, 14);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(199, 60, 116, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmLogin.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(InterText.getString("password"));
		lblNewLabel_1.setBounds(121, 25, 74, 14);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_1.add(lblNewLabel_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(199, 22, 116, 20);
		panel_1.add(passwordField_1);
		passwordField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 0, 64));
		frmLogin.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton backButton = new JButton(InterText.getString("back"));
		backButton.setBounds(123, 24, 89, 23);
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(128, 128, 128));
		panel_2.add(backButton);
		
		JButton loginButton = new JButton(InterText.getString("login"));
		loginButton.setBounds(232, 24, 89, 23);
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(128, 128, 128));
		
		panel_2.add(loginButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new MenuWindow();
					frmLogin.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String a = "notOK";
					if(!textField.getText().equals("") && !passwordField_1.getText().equals("")) {
						a = HttpController.login(textField.getText(), passwordField_1.getText());
					}
					System.out.println(a);
					if(!a.equals("notOK")) {
						new UserWindow(a);
						frmLogin.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frmLogin.setVisible(true);
	}
}
