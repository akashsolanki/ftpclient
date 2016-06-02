<<<<<<< HEAD
package org.springframework.security.samples;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import org.springframework.stereotype.Component;

@Component
public class LoadingScreenDemo extends JWindow {
	
	static LoadingScreenDemo loadingScreenObject = new LoadingScreenDemo();
	
	public static LoadingScreenDemo getInstance(){
		return loadingScreenObject;
	}
	
	//
  Panel panel = new Panel();
  
  
  public void closeLoader(){
	  dispose();
  }
  public LoadingScreenDemo() {
	  
    /*addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          dispose();
        }
      }
    });*/
   panel.setBackground(Color.WHITE);
    
    
    URL url=null;
	try {
		url = new URL("http://enterprise-dashboard.com/img/googleballs-animated.gif");
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    ImageIcon icon = new ImageIcon(url); 
    ImageIcon ggkIcon = new ImageIcon("ggk_logo2.png");
    JLabel ggkLabel = new JLabel();
	JLabel thumb = new JLabel();
	thumb.setIcon(icon);
	ggkLabel.setIcon(ggkIcon);
	panel.add(ggkLabel);
	panel.add(thumb);
    add(panel);
  }
  class Panel extends JPanel {
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setFont(new Font("Verdana", Font.BOLD, 24));
      //g.drawString("Loading...", 270, 210);
    }
  }
=======
package org.springframework.security.samples;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import org.springframework.stereotype.Component;

@Component
public class LoadingScreenDemo extends JWindow {
	
	static LoadingScreenDemo loadingScreenObject = new LoadingScreenDemo();
	
	public static LoadingScreenDemo getInstance(){
		return loadingScreenObject;
	}
	
	//
  Panel panel = new Panel();
  
  
  public void closeLoader(){
	  dispose();
  }
  public LoadingScreenDemo() {
	  
    /*addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          dispose();
        }
      }
    });*/
   panel.setBackground(Color.WHITE);
    
    
    URL url=null;
	try {
		url = new URL("http://enterprise-dashboard.com/img/googleballs-animated.gif");
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    ImageIcon icon = new ImageIcon(url); 
    ImageIcon ggkIcon = new ImageIcon("ggk_logo2.png");
    JLabel ggkLabel = new JLabel();
	JLabel thumb = new JLabel();
	thumb.setIcon(icon);
	ggkLabel.setIcon(ggkIcon);
	panel.add(ggkLabel);
	panel.add(thumb);
    add(panel);
  }
  class Panel extends JPanel {
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setFont(new Font("Verdana", Font.BOLD, 24));
      //g.drawString("Loading...", 270, 210);
    }
  }
>>>>>>> branch 'master' of https://github.com/akashsolanki/ftpclient
}