
public class Outil {
	int vie;
	char type;
	
	//constructeur
	public Outil(char ptype,int pvie){
		type = ptype;
		vie = pvie;
	}
	
	//getter
		public char gettype(){
			return type;
		}
		
		public int getvie(){
			return vie;
		}
		
	//setter
		public void settype(char ptype){
			type = ptype;
		}
		
		public void setvie(int pvie){
			vie = pvie;
		}
		
}
