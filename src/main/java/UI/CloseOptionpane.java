/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CloseOptionpane {

   @SuppressWarnings("serial")
   private static void createAndShowGui(String pro) {
      final JLabel l_timer = new JLabel();
      int timerDelay = 1000;
      new Timer(timerDelay , new ActionListener() {
         int timeLeft = 5;

         @Override
         public void actionPerformed(ActionEvent e) {
            if (timeLeft > 0) {
               l_timer.setText("<html><b><center>"+pro+"</center></b><br/>"+"Please wait " + timeLeft + " seconds"+"</html>");
               timeLeft--;
            } else {
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
       
      JOptionPane.showMessageDialog(null, p,"Please wait", JOptionPane.INFORMATION_MESSAGE);

   }

   public static void disp(String process) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui(process);
         }
      });
   }
}