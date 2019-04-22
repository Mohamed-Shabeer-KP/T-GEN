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

public class table_teacher extends JFrame{

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
        
             File f = new File("./src/t-gen-007-firebase-adminsdk-eno5f-c15f92dde6.json");
                // FileInputStream serviceAccount = new FileInputStream("C:\\Users\\moham\\Documents\\NetBeansProjects\\T-GEN\\src\\t-gen-007-firebase-adminsdk-eno5f-c15f92dde6.json");
                FileInputStream serviceAccount = new FileInputStream(f);
                FirebaseOptions options = null;
                try {
                    options = new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .setDatabaseUrl("https://t-gen-007.firebaseio.com")
                            .build(); } catch (IOException ex) {
                                Logger.getLogger(inputdata.class.getName()).log(Level.SEVERE, null, ex);
                            }
               
                
                
               
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("teacher");

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                     teacher_count = Integer.parseInt((String) dataSnapshot.child("count").getValue());    
                
                        
                    tableModel = new DefaultTableModel(new Object[]{"Name","Subject"},0);
                    table.setModel(tableModel);
                    table.setEnabled(false);
                      
                for(int i = 1; i <= teacher_count ; i++)             
                {    
                    teacher_name = (String) dataSnapshot.child("teacher:"+i).child("name").getValue();
                    sub_name = (String) dataSnapshot.child("teacher:"+i).child("subject").getValue();
                    tableModel.addRow(new Object[]{teacher_name,sub_name});
                }
                   flag=1;
                }         
           
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("Error");// ...
                    }
                    
                });
       time();
       flag=0;
                
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