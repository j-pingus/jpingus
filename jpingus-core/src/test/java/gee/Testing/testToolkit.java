
//Title:      Game Tookit
//Version:    
//Copyright:  Copyright (c) 1999
//Author:     Gérald Even
//Company:    GEE
//Description:Should help me in the creation of my (1st) real game.
package gee.Testing;

import java.awt.Frame;

import javax.swing.JOptionPane;

//import javax.swing.UIManager;

public class testToolkit { 
  boolean packFrame = false;
  
  //Construct the application
  public testToolkit() {
	  int user_data = JOptionPane.showConfirmDialog(new Frame("a"),
				"Full screen?");
	Frame1 frame = new Frame1(user_data == 0);
    frame.setVisible(true);
    frame.start();

  }

  //Main method
  public static void main(String[] args) {
    try  {
      //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
    }
    new testToolkit();
  }
}