package main.java.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.Client.InterText;

public class SongCatalogWindow {

    private JFrame frmSongCatalog;
    String token;
    // Sample array of strings to display in the JList
    private ArrayList<String> songs = new ArrayList<String>();
    
    public SongCatalogWindow(String token) {
        this.token = token;
        initialize();
    }

    private void initialize() {
        frmSongCatalog = new JFrame();
        frmSongCatalog.setTitle(InterText.getString("app_title"));
        frmSongCatalog.setBounds(100, 100, 450, 400);
        frmSongCatalog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSongCatalog.getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 0, 64));
        frmSongCatalog.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel(InterText.getString("song_catalog"));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(176, 25, 127, 14);
        panel.add(lblNewLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        frmSongCatalog.getContentPane().add(scrollPane);
        
        
//        File folder = new File("audios");
//		File[] listOfFiles = folder.listFiles();
//		for(File i: listOfFiles) {
//			songs.add(i.getName());
//		}
        System.out.println("AAAA");
        songs.clear();
        File folder = new File("target/audios");

        File[] listOfFiles = folder.listFiles();
        System.out.println(listOfFiles.length);
        if (listOfFiles != null) {
            if (listOfFiles.length == 0) {
                //System.out.println("La carpeta audios está vacía");
            } else {
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        songs.add(file.getName());
                    }
                }
            }
        }
        JList<String> list = new JList<String>(songs.toArray(new String[0]));
        
        scrollPane.setViewportView(list);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(128, 0, 64));
        frmSongCatalog.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JButton backButton = new JButton(InterText.getString("back"));
        backButton.setBackground(new Color(128, 128, 128));
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setBounds(179, 87, 89, 23);
        panel_1.add(backButton);
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new UserWindow(token);
                    frmSongCatalog.setVisible(false);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = list.getSelectedIndex();
                    String selectedValue = list.getSelectedValue();
                    // Aquí es donde puedes realizar alguna acción al hacer doble clic en el elemento
                    System.out.println("Doble clic en el elemento #" + index + " (" + selectedValue + ")");
                }
            }
        });
        
        frmSongCatalog.setVisible(true);
    }
}

        