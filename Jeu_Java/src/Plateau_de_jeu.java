public class Plateau_de_jeu {

	int largeur;
	int longueur;
	char [][] plateau_de_jeu;
	//char [] t_lettre; //Contient les lettres possibles dans la grille
	int random;
	
//Liste des objets possibles----------
	// O : personnage
    // X : rien
	// D : danger
	// C : chaud
	// F : froid
//------------------------------------
	
//Constructeur du plateau ( pour l'instant un tableau de 0)

	public Plateau_de_jeu( int l, int L) {
		longueur = l;
		largeur = L;
		plateau_de_jeu = new char[longueur][largeur];	
		random =0;
		
		//Remplissage du tableau 
		for(int i = 0; i < longueur; i++) {
			for(int j=0; j < largeur; j++) {
				random = (int) Math.floor((Math.random()*100)); //Atttribut un nombre en 0 et 100
				
				if(random <= 20) {//revient a attibuer une proba d'apparition de 20% 
					 plateau_de_jeu[i][j]= 'D'; //Case danger
				}
				 
				if(random >20 && random < 60) {//revient a attibuer une proba d'apparition de 40% 
					 plateau_de_jeu[i][j]= 'X'; //Case rien
				}
				
				if(random >=60 && random < 80) {//revient a attibuer une proba d'apparition de 20% 
					 plateau_de_jeu[i][j]= 'C'; //Case environnement chaud
				}
				
				if(random >=80) {//revient a attibuer une proba d'apparition de 20% 
					 plateau_de_jeu[i][j]= 'F'; //Case environnement froid
				}
			}
		}
	 }
	
	//Déclaration des variables de position du personnage en public avec setter & getter		
		
		//Récupérer la valeur de la position i,j
		public char gettab(int i ,int j ) {
			return plateau_de_jeu[i][j];
		}
		
		//Compléter la carte de position i,j avec la valeur voulue
		public char settab(int i ,int j, char valeur) {
			plateau_de_jeu[i][j]= valeur;
			return plateau_de_jeu[i][j];
		}
		
		//Récupérer la position d'un symbole dans le tableau
		//En pratique : servira à déterminer la position du personnage de symbole O
		public int[] recup_pos(char symbole) {
			int i_perso=0;
			int j_perso = 0;
			int[] tab_position = new int[2] ;
			
			for(i_perso=0; i_perso < longueur; i_perso++) {
				
				for(j_perso=0; j_perso < largeur; j_perso++) {
					 //System.out.print(i_perso + " | " + j_perso + " . "); Test pour débug
					if(plateau_de_jeu[i_perso][j_perso] == symbole) {
						tab_position[0]= i_perso;
						tab_position[1] = j_perso;
						return tab_position; 
						}
					} 
			}
			return tab_position;
			
		}
		
	
	//Méthode pour vider le tableau
	public void vider() {
		for(int i = 0; i < longueur; i++) {
			for(int j=0; j < largeur; j++) {
				 plateau_de_jeu[i][j] = '.';
			}			
		}
	}
	
	//Méthode de placement
	public void positionner(int i_pos, int j_pos, char symbole) {
		//i_pos = i_pos-1; // car les tableaux Java commencent en [0][0]
		//j_pos=j_pos-1;
		
		if (i_pos <0 || j_pos <0 || i_pos > longueur || j_pos > largeur) {
			//Position hors du plateau : Erreur
			
			return;
		}
		
		//if(plateau_de_jeu[i_pos][j_pos]=='X'){	
			plateau_de_jeu[i_pos][j_pos]= symbole; // O représentant le personnage 
		//}					
	}
	
}
				
