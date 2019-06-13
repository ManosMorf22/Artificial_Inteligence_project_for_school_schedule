//Vatmanidis Nikolaos 3150009
//Morfiadakis Emmanouil 3150112
import java.util.ArrayList;
public class State{
		private ArrayList<Lesson> lessons;
		private ArrayList<Teacher> teachers;
		private Lesson[][][] namelessons=new Lesson[5][7][9];//5 days 7 hours 9 classes
		private Teacher[][][] nameteachers=new Teacher[5][7][9];
		private int x=0,y=0,z=0;
		public State(ArrayList<Lesson> lessonA,ArrayList<Teacher> teachersA){
			teachers=teachersA;
			lessons=lessonA;
		}
		public Lesson [][][] Program(){
			return namelessons;
		}
		public Teacher findTeacher(){
			ArrayList<Lesson>who;
			for(Teacher t:teachers){
				who=t.getCodesoflessons();
				for(Lesson cobralesson:who){
					if((cobralesson.getNameCourse()).equals(namelessons[x][y][z].getNameCourse())){
						return t;
						
					}
				}
			}
			return null;
			
		}
		public int getpriority(){
			int pr=0;
			if(!isTiredTeacher()) pr++;
			if(EvenlySpreadDays()) pr++;
			if(IsEvenlySpreadTeachers())pr++;
            if(namelessons[x][y][z].Active())pr++;
			return pr;
		}
		
		public boolean IsAtTheSameTime(){
			if(nameteachers[x][y][z]==null) return false;
			for(int ip=0; ip<z; ip++){
				if(nameteachers[x][y][ip]==null) continue;
				if(nameteachers[x][y][z].getName().equals(nameteachers[x][y][ip].getName())) return true;
			}
			return false;
		}
		public boolean isTiredTeacher(){
			boolean b1=false,b2=false;
			if(y>1){
				for(int w1=0; w1<=z; w1++){
					if(nameteachers[x][y-1][w1]!=null)
				if(nameteachers[x][y][z].getName().equals(nameteachers[x][y-1][w1].getName()))b1=true;
					if(nameteachers[x][y-2][w1]!=null)
						if(nameteachers[x][y][z].getName().equals(nameteachers[x][y-2][w1].getName()))b2=true;
					if(b1&&b2)break;
				}
			}
			return (b1&&b2);
		}
		public boolean IsEvenlySpreadTeachers(){
			int m;
			for(int j=0; j<5; j++){
			for(int i=1; i<teachers.size(); i++){
				m=teachers.get(i).getD(j);
				if(m!=teachers.get(i-1).getD(j))
					return false;
			}
			}
			return true;
		}
		public boolean EvenlySpreadDays(){
			for(int z1=0; z1<z; z1++){
				int sum[]=new int[5];
				for(int x1=0; x1<x; x1++){
					sum[x1]=0;
				for(int y1=0; y1<y; y1++){
					if(namelessons[x1][y1][z1]!=null)
						sum[x1]++;
				}
				if(x1>=1)
					if(sum[x1]!=sum[x1-1]) return false;
				}
			}
			return true;
		}
       
        public boolean write(Lesson le,boolean real){
        	if(le==null||le.getNameCourse()==null)return false;
        	namelessons[x][y][z]=le;
        	nameteachers[x][y][z]=findTeacher();
        	if(IsAtTheSameTime()||!namelessons[x][y][z].getClassABC().equals(n())||namelessons[x][y][z].getAmoh()==0||nameteachers[x][y][z].getD(x)==0||nameteachers[x][y][z].getW()==0){
        		delete();
        	return false;
        	}if(real){
        	namelessons[x][y][z].reduce();
        	nameteachers[x][y][z].reduce(x);
        	GoNext();
        	}
        	return true;
        }
        
        public State getBestchild(){
        	int size=lessons.size();
        	State states[]=new State[size];
            int MAX=0;
            int MAXN=0;
            boolean writen=false;
        	for(int i=0; i<size; i++){
        		states[i]=this;
        		if(!this.write(lessons.get(i),false)) continue;
        		int pr=states[i].getpriority();
        		writen=true;
        		if(i==0)
        			MAX=pr;
        		else
        			MAX=max(MAX,pr);
        		if(MAX==pr)
        			MAXN=i;
        		this.delete();
        		if(pr==4) break;
        	}
        	if(!writen){
        		GoNext();
        		return this;
        	}
        	State newState=this;
        	newState.write(lessons.get(MAXN),true);
        	return newState;
        }
       
        
        public void GoNext(){
        	if(x==4&&y==6&&z!=8){
        		x=0;
        		y=0;
        		z++;
            		for(Lesson LessonA:lessons)
            			LessonA.upL();
            		
        	}
        	else if(y==6){
        		for(int i=0; i<7; i++)
        		if(namelessons[x][i][z]!=null)
        		namelessons[x][i][z].detonation();
        		y=0;
        		x++;		
        	}else
        		y++;
        	}
        
        
        public void delete(){
        	namelessons[x][y][z]=null;
        	nameteachers[x][y][z]=null;
        	
        	
        }
		public boolean isTerminal(){
			/*for(Lesson lesson:lessons){
				if(lesson.getAmoh()!=0)
					return false;
			}
			*/
			if(x!=4||y!=6||z!=8) return false;
			return true;
		}
		public int max(int x,int y){
			if(x>y)return x;
			else return y;
		}
		public String n(){
			if(z>=6) return "C";
			else if(z>=3) return "B";
			else return "A";
		}
	}