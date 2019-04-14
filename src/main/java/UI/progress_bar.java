package UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class progress_bar extends JFrame implements ActionListener
   {
      JProgressBar pb;
      JButton b1;
      
    public static void disp()
          {
          progress_bar m=new progress_bar();
          m.setSize(330,100);
          m.setVisible(true);
          Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
          int x = (int) ((dimension.getWidth() - m.getWidth()) / 2);
          int y = (int) ((dimension.getHeight() - m.getHeight()) / 2);
          m.setLocation(x, y); 
          }
      
      
          progress_bar()
          {
             super("Confirm ");
             setLayout(null);
             b1=new JButton("Ok");
             b1.setBackground(Color.yellow);             
             pb=new JProgressBar(1,100);
             pb.setValue(0);
             pb.setStringPainted(true);
             pb.setForeground(Color.red);   
             pb.setBackground(Color.white); 
             b1.setBounds(20,20,80,25);
             pb.setBounds(110,20,200,25);
             pb.setVisible(false);
             add(b1);
             add(pb);        
             
             b1.addActionListener(this);
             setResizable(false);
           
          }
           public void actionPerformed(ActionEvent e)
           {
              int i=0;
              if(e.getSource()==b1)
                 {
                   pb.setVisible(true);
                try
                {
                   while(i<=100)
                   {
                    Thread.sleep(50);
    pb.paintImmediately(0, 0, 200, 25);
        pb.setValue(i);
                     i++;
    }
                   super.setVisible(false);
                 }
                 catch(Exception e1)
                 {
    System.out.print("Caughted exception is ="+e1);
                 }
                }
           }
         
   }
