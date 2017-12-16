
public class Cadre {
	char type;
	int niveau;
	int i_reel;
	int j_reel;
	
	public Cadre(char ptype, int pniveau, int pi_reel, int pj_reel){
		type = ptype;
		niveau = pniveau;
		i_reel = pi_reel;
		j_reel = pj_reel;
	}
	
	//getter
	public char gettype(){
		return type;
	}
	
	public int getniveau(){
		return niveau;
	}
	
	//setter
	public void settype(char ptype){
		type = ptype;
	}
	
	public void setniveau(int pniveau){
		niveau = pniveau;
	}
	
	
}

