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
import java.text.DecimalFormat;
import javax.swing.*;

public class ProgressOptionpane {
double  fitness=0;
   @SuppressWarnings("serial")
   private  void createAndShowGui() {
       JLabel l_timer = new JLabel();
      int timerDelay = 1000;
           
   
      new Timer(timerDelay , new ActionListener() {

  int timeCounter = 1;
  int min=0;
  int sec=0;

      @Override
         public void actionPerformed(ActionEvent e) {

             int flag = (int) Math.round(fitness * 100);
             DecimalFormat f = new DecimalFormat("##.##");
             double perc =Double.valueOf(f.format(fitness*100));
             
            if (timeCounter <60 && flag!=100)  {      
                l_timer.setText("<html><b><center>"+"Progress : "+ perc +"%"+"</center></b><br/>"+"Time Elapsed " + timeCounter + " seconds"+"</html>");
                timeCounter++;
            }
            else if (timeCounter <10000 && flag!=100)
            {
                if(timeCounter%60==0)
                min++;
                else
                sec=timeCounter%60;
                
                l_timer.setText("<html><b><center>"+"Progress : "+ perc +"%"+"</center></b><br/>"+"Time Elapsed " + min +"mins and "+ sec + "seconds"+"</html>");
                timeCounter++;
            }
            else {
               ((Timer)e.getSource()).stop();
               Window win = SwingUtilities.getWindowAncestor(l_timer);
               win.setVisible(false);
               JOptionPane.showMessageDialog(null,"Time Taken : "+ min+"mins and "+sec+"seconds ");
            }
         
         
         }
      }){{setInitialDelay(0);}}.start();
    
      
JPanel p = new JPanel();
p.setSize(1000,1000);
p.setPreferredSize(new Dimension(300, 60));
p.add(l_timer);



   JOptionPane.showOptionDialog(null, p, "Generating Time Table", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
 

   }
   
   public  void setfitness(double fit)
   {
   fitness=fit;
   }
              

   public  void disp() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}