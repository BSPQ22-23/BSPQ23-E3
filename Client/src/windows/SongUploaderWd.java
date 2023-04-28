package windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import remoteConnection.HttpController;

public class SongUploaderWd extends JFrame {
    private JButton fileButton;
    private JTextField secondNameField;
    private File selectedFile;
    String fileButText;
    public SongUploaderWd(Player player) {
        super("Song Uploader");

        // create the label and text field for the first name

        // create the button to select a file
        fileButText = "Choose file...";
        fileButton = new JButton(fileButText);
        fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(SongUploaderWd.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    fileButText = "Choose file (" + selectedFile.getName() +")";
                    fileButton.setText(fileButText);
                    // do something with the selected file
                }
            }
        });

        // create the label and text field for the second name
        JLabel secondNameLabel = new JLabel("Album:");
        secondNameField = new JTextField(20);
        JPanel secondNamePanel = new JPanel(new BorderLayout());
        secondNamePanel.add(secondNameLabel, BorderLayout.WEST);
        secondNamePanel.add(secondNameField, BorderLayout.CENTER);

        // create the button to submit the form
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle form submission
            	
            	try {
            		if(selectedFile!=null) {
            			HttpController.sendFile(selectedFile.getAbsolutePath(), secondNameField.getText());
            		}
            		
            	}catch(Exception f) {
            		f.printStackTrace();
            	}
            	

            }
        });
        JButton returnToP = new JButton("Back");
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