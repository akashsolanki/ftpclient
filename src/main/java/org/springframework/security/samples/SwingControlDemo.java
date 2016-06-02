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
import java.net.MalformedURLException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	      mainFrame.setSize(500,500);
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

	      JLabel  namelabel= new JLabel("Directory Path : "); 
	      final JTextField userText = new JTextField(20);
	      userText.setEditable(false);
	      JButton loginButton = new JButton("Enter");
	      JButton browseButton = new JButton("Browse");
	      final JCheckBox checkBox = new JCheckBox();
	      JLabel dbManagerPanel = new JLabel("Show DB Manager",JLabel.LEFT);
	      browseButton.addActionListener(new ActionListener() {  
		         public void actionPerformed(ActionEvent e) { 
		        	 JFileChooser chooser = new JFileChooser();
		        	 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        	 if(!"".equals(userText.getText())){
		        		 chooser.setSelectedFile(new File(userText.getText()));
		        	 }
		        	 chooser.showOpenDialog(mainFrame);
		        	 userText.setText(chooser.getSelectedFile()==null?"":chooser.getSelectedFile().getAbsolutePath());
		         }
	      });
	      loginButton.addActionListener(new ActionListener() { 
	         public void actionPerformed(ActionEvent e) { 
	        	 repositoryPath = userText.getText();
	        	 
	        	 if("".equals(repositoryPath) || repositoryPath == null){
	        		 return; 
	        	 }
	        	 
	        	 logger.info("Repository path :"+userText.getText());
	            mainFrame.setVisible(false); 
	            File files = new File(repositoryPath);
	            files.mkdirs();
	            SpringApplication springApplication = new SpringApplication(new Object[]{Application.class});
	            set(repositoryPath,checkBox.isSelected());
	           
	            LoadingScreenDemo loadingDemo = LoadingScreenDemo.getInstance();
	            loadingDemo.setSize(640, 300);
	            loadingDemo.setLocationRelativeTo(null);
	            loadingDemo.setVisible(true);
	            
		        springApplication.run(args);
	         }

			private void set(String repositoryPath, boolean showManagerPanel) {
				// TODO Auto-generated method stub
				 FileOutputStream fileOut = null;
			     FileInputStream fileIn = null;
			        try {
			            Properties configProperty = new Properties();

			            File file = new File("application.properties");
			            fileIn = new FileInputStream(file);
			            configProperty.load(fileIn);
			            configProperty.setProperty("property.root.path",repositoryPath);
			            configProperty.setProperty("property.db.managerpanel",String.valueOf(showManagerPanel));
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
	      controlPanel.add(browseButton);
	      controlPanel.add(dbManagerPanel);
	      controlPanel.add(checkBox);
	      controlPanel.add(loginButton);
	      mainFrame.setVisible(true);  
	   }
}
