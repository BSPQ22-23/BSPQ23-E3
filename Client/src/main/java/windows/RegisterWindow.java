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

public class RegisterWindow {
	
	private JFrame frmRegister;
	private JTextField textField;
	private JTextField passwordField;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegisterWindow window = new RegisterWindow();
//					window.frmRegister.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public RegisterWindow() {
		initialize();
	}

	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.setTitle("Register");
		frmRegister.setBounds(100, 100, 450, 300);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		frmRegister.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(InterText.getString("username"));
		lblNewLabel.setBounds(114, 63, 74, 14);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(198, 60, 126, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 64));
		frmRegister.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(InterText.getString("password"));
		lblNewLabel_1.setBounds(112, 26, 74, 14);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_1.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(196, 23, 132, 20);
		panel_1.add(passwordField);
		passwordField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 0, 64));
		frmRegister.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton backButton = new JButton(InterText.getString("back"));
		backButton.setBounds(115, 39, 83, 23);
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(128, 128, 128));
		panel_2.add(backButton);
		
		JButton registerButton = new JButton(InterText.getString("register"));
		registerButton.setBounds(214, 39, 114, 23);
		registerButton.setForeground(new Color(255, 255, 255));
		registerButton.setBackground(new Color(128, 128, 128));
		panel_2.add(registerButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new MenuWindow();
					frmRegister.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					textField.getText();
					boolean a = false;
					if(!textField.getText().equals("") && !passwordField.getText().equals("")) {
						a = HttpController.register(textField.getText(), passwordField.getText());
					}
					if(a) {
						JOptionPane.showMessageDialog(null, "Successfully registered");
					} else {
						JOptionPane.showMessageDialog(null, "Error");
					}
					new MenuWindow();
					frmRegister.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frmRegister.setVisible(true);
	}
}
