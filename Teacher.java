//Vatmanidis Nikolaos 3150009
//Morfiadakis Emmanouil 3150112
import java.util.ArrayList;

public class Teacher{
		private String code,name;
		private ArrayList<Lesson>codelessons=new ArrayList<Lesson>();
		private int []D=new int[5];
		private int W;
		public int getD(int i){
			return D[i];
		}
	
		public int getW(){
			return W;
		}
		public void setCode(String codee){
			code=codee;
		}
		public void setName(String namee){
			name=namee;
		}
		public void setMaxDay(int md){
			for(int i=0; i<5; i++)
				D[i]=md;
		}
		public void setMaxWeek(int mw){
			W=mw;
		}
		public String getCode(){
			return code;
		}
		public String getName(){
			return name;
		}
		public int getMaxWeek(){
			return W;
		}
		public void reduce(int i){
			W--;
			D[i]--;
		}
	
	
		public void addLesson(Lesson cod){
			codelessons.add(cod);
		}
		public ArrayList<Lesson> getCodesoflessons(){
			return codelessons;
		}
	}