//Vatmanidis Nikolaos 3150009
//Morfiadakis Emmanouil 3150112
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.io.IOException;
public class AssigmentA{
	public static void main(String[] args){
		int max=0;//we will use it for evenly spread writing
		FileReader []fr=new FileReader[2];
		BufferedReader []br=new BufferedReader[2];
		ArrayList<Lesson> lessons=new ArrayList<Lesson>();
		ArrayList<Teacher> teachers=new ArrayList<Teacher>();
		try{
		for(int i=0; i<2; i++){
			fr[i]=new FileReader(args[i+1]);
			br[i]=new BufferedReader(fr[i]);
		}
		
		String line="";
		int i=0;
		int ll=0;
		while(line!=null){
			lessons.add(new Lesson());
			line=br[0].readLine();
			lessons.get(i).setCode(line.substring(line.indexOf(" ")+1));
			line=br[0].readLine();
			lessons.get(i).setNameCourse(line.substring(line.indexOf(" ")+1));
			line=br[0].readLine();
			lessons.get(i).setClass(line.substring(line.indexOf(" ")+1));
			line=br[0].readLine();
			lessons.get(i).setAmmountofhours(Integer.parseInt(line.substring(line.indexOf(" ")+1)));
			lessons.get(i).upL();
			line=br[0].readLine();
			ll=lessons.get(i).getNameCourse().length();
			if(max<ll) max=ll;//we want max length of lessons
			i++;
		}
		br[0].close();
		line="";
		i=0;
		String coursename;
		while(line!=null){
		  teachers.add(new Teacher());
		  line=br[1].readLine();
		  teachers.get(i).setCode(line.substring(line.indexOf(" ")+1));
		  line=br[1].readLine();
		  teachers.get(i).setName(line.substring(line.indexOf(" ")+1));
		  line=br[1].readLine();
		  teachers.get(i).setMaxDay(Integer.parseInt(line.substring(line.indexOf(" ")+1)));
		  line=br[1].readLine();
		  teachers.get(i).setMaxWeek(Integer.parseInt(line.substring(line.indexOf(" ")+1)));
		  line=br[1].readLine();
		  String line2=line.substring(line.indexOf(" ")+1);
		  while(line2!=null){
			  if(line2.indexOf(",")>=0)
			   coursename=line2.substring(0,line2.indexOf(","));
		   else
			   coursename=line2;
			  for(Lesson l:lessons){
				  if(l.getNameCourse().equals(coursename)){
					  teachers.get(i).addLesson(l);
					  break;
				  }
			  }
			  if(line2.indexOf(",")>=0)
			  line2=line2.substring(line2.indexOf(",")+1);
		      else
				  line2=null;
		  }
		  line=br[1].readLine();
		  i++;
		}
		br[1].close();
		}catch(IOException e){
			System.out.println("Error during opening files");
		}
		State state=new State(lessons,teachers);
		while(!state.isTerminal()){
			state=state.getBestchild();
		}
		Lesson [][][] program=state.Program();
		String day[]=new String[5];
		day[0]="Monday";
		day[1]="Tuesday";
		day[2]="Wednesday";
		day[3]="Thursday";
		day[4]="Firday";
		String sector[]=new String[9];
		sector[0]="A1";
		sector[1]="A2";
		sector[2]="A3";
		sector[3]="B1";
		sector[4]="B2";
		sector[5]="B3";
		sector[6]="C1";
		sector[7]="C2";
		sector[8]="C3";
		try{
		FileWriter fw=new FileWriter("schedule.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write("Program of school\n");
		int yt;
		String wr;
		for(int x=0; x<5; x++){
			bw.write("\n"+day[x]+"\n");
				for(int y=0; y<7; y++){
					yt=y+1;
					bw.write(""+yt+".)");
					for(int z=0; z<9; z++){
					if(program[x][y][z]!=null&&program[x][y][z].getNameCourse()!=null){
					bw.write(sector[z]+" "+program[x][y][z].getNameCourse());
					wr=program[x][y][z].getNameCourse();
					}
					else {
						bw.write(sector[z]+" "+"FREE TIME");
					wr="FREE TIME";
					}
					for(int ui=0; ui<=(max-wr.length()); ui++)//evely spread writing
						bw.write(" ");
					
				}
					bw.write("\n");
			}
		}
		bw.close();
		}catch(IOException e){
			System.out.println("Error during writing in file");
		}
	}
}
