public class Personnage {
	
	//Nom du personnage
	String nom_personnage;
	//Points de vie du personnage
	int vitalite_personnage;
	//Points de résistance 
	int resistance_personnage;
	//Point de force
	int force_personnage;
	
	Outil outil;
	//Constructeur de la classe personnage 
	
	public Personnage(String pnom, int pvie, int presistance, int pforce, Outil poutil) {
		System.out.println("Création de votre personnage et du plateau effectuée !");
		nom_personnage = pnom;
		vitalite_personnage = pvie;
		resistance_personnage = presistance;
		force_personnage = pforce;
		outil = poutil;
	}
	
	//Getter
	public String getnom() {
		return(nom_personnage);
	}
	public int getvitalite() {
		return(vitalite_personnage);
	}
	public int getresistance() {
		return(resistance_personnage);
	}
	public int getforce() {
		return(force_personnage);
	}
	
	//Setter
	public void setnom(String pnom) {
		nom_personnage = pnom;
	}
	public void setvie(int pvie) {
		vitalite_personnage = pvie;
	}
	public void setresistance(int presistance) {
		resistance_personnage = presistance;
	}
	public void setforce(int pforce) {
		force_personnage = pforce;
	}
	
	public void update_carac(Cadre cadre){
		if (cadre.gettype() == 'N'){ //nourriture
			vitalite_personnage += 1;
		}else if(cadre.gettype() == 'C'){ //chaud
			
			if(resistance_personnage < cadre.getniveau()){
				vitalite_personnage -= 1;
			}
			resistance_personnage -=1;
		}else if(cadre.gettype() == 'F'){ //froid
			
			if(resistance_personnage < cadre.getniveau()){
				vitalite_personnage -= 1;
			}
			resistance_personnage +=1;
		}else if(cadre.gettype() == 'L'){ //danger
			if(force_personnage < cadre.getniveau()){
				vitalite_personnage -= 1;
			}else{
				((Loup)cadre).survie = false;//loup disparait
			}
			
			force_personnage +=1;
		}else if(cadre.gettype() == 'V'){ //danger
			if(force_personnage < cadre.getniveau()){
				if (outil.getvie() > 0){
					outil.setvie(outil.getvie() - 1);//voleur vole l'outil
				}
			}
			
		}	
	}
}
