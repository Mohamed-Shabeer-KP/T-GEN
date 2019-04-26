package operations;

import UI.TimeTableDisplay;
import UI.DatabaseOp;
import UI.ProgressOptionpane;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.*;
import elements.StudentGroup;
import elements.Teacher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class inputdata {

	public static StudentGroup[] studentgroup;
	public static Teacher[] teacher;
	public static double crossoverrate = 1.0, mutationrate = 0.1;
	public static int nostudentgroup, noteacher;
	public static int hoursperday, daysperweek;
        int flag=0;
        int file_flag=0;
        boolean hasbeeninitialized=false;
        int ip_type=0;
        
	public inputdata(int ip_type,ProgressOptionpane object) throws InterruptedException {
		
                this.ip_type=ip_type;
                studentgroup = new StudentGroup[100];
		teacher =   new Teacher[100];
            try {
                takeinput(object);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(inputdata.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	boolean classformat(String l) {
		StringTokenizer st = new StringTokenizer(l, " ");
		if (st.countTokens() == 3)
			return (true);
		else
			return (false);
	}

	public void takeinput(ProgressOptionpane obj) throws FileNotFoundException, InterruptedException// takes input from file input.txt
	{
		//this method of taking input through file is only for development purpose so hours and days are hard coded
		
               if(ip_type==0)
               {
               daysperweek = 5;
               hoursperday = 6;
		try {
                   
			File file = new File(TimeTableDisplay.path);
			
			Scanner scanner = new Scanner(file);
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				// input student groups
				if (line.equalsIgnoreCase("studentgroups")) {
					int i = 0, j;
					while (!(line = scanner.nextLine()).equalsIgnoreCase("teachers")) {
						studentgroup[i] = new StudentGroup();
						StringTokenizer st = new StringTokenizer(line, " ");
						studentgroup[i].id = i;
						studentgroup[i].name = st.nextToken();
						studentgroup[i].nosubject = 0;
						j = 0;
						while (st.hasMoreTokens()) {
							studentgroup[i].subject[j] = st.nextToken();
							studentgroup[i].hours[j++] = Integer.parseInt(st.nextToken());
							studentgroup[i].nosubject++;
						}
						i++;
					}
					nostudentgroup = i;
				}

				//input teachers
				if (line.equalsIgnoreCase("teachers")) {
					int i = 0;
					while (!(line = scanner.nextLine()).equalsIgnoreCase("end")) {
						teacher[i] = new Teacher();
						StringTokenizer st = new StringTokenizer(line, " ");
						teacher[i].id = i;
						teacher[i].name = st.nextToken();
						teacher[i].subject = st.nextToken();

						i++;
					}
                                        noteacher = i;
				if((line = scanner.nextLine()).equalsIgnoreCase("end"))
                                file_flag=1; 
                                }
                          
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
                
                if(file_flag==1)
                    System.out.println("Input Successfull");
                else
                {
                obj.set_file_error_flag(1);
                JOptionPane.showMessageDialog(null,"Inputing failed , Please make sure input format is valid ");       
                }
        }
  
 
        
else if(ip_type==1)
{
                    try {         
                File f = new File("./src/t-gen-007-firebase-adminsdk-eno5f-c15f92dde6.json");
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
                DatabaseReference basic_ref = database.getReference("basic");
              
                basic_ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        daysperweek = Integer.parseInt((String) dataSnapshot.child("daysperweek").getValue());                  
                        hoursperday = Integer.parseInt((String) dataSnapshot.child("hoursperday").getValue());
                    }
                    
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("Error");// ...
                    }
               });
           
                 
                DatabaseReference sgsref = database.getReference();
                sgsref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        nostudentgroup = Integer.parseInt((String) dataSnapshot.child("studentgroup").child("count").getValue()); 
                        noteacher = Integer.parseInt((String) dataSnapshot.child("teacher").child("count").getValue()); 
                       
                        for(int i=0;i<nostudentgroup;i++)
                        {
                            studentgroup[i] = new StudentGroup();
                            studentgroup[i].id = i;
                            studentgroup[i].nosubject =    Integer.parseInt((String) dataSnapshot.child("studentgroup").child("studentgroup:"+(i+1)).child("subjectno").getValue());
                            studentgroup[i].name =    String.valueOf( dataSnapshot.child("studentgroup").child("studentgroup:"+(i+1)).child("name").getValue());
    
                            for(int j=0;j<studentgroup[i].nosubject;j++)
                            {
                                studentgroup[i].subject[j]  = (String) dataSnapshot.child("studentgroup").child("studentgroup:"+(i+1)).child("subjects").child("subject:"+(j+1)).child("name").getValue();
                                studentgroup[i].hours[j]   = Integer.parseInt((String)  dataSnapshot.child("studentgroup").child("studentgroup:"+(i+1)).child("subjects").child("subject:"+(j+1)).child("hours").getValue());
                            }
                        }
                        
                        for(int i=0;i<noteacher;i++)
                        {
                            teacher[i] = new Teacher();
                            teacher[i].id = i;
                            teacher[i].name = (String) dataSnapshot.child("teacher").child("teacher:"+(i+1)).child("name").getValue();
                            teacher[i].subject = (String) dataSnapshot.child("teacher").child("teacher:"+(i+1)).child("subject").getValue();
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
            } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseOp.class.getName()).log(Level.SEVERE, null, ex);
        }

}
                assignTeacher();
	}
        
        
	// assigning a teacher for each subject for every studentgroup
	public void assignTeacher() {

		// looping through every studentgroup
		for (int i = 0; i < nostudentgroup; i++) {

			// looping through every subject of a student group
			for (int j = 0; j < studentgroup[i].nosubject; j++) {

				int teacherid = -1;
				int assignedmin = -1;

				String subject = studentgroup[i].subject[j];

				// looping through every teacher to find which teacher teaches the current subject
				for (int k = 0; k < noteacher; k++) {

					// if such teacher found,checking if he should be assigned the subject or some other teacher based on prior assignments
					if (teacher[k].subject.equalsIgnoreCase(subject)) {

						// if first teacher found for this subject
						if (assignedmin == -1) {
							assignedmin = teacher[k].assigned;
							teacherid = k;
						}

						// if teacher found has less no of pre assignments than the teacher assigned for this subject
						else if (assignedmin > teacher[k].assigned) {
							assignedmin = teacher[k].assigned;
							teacherid = k;
						}
					}
				}

				// 'assigned' variable for selected teacher incremented
				teacher[teacherid].assigned++;

				studentgroup[i].teacherid[j]= teacherid;
			}
		}
	}
        
                public void time() throws InterruptedException
        {
            if(flag==0)
            {   
                TimeUnit.SECONDS.sleep(10); 
                time();
            }
        }
}

