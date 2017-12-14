
public class personnage {
	
	//Nom du personnage
	String nom_personnage;
	//Points de vie du personnage
	int vitalite_personnage;
	//Points de r�sistance 
	int resistance_personnage;
	//Point de force
	int force_personnage;
	
	//Constructeur de la classe personnage 
	
	public personnage(String pnom, int pvie, int presistance, int pforce ) {
		System.out.println("Création de votre personnage et du plateau effectuée !");
		nom_personnage = pnom;
		vitalite_personnage = pvie;
		resistance_personnage = presistance;
		force_personnage = pforce;
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
}
