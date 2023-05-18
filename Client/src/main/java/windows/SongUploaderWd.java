package main.java.windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.Client.InterText;
import main.java.remoteConnection.HttpController;

public class SongUploaderWd extends JFrame {
    private JButton fileButton;
    private JTextField secondNameField, playlistField;
    private File selectedFile;
    String fileButText;
    String totem;
    public SongUploaderWd(Player player, String totem) {
        super(InterText.getString("app_title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.totem = totem;
        // create the label and text field for the first name

        // create the button to select a file
        fileButText = InterText.getString("choose_file");
        fileButton = new JButton(fileButText);
        fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(SongUploaderWd.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    fileButText =  InterText.getString("choose_file") + "(" + selectedFile.getName() +")";
                    fileButton.setText(fileButText);
                    // do something with the selected file
                }
            }
        });

        // create the label and text field for the second name
        JLabel secondNameLabel = new JLabel(InterText.getString("album"));
        JLabel playlistLabel = new JLabel("PlayList:");
        secondNameField = new JTextField(20);
        playlistField = new JTextField(20);
        JPanel secondNamePanel = new JPanel(new BorderLayout());
        
        secondNamePanel.add(secondNameLabel, BorderLayout.WEST);
        secondNamePanel.add(secondNameField, BorderLayout.CENTER);
        secondNamePanel.add(playlistLabel, BorderLayout.SOUTH);
        secondNamePanel.add(playlistField, BorderLayout.SOUTH);

        // create the button to submit the form
        JButton submitButton = new JButton(InterText.getString("sumbit"));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle form submission
            	
            	try {
            		if(selectedFile!=null) {
            			System.out.println("Album: "+ secondNameField.getText() + " PlayList: "+ playlistField.getText());
            			HttpController.sendFile(selectedFile.getAbsolutePath(), secondNameField.getText(), playlistField.getText(), totem); //TODO
            		}
            		
            	}catch(Exception f) {
            		f.printStackTrace();
            	}
            	

            }
        });
        JButton returnToP = new JButton(InterText.getString("back"));
        returnToP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				player.setVisible(true);
				player.validate();
				
			}
		});
        // create the main panel and add the components to it
        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.add(fileButton);
        mainPanel.add(secondNamePanel);
        mainPanel.add(submitButton);
        mainPanel.add(returnToP);

        // add the main panel to the frame and set its size
        getContentPane().add(mainPanel);
        setSize(400, 300);
        setVisible(true);
    }
}