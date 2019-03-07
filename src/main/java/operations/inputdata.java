package operations;

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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class inputdata {

	public static StudentGroup[] studentgroup;
	public static Teacher[] teacher;
	public static double crossoverrate = 1.0, mutationrate = 0.1;
	public static int nostudentgroup, noteacher;
	public static int hoursperday, daysperweek;
        int flag=0;

	public inputdata() throws InterruptedException {
		studentgroup = new StudentGroup[100];
		teacher =   new Teacher[100];
            try {
                takeinput();
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

	public void takeinput() throws FileNotFoundException, InterruptedException// takes input from file input.txt
	{
		//this method of taking input through file is only for development purpose so hours and days are hard coded
		
		/*try {
			File file = new File("c:\\test\\input.txt");
			// File file = new File(System.getProperty("user.dir") +
			// "/input.txt");
			
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
				}

			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
  
  // Fetch the service account key JSON file contents
        
 /* FileInputStream serviceAccount = new FileInputStream("C:\\Users\\moham\\Documents\\NetBeansProjects\\T-GEN\\src\\t-gen-007-firebase-adminsdk-eno5f-c15f92dde6.json");

FirebaseOptions options = null;
            try {
                options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://t-gen-007.firebaseio.com")
                        .build(); } catch (IOException ex) {
                Logger.getLogger(inputdata.class.getName()).log(Level.SEVERE, null, ex);
            }

FirebaseApp.initializeApp(options);
                
     
final FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference ref2 = database.getReference("test");
         DatabaseReference usersRef = ref2.child("users");

Map<String, String> users = new HashMap<>();
users.put("alanisawesome", "Alan Turing");
users.put("gracehop","Hello");

usersRef.setValueAsync(users);


usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
  @Override
  public void onDataChange(DataSnapshot dataSnapshot) {
    System.out.println( dataSnapshot.child("gracehop").getValue());
    flag=1;
  }

  @Override
  public void onCancelled(DatabaseError databaseError) {
    System.out.println("Error");// ...
  }
});


TimeUnit.SECONDS.sleep(5);

     
if(flag==1)
    System.out.println("VALUES FETCHED");
else
    System.out.println("VALUES DIDNT FETCHED");

*/
                daysperweek = 5;
                hoursperday = 6;
                          
                nostudentgroup = 2;
                noteacher = 5;
             
                studentgroup[0] = new StudentGroup();
                studentgroup[0].id = 0;
		studentgroup[0].name = "INT MCA 6TH A BATCH";
                studentgroup[0].nosubject = 5;
		studentgroup[0].subject[0] = "AI";
		studentgroup[0].hours[0] = 4;
                studentgroup[0].subject[1] = "CG";
		studentgroup[0].hours[1] = 4;
                studentgroup[0].subject[2] = "SC";//SC
		studentgroup[0].hours[2] = 5;
                studentgroup[0].subject[3] = "LIB";
		studentgroup[0].hours[3] = 2;
                studentgroup[0].subject[4] = "LAB";
		studentgroup[0].hours[4] = 15;
                
                studentgroup[1] = new StudentGroup();
                studentgroup[1].id = 1;
		studentgroup[1].name = "INT MCA 6TH B BATCH";
                studentgroup[1].nosubject = 5;
		studentgroup[1].subject[0] = "AI";
		studentgroup[1].hours[0] = 4;
                studentgroup[1].subject[1] = "CG";
		studentgroup[1].hours[1] = 4;
                studentgroup[1].subject[2] = "SC";
		studentgroup[1].hours[2] = 5;
                studentgroup[1].subject[3] = "LIB";
		studentgroup[1].hours[3] = 2;
                studentgroup[1].subject[4] = "LAB";
		studentgroup[1].hours[4] = 15;
                
                teacher[0] = new Teacher();
                teacher[0].id = 0;
		teacher[0].name = "SOUMYA MISS";
		teacher[0].subject = "AI";
                
                teacher[1] = new Teacher();
                teacher[1].id = 1;
		teacher[1].name = "DEEPA MISS";
		teacher[1].subject = "SC";
                
                teacher[2] = new Teacher();
                teacher[2].id = 2;
		teacher[2].name = "LEENA MISS";
		teacher[2].subject = "CG";
                
                teacher[3] = new Teacher();
                teacher[3].id = 3;
		teacher[3].name = "RAJESH SIR";
		teacher[3].subject = "LIB";
                
                teacher[4] = new Teacher();
                teacher[4].id = 4;
		teacher[4].name = "NITHIN SIR";
		teacher[4].subject = "LAB";
                
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
}

