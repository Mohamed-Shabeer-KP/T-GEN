package operations;



import UI.ProgressOptionpane;
import UI.TimeTableDisplay;
import UI.TimeTable_Table;
import java.io.*;
import java.util.*;

import elements.Slot;
import java.awt.PopupMenu;
import javax.swing.JButton;
import javax.swing.JPanel;

//Chromosome represents array of genes as complete timetable (looks like gene[0]gene[1]gene[2]...)
public class Chromosome implements Comparable<Chromosome>,Serializable{
	
	double crossoverrate;
	double mutationrate;
	public int hours,days;
        int nostgrp;
	double fitness;
	double tr_p;
	public Gene[] gene;
        double tlp_lp,rlh_lp,st_lp;
        double lab_point_1,lab_point_2,lab_point_3,final_lab_point,final_teacher_point;
        double teacher_point_1;
        public ArrayList subject;
        

	
	Chromosome(inputdata ipobj){
		
                crossoverrate=ipobj.crossoverrate;
                mutationrate=ipobj.mutationrate;
                nostgrp=ipobj.nostudentgroup;
                hours=ipobj.hoursperday;
                days=ipobj.daysperweek;
            
		gene=new Gene[nostgrp];
		
		for(int i=0;i<nostgrp;i++){			
			gene[i]=new Gene(i,ipobj);			
		}
		fitness=getFitness();
              
		
	}
	
	public Chromosome deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Chromosome) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	public double getFitness(){
                
		tr_p=0;
                tlp_lp=0;
                rlh_lp=0;
                st_lp=0;
             
		for(int i=0;i<hours*days;i++){//i=30
			
			List<Integer> teacherlist=new ArrayList<Integer>();  
			teacherlist.isEmpty();
                                      
 			for(int j=0;j<nostgrp;j++){ // j<2
			
				Slot slot;
                                
			//	System.out.println("i="+i+" j="+j);
                        
				if(TimeTable.slot[gene[j].slotno[i]]!=null)
                                slot=TimeTable.slot[gene[j].slotno[i]];
				else slot=null;

                                if(slot!=null){
					
                           
                                         
                                         
                                        /* if(teacherlist.contains(3)&&slot.subject!="lab")
                                          {
                                              tr_p=tr_p/2;
                                          }
                                         else*/ if(teacherlist.contains(slot.teacherid))//&&slot.subject!="lab")  
                                          {
                                              tr_p++;
                                          }   
                                          
					 else teacherlist.add(slot.teacherid);
                                        
                                        
				}
			}	
		}
                
           
		for(int i=0;i<hours*days;i++){//i=30	
                         int count=0;
                         
 			for(int j=0;j<nostgrp;j++){ // j<2
			
		          if(TimeTable.slot[gene[j].slotno[i]]!=null)
                          {
                              Slot slot = TimeTable.slot[gene[j].slotno[i]];
                              if(slot.subject.equals("LAB"))
                                 count++; 
                          }
                        }
                           if(count/2==1)
                              st_lp++;
                }
		
                
                
                //code for one day 3 lab cont:
            for(int i=0;i<nostgrp;i++){
			int labcount=0;
                         String prevsub = null;
			for(int j=0;j<days;j++){
                            prevsub=null;
				for(int k=0;k<hours;k++){//hours = 6
				if(TimeTable.slot[gene[i].slotno[k+j*hours]]!=null)
                                {
                                    if(prevsub=="lab" && TimeTable.slot[gene[i].slotno[k+j*hours]].subject=="lab")
                                         rlh_lp++;
                                    
                                    prevsub=TimeTable.slot[gene[i].slotno[k+j*hours]].subject;
                                  
                                  
                                    if(TimeTable.slot[gene[i].slotno[k+j*hours]].subject.equals("lab"))
                                    labcount++;
                                 }
                                
		}
                                if(labcount==3)
                               tlp_lp++;
                                
                                labcount=0;
                        
                        }
                        
                       
              }
                
              
              //  lab_point_1 =.5;//(tlp_lp/(days*(hours/3)))/2;//tlp_lp m(10) ,lab_point_1 - .5
              //  lab_point_2 =.5;//(rlh_lp/22)/2;//div by 25 - rlh_lp m(25) , lab_point_2 - .5
              //  lab_point_3 = .5;//(st_lp/27);//div by 30 - st_lp m(15) lab_point_2 - .5
              //  final_lab_point = (lab_point_3+lab_point_1+lab_point_2)/3;//final_lab_point - .5
          
            
            
             teacher_point_1 = 1 - (tr_p)/((nostgrp-1.0)*hours*days);//ntr_p m(30) teacher_point_1 - 1
             final_teacher_point = teacher_point_1;//final_teacher_point - .5
             
             
             fitness =final_teacher_point ;
             
		return fitness;
	}
	
	                
 
	//printing final timetable
	public void printTimeTable(JPanel p,ProgressOptionpane object) throws FileNotFoundException, InterruptedException, Throwable{
            
            
            TimeTable_Table table_obj =new TimeTable_Table();
            String sg_name = null;
		//for each student group separate time table
		for(int i=0;i<nostgrp;i++){
			
			//status used to get name of student grp because in case first class is free it will throw error
			boolean status=false;
			int l=0;
			while(!status){
			
				if(TimeTable.slot[gene[i].slotno[l]]!=null){
					
                                    sg_name ="Batch "+TimeTable.slot[gene[i].slotno[l]].studentgroup.name+" Timetable :-";                                  
                                    status=true;
				}
				l++;
			}
			
                        
                        subject = new ArrayList();
                        
			//looping for each day
			for(int j=0;j<days;j++){
				
				//looping for each hour of the day
				for(int k=0;k<hours;k++){
				
					//checking if this slot is free otherwise printing it
					if(TimeTable.slot[gene[i].slotno[k+j*hours]]!=null)						
								    
                                        {   
                                            
                                            subject.add(TimeTable.slot[gene[i].slotno[k+j*hours]].subject);
                                        }
					else subject.add("FREE");
				
				}
				
			
			}
                
        table_obj.createGUI(p,sg_name,subject,days,hours,object);	
		}

	}
	
	
	
	public void printChromosome(){
		
		for(int i=0;i<nostgrp;i++){
			for(int j=0;j<hours*days;j++){
				System.out.print(gene[i].slotno[j]+" ");
			}
			System.out.println("");
		}
		
	}



	public int compareTo(Chromosome c) {
		
		if(fitness==c.fitness) return 0;
		else if(fitness>c.fitness) return -1;
		else return 1;
	
	}
	
	
 
        
	
}