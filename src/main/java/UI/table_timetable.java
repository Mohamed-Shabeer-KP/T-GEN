/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import operations.inputdata;

public class table_timetable extends JFrame{

    int flag=0;
    int teacher_count=0;
    String teacher_name;
    String sub_name;JTable table;
    boolean hasbeeninitialized=false;
    DefaultTableModel tableModel;
   

    public  void createGUI(JPanel p) throws FileNotFoundException, InterruptedException {
    
       
        p.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane();
        table = new JTable();
        pane.setViewportView(table);
        JPanel eastPanel = new JPanel();

        JPanel northPanel = new JPanel();
    
        p.add(northPanel, BorderLayout.NORTH);
        p.add(eastPanel, BorderLayout.EAST);
        p.add(pane,BorderLayout.CENTER);
        
                
        
                    tableModel = new DefaultTableModel(new Object[]{"Period ","Subject"},0);
                    table.setModel(tableModel);
                    table.setEnabled(false);
                      
                for(int i = 1; i <= teacher_count ; i++)             
                {    
     
                }
                  
                         
           
   
        p.revalidate();
        p.repaint();
    }
        public  void time() throws InterruptedException
    {
            if(flag==0)
            {   
                TimeUnit.SECONDS.sleep(6); 
                time();
            }
    }
   
} 