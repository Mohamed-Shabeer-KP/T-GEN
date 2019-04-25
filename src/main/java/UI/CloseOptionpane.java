/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class CloseOptionpane {
int  flag=1;
   @SuppressWarnings("serial")
   private  void createAndShowGui(String pro) {
       JLabel l_timer = new JLabel();
      int timerDelay = 1000;
           
   
      new Timer(timerDelay , new ActionListener() {

  int timeCounter = 1;

      @Override
         public void actionPerformed(ActionEvent e) {

            if (timeCounter <100 && flag==1)  {
               l_timer.setText("<html><b><center>"+pro+"</center></b><br/>"+"Time Elapsed " + timeCounter + " seconds"+"</html>");
               timeCounter++;
            }
            else {
               ((Timer)e.getSource()).stop();
               Window win = SwingUtilities.getWindowAncestor(l_timer);
               win.setVisible(false);
            }
         
         
         }
      }){{setInitialDelay(0);}}.start();
    
      
JPanel p = new JPanel();
p.setSize(1000,1000);
p.setPreferredSize(new Dimension(300, 60));
p.add(l_timer);



   JOptionPane.showOptionDialog(null, p, "Please wait", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
 

   }
   
   public  void setflag()
   {
   flag=0;
   }
              

   public  void disp(String process) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui(process);
         }
      });
   }
}