
public class Carte extends Plateau_de_jeu {
	//parent de type Plateau_de_jeu
	public Plateau_de_jeu parent;
	
	//constructeur 
	public Carte(Plateau_de_jeu plateau) {
		super(plateau.longueur,plateau.largeur);
		super.vider();
		parent = plateau;
	}
	
	//direction = 1: haut ; 2: bas ; 3: gauche 4: droite;
	public boolean deplacer(int direction, int i_reel, int j_reel) {
		
		if(i_reel == 0 && direction ==1) { //Si personnage est en 0,0 alors il ne peut pas deplacer vers le haut
			System.out.print("Erreur : vous ne pouvez vous déplacer vers le haut !");
			return false;
		} else if (i_reel == longueur-1 && direction == 2) { //Si personnage est en longueur-1,0 alors il ne peut pas deplacer vers le bas
	        System.out.println("Erreur : vous ne pouvez vous déplacer vers le bas !");
			return false;
	    } else if(j_reel == 0 && direction == 3) { //Si personnage est en 0,0 alors il ne peut pas deplacer vers la gauche
			System.out.print("Erreur : vous ne pouvez vous déplacer vers la gauche !");
			return false;
		} else if (j_reel == largeur-1 && direction == 4) { //Si personnage est en 0,largeur-1 alors il ne peut pas deplacer vers la droite
	        System.out.println("Erreur : vous ne pouvez vous déplacer vers la droite !");
			return false;
	    }         
		
		//completer la carte 
		completer(i_reel,j_reel);
		
		//deplacer selon la direction
		switch(direction){
			case 1:
				super.positionner(i_reel-1, j_reel, 'O');
				break;
			case 2:
				super.positionner(i_reel+1, j_reel, 'O');
				break;
			case 3:
				super.positionner(i_reel, j_reel-1, 'O');
				break;
			case 4:
				super.positionner(i_reel, j_reel+1, 'O');
				break;
		}
		return true;
	}
	
	//Méthode compléter la carte avec les valeurs
	public void completer(int i_pos, int j_pos){
		super.positionner(i_pos, j_pos, parent.gettab(i_pos, j_pos));
	}
	
	//Méthode afficher les parties connues de la carte
	public void afficher() {
		System.out.println();
		
		for(int i = 0; i < longueur; i++) {
			for(int j=0; j < largeur; j++) {
				if (plateau_de_jeu[i][j] != '.'){
					System.out.print(" | " + plateau_de_jeu[i][j]);
				}else{
					System.out.print("    ");
				}
			}
		System.out.println(); //ligne suivante
		}
	System.out.println(); 
	}
}