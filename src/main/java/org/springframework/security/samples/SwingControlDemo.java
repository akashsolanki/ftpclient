package org.springframework.security.samples;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;

public class SwingControlDemo{

	final static Logger logger = Logger.getLogger(SwingControlDemo.class); 
	
	public String repositoryPath = "";
	
	public String[] args;

	 private JFrame mainFrame;
	   private JLabel headerLabel;
	   private JLabel statusLabel;
	   private JPanel controlPanel;
	   
	   
	 public SwingControlDemo(){
		   prepareGUI();
	   }
	
	 
	 private void prepareGUI(){
	      mainFrame = new JFrame("GGK | SNA | FTPClient");
	      mainFrame.setSize(400,400);
	      mainFrame.setLayout(new GridLayout(3, 1));
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      headerLabel = new JLabel("", JLabel.CENTER);        
	      statusLabel = new JLabel("",JLabel.CENTER);    

	      statusLabel.setSize(350,100);

	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());

	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
	   }
	 
	 public void showTextFieldDemo(){
		 
	      headerLabel.setText("GGK | SNA | FTPClient"); 

	      JLabel  namelabel= new JLabel("Directory Path : ", JLabel.RIGHT); 
	      final JTextField userText = new JTextField(25);

	      JButton loginButton = new JButton("Enter");
	      loginButton.addActionListener(new ActionListener() { 
	         public void actionPerformed(ActionEvent e) { 
	        	 repositoryPath = userText.getText();
	        	 logger.info("Repository path :"+userText.getText());
	            mainFrame.setVisible(false); 
	            File files = new File(repositoryPath);
	            files.mkdirs();
	            SpringApplication springApplication = new SpringApplication(new Object[]{Application.class});
	            set(repositoryPath);
		        springApplication.run(args);
	         }

			private void set(String repositoryPath) {
				// TODO Auto-generated method stub
				 FileOutputStream fileOut = null;
			     FileInputStream fileIn = null;
			        try {
			            Properties configProperty = new Properties();
			            File file = new File("application.properties");
			            fileIn = new FileInputStream(file);
			            configProperty.load(fileIn);
			            configProperty.setProperty("property.root.path",repositoryPath);
			            fileOut = new FileOutputStream(file);
			            configProperty.store(fileOut, null);

			        } catch (Exception ex) {
			            Logger.getLogger(SwingControlDemo.class.getName()).log(Level.ERROR, null, ex);
			        } finally {

			            try {
			                fileOut.close();
			            } catch (IOException ex) {
			                Logger.getLogger(SwingControlDemo.class.getName()).log(Level.ERROR, null, ex);
			            }
			        }			
			}
	      }); 

	      controlPanel.add(namelabel);
	      controlPanel.add(userText);
	      controlPanel.add(loginButton);
	      mainFrame.setVisible(true);  
	   }
}
