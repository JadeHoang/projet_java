

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
				
				if(random <= 10) {//revient a attibuer une proba d'apparition de 10% 
					 plateau_de_jeu[i][j]= 'D'; //Case danger
				}
				 
				if(random >10 && random < 60) {//revient a attibuer une proba d'apparition de 50% 
					 plateau_de_jeu[i][j]= 'X'; //Case danger
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
	
	//D�claration des variables de position du personnage en public avec setter & getter		
	
		public char gettab(int i ,int j ) {
			return plateau_de_jeu[i][j];
		}
		
		public char settab(int i ,int j, char valeur) {
			plateau_de_jeu[i][j]= valeur;
			return plateau_de_jeu[i][j];
		}
		

		public int[] recup_pos(char symbole) {
			int i_perso=0;
			int j_perso = 0;
			int[] tab_position = new int[2] ;
			
			for(i_perso=0; i_perso < longueur; i_perso++) {
				
				for(j_perso=0; j_perso < largeur; j_perso++) {
					 //System.out.print(i_perso + " | " + j_perso + " . "); Test pour d�bug
					if(plateau_de_jeu[i_perso][j_perso] == symbole) {
						tab_position[0]= i_perso;
						tab_position[1] = j_perso;
						return tab_position; 
						}
					} 
			}
			return tab_position;
			
		}
		
	

	//R�cup�rer la position d'un symbole dans le tableau
	//En pratique : servira � d�terminer la position du personnage de symbole 0
	
	
	
	//M�thode 
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
//M�thode pour vider le tableau
	public void vider() {
		for(int i = 0; i < longueur; i++) {
			for(int j=0; j < largeur; j++) {
				 plateau_de_jeu[i][j] = '.';
			}			
		}
	}
	
	//M�thode de placement
	public void positionner(int i_pos, int j_pos, char symbole) {
		i_pos = i_pos-1; // car les tableaux Java commencent en [0][0]
		j_pos=j_pos-1;
		
		if (i_pos <0 || j_pos <0 || i_pos > longueur || j_pos > largeur) {
			//Position hors du plateau : Erreur
			
			return;
		}
		
		//if(plateau_de_jeu[i_pos][j_pos]=='X'){	
			plateau_de_jeu[i_pos][j_pos]= symbole; // 0 repr�sentant le personnage 
		//}					
	}
	
	//direction = 1: haut ; 2: bas ; 3: gauche 4: droite;
	public boolean deplacer_vertical(int direction, int i_reel, int j_reel) {
		boolean test = false;
		if(i_reel == 0 && direction ==1) {
			System.out.print("Erreur : vous ne pouvez vous d�placer vers le haut !");
			return test =false;
		} else if (i_reel == 4 && direction == 2) {
	        System.out.println("Erreur : vous ne pouvez vous d�placer vers le bas !");
			return test =false;
	      }      
		return test=true;
		}
	
	public boolean deplacer_horizontal(int direction, int i_reel, int j_reel) {
		boolean test = false;
		if(j_reel == 0 && direction == 3) {
			System.out.print("Erreur : vous ne pouvez vous d�placer vers la gauche !");
			return test =false;
		} else if (j_reel == 3 && direction == 4) {
	        System.out.println("Erreur : vous ne pouvez vous d�placer vers la droite !");
			return test =false;
	      }      
		return test=true;
		}
	}
				
