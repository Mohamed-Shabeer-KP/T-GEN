package operations;

import UI.ProgressOptionpane;



public class Utility {
	inputdata ip_obj;
	public void printInputData(int ip_type,ProgressOptionpane object) throws InterruptedException{
                ip_obj=new inputdata(ip_type,object);
                
		System.out.println("Nostgrp="+ip_obj.nostudentgroup+" Noteachers="+ip_obj.noteacher+" daysperweek="+ip_obj.daysperweek+" hoursperday="+ip_obj.hoursperday);
		for(int i=0;i<ip_obj.nostudentgroup;i++){
			
			System.out.println(ip_obj.studentgroup[i].id+" "+ip_obj.studentgroup[i].name);
			
			for(int j=0;j<ip_obj.studentgroup[i].nosubject;j++){
				System.out.println(ip_obj.studentgroup[i].subject[j]+" "+ip_obj.studentgroup[i].hours[j]+" hrs "+ip_obj.studentgroup[i].teacherid[j]);
			}
			System.out.println("");
		}
		
		for(int i=0;i<ip_obj.noteacher;i++){			
			System.out.println(ip_obj.teacher[i].id+" "+ip_obj.teacher[i].name+" "+ip_obj.teacher[i].subject+" "+ip_obj.teacher[i].assigned);
		}
	}
	
	
	public void printSlots(){
		
		int days=ip_obj.daysperweek;
		int hours=ip_obj.hoursperday;
		int nostgrp=ip_obj.nostudentgroup;
		System.out.println("----Slots----");
		for(int i=0;i<days*hours*nostgrp;i++){
			if(TimeTable.slot[i]!=null)
				System.out.println(i+"- "+TimeTable.slot[i].studentgroup.name+" "+TimeTable.slot[i].subject+" "+TimeTable.slot[i].teacherid);
			else
				System.out.println("Free Period");
			if((i+1)%(hours*days)==0) System.out.println("******************************");
		}
		
	}
        
        public inputdata getobj()
        {       
        return ip_obj;
        }

}
