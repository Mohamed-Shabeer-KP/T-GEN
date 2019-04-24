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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import operations.Chromosome;
import operations.inputdata;

public class table_timetable extends JFrame{

    int flag=0;
    String sub_name;JTable table;
    boolean hasbeeninitialized=false;
    DefaultTableModel tableModel;
   

    public  void createGUI(JPanel p,String sg_name ,ArrayList list) throws FileNotFoundException, InterruptedException {
   
        int h = Chromosome.hours;
        int d = Chromosome.days;
        
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
         p.setBorder(new LineBorder(Color.BLACK));
        JPanel panel = new JPanel();

        JLabel l_sg = new JLabel();
        l_sg.setFont(new Font("Serif", Font.PLAIN, 20));
        l_sg.setText(sg_name);
        
        
        JScrollPane pane = new JScrollPane();
        table = new JTable();
     
        pane.setViewportView(table);
        pane.setPreferredSize(new Dimension(130*h,22*d));
        panel.setMaximumSize(new Dimension(250*h,30*d));
        panel.add(pane);
        
        ArrayList period_col = new ArrayList();
        for(int i=1;i<=h;i++)
        period_col.add("Period "+i);
        
        Object[] period_obj = period_col.toArray();


        ArrayList[] sub_array = new ArrayList[d];
        Object[] sub_obj=new Object[d];
        int j=0;
        int check;
        for(int i=0;i<d;i++)
        {
            sub_array[i]=new ArrayList();
            check = (h*i)+h;
            for( ;j<check;j++)
            {  
                sub_array[i].add(list.get(j));              
            }       
            sub_obj[i]=sub_array[i].toArray();
        }
        
        
        tableModel = new DefaultTableModel(period_obj,0);
        table.setModel(tableModel);
        table.setEnabled(false);
        for(int i=0;i<d;i++)
        {
        tableModel.addRow((Object[]) sub_obj[i]);
        }
        
        p.add(l_sg);
        p.add(panel); 
        p.revalidate();
        p.repaint();
    }
    

   
} 