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

public class WaitingOptionpane {
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



//JOptionPane.showOptionDialog(null, p, "Please wait", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
Object [] options1 = {"Go Back", "Accept"};
JOptionPane msg = new JOptionPane(p, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,new Object[]{},null);
JDialog msg_dialog = msg.createDialog(null, "Processing");
msg_dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
msg_dialog.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent we) {
    JOptionPane jop = new JOptionPane("Exit Application", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, null, options1, options1[0]);
    JDialog dialog = jop.createDialog(null, "Caution");
    dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    dialog.setVisible(true);
    String a3 = (String) jop.getValue();
    if (a3.equals("Accept")) {
    System.exit(0);
}
    dialog.dispose();
    }
});
msg_dialog.setVisible(true);
msg_dialog.dispose();

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