/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
/**
 *
 * @author sutha
 */
public class Message_Box {
    
   public static void infoBox(String infoMessage, String titleBar,JFrame Parent)
    {
        JOptionPane.showMessageDialog(Parent, infoMessage,  titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
  public static void errorBox(String infoMessage, String titleBar,JFrame Parent)
    {
        JOptionPane.showMessageDialog(Parent, infoMessage,  titleBar, JOptionPane.ERROR_MESSAGE);
    } 
  
public static int yes_noBox(String infoMessage, String titleBar,JFrame Parent)
    {
       return JOptionPane.showConfirmDialog(Parent, infoMessage,  titleBar, JOptionPane.YES_NO_OPTION);
    } 
}
