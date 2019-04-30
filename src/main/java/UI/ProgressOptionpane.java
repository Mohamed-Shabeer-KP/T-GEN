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

    double fitness = 0;
    int file_error_flag = 0;

    @SuppressWarnings("serial")
    private void createAndShowGui() {
        JLabel l_timer = new JLabel();
        int timerDelay = 1000;

        new Timer(timerDelay, new ActionListener() {

            int timeCounter = 1;
            int min = 0;
            int sec = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                int flag = (int) Math.round(fitness * 100);
                DecimalFormat f = new DecimalFormat("##.##");
                double perc = Double.valueOf(f.format(fitness * 100));

                if (file_error_flag == 1) {
                    ((Timer) e.getSource()).stop();
                    Window win = SwingUtilities.getWindowAncestor(l_timer);
                    win.setVisible(false);
                }

                if (timeCounter < 60 && flag != 100) {
                    l_timer.setText("<html><b><center>" + "Progress : " + perc + "%" + "</center></b><br/>" + "Time Elapsed " + timeCounter + " seconds" + "</html>");
                    timeCounter++;
                } else if (timeCounter < 10000 && flag != 100) {
                    if (timeCounter % 60 == 0) {
                        min++;
                    } else {
                        sec = timeCounter % 60;
                    }

                    l_timer.setText("<html><b><center>" + "Progress : " + perc + "%" + "</center></b><br/>" + "Time Elapsed " + min + " minutes and " + sec + " seconds" + "</html>");
                    timeCounter++;
                } 
                else if(min==30)
                {
                 JOptionPane.showMessageDialog(null, "Not Possible To Generate Time-Table");
                }
                else {
                    ((Timer) e.getSource()).stop();
                    Window win = SwingUtilities.getWindowAncestor(l_timer);
                    win.setVisible(false);
                    if (sec==0 &min==0)
                        sec = timeCounter;
                    
                    JOptionPane.showMessageDialog(null, "Time Taken : " + min + " minutes and " + sec + " seconds ");
                }

            }
        }) {
            {
                setInitialDelay(0);
            }
        }.start();

        JPanel p = new JPanel();
        p.setSize(1000, 1000);
        p.setPreferredSize(new Dimension(300, 60));
        p.add(l_timer);
        Object[] options1 = {"Yes", "No"};
        JOptionPane msg = new JOptionPane(p, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog msg_dialog = msg.createDialog(null, "Generating Time-Table");
        msg_dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        msg_dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                JOptionPane jop = new JOptionPane("Exit Application", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, null, options1, options1[0]);
                JDialog dialog = jop.createDialog(null, "Caution");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                String a3 = (String) jop.getValue();
                if (a3.equals("Yes")) {
                    System.exit(0);
                }
                dialog.dispose();
            }
        });
        msg_dialog.setVisible(true);
        msg_dialog.dispose();

    }

    public void setfitness(double fit) {
        fitness = fit;
    }

    public void set_file_error_flag(int t) {
        file_error_flag = t;
    }

    public int get_file_error_flag() {
        return file_error_flag;
    }

    public void disp() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}
