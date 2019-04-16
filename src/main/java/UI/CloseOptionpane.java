/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CloseOptionpane {

   @SuppressWarnings("serial")
   private static void createAndShowGui() {
      final JLabel label = new JLabel();
      int timerDelay = 1000;
      new Timer(timerDelay , new ActionListener() {
         int timeLeft = 10;

         @Override
         public void actionPerformed(ActionEvent e) {
            if (timeLeft > 0) {
               label.setText("Please wait " + timeLeft + " seconds");
               timeLeft--;
            } else {
               ((Timer)e.getSource()).stop();
               Window win = SwingUtilities.getWindowAncestor(label);
               win.setVisible(false);
            }
         }
      }){{setInitialDelay(0);}}.start();

      JOptionPane.showMessageDialog(null, label);

   }

   public static void disp() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}