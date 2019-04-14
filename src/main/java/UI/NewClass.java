/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;


import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.util.List; 
import java.util.concurrent.ExecutionException; 
  
import javax.swing.*; 
  
  
public class NewClass  
{ 
      
    private static JLabel statusLabel; 
    private static JFrame mainFrame; 
    
     JProgressBar pb;
      JButton b1;
      
    public  void init ()  
    { 
      mainFrame = new JFrame("Swing Worker"); 
        mainFrame.setSize(400, 400); 
        mainFrame.setLayout(new GridLayout(2,1)); 
          
        mainFrame.addWindowListener(new WindowAdapter()  
        { 
  
            @Override
            public void windowClosing(WindowEvent e)  
            { 
                System.exit(0); 
            } 
              
        }); 
        /*    
        statusLabel = new JLabel("Not Completed", JLabel.CENTER); 
        mainFrame.add(statusLabel); 
          
        JButton btn = new JButton("Start counter"); 
        btn.setPreferredSize(new Dimension(5,5)); 
        */
        
            
             mainFrame.setLayout(null);
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
             mainFrame.add(b1);
             mainFrame.add(pb);        
             
          
             mainFrame.setResizable(false);
        b1.addActionListener(new ActionListener()  
        { 
  
            @Override
            public void actionPerformed(ActionEvent e)  
            { 
                System.out.println("Button clicked, thread started"); 
                startThread();  
            } 
              
        }); 
          
        mainFrame.add(b1); 
        mainFrame.setVisible(true); 
    } 
      
    public  void startThread()  
    { 
  
        SwingWorker sw1 = new SwingWorker()  
        { 
  
            @Override
            protected String doInBackground() throws Exception  
            { 
                // define what thread will do here 
                for ( int i=10; i>=0; i-- )  
                { 
                    Thread.sleep(100); 
                    System.out.println("Value in thread : " + i); 
                    publish(i); 
                } 
                  
                String res = "Finished Execution"; 
                return res; 
            } 
  
            @Override
            protected void process(List chunks) 
            { 
                // define what the event dispatch thread  
                // will do with the intermediate results received 
                // while the thread is executing 
                int val = (int) chunks.get(chunks.size()-1); 
                  
                statusLabel.setText(String.valueOf(val)); 
            } 
  
            @Override
            protected void done()  
            { 
                // this method is called when the background  
                // thread finishes execution 
                try 
                { 
                    String statusMsg = (String) get(); 
                    System.out.println("Inside done function"); 
                    statusLabel.setText(statusMsg); 
                      
                }  
                catch (InterruptedException e)  
                { 
                    e.printStackTrace(); 
                }  
                catch (ExecutionException e)  
                { 
                    e.printStackTrace(); 
                } 
            } 
        }; 
          
        // executes the swingworker on worker thread 
        sw1.execute();  
    } 
      
  
} 