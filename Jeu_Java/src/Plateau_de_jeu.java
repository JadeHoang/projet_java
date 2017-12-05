
public class Plateau_de_jeu {

	int largeur;
	int longueur;
	char [][] plateau_de_jeu;



//Constructeur du plateau ( pour l'instant un tableau de 0)

	public Plateau_de_jeu( int l, int L) {
	
		longueur = l;
		largeur = L;
		plateau_de_jeu = new char[longueur][largeur];
		
		//Remplissage du tableau 
		for(int i = 0; i < longueur; i++) {
			for(int j=0; j < largeur; j++) {
				 plateau_de_jeu[i][j]= 'X';
			}
		}
	 }
	//Récupérer la position 
	
	public char recup_pos(int i_pos, int j_pos) {
		return plateau_de_jeu[i_pos][j_pos];
	}
	
	//Méthode 
	public void afficher() {
		System.out.println();
		
		for(int i = 0; i < longueur; i++) {
			for(int j=0; j < largeur; j++) {
				 System.out.print(" | " + plateau_de_jeu[i][j]);
			}
			
			System.out.println(" | ");
		}
		System.out.println();
	}
	
	//Méthode de placement
	public void positionner(int i_pos, int j_pos, char symbole) {
		i_pos = i_pos-1; // car les tableaux Java commence en [0][0]
		j_pos=j_pos-1;
		
		if (i_pos <0 || j_pos <0 || i_pos > longueur || j_pos > largeur) {
			//Position hors du plateau : Erreur
			System.out.print("Erreur dans le position du personnage");
			return;
		}
		
		if(plateau_de_jeu[i_pos][j_pos]=='X'){	
			plateau_de_jeu[i_pos][j_pos]= symbole; // 0 représentant le personnage 
		}
			
	}
	
}