
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
		System.out.println("Cr�ation de votre personnage et du plateau effectu�e !");
		nom_personnage = pnom;
		vitalite_personnage = pvie;
		resistance_personnage = presistance;
		force_personnage = pforce;
	}
	
}
