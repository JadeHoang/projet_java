
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
	
	//D�claration des variables de position du personnage en public avec setter & getter
	public static class position {
		public static int i_perso;
		public static int j_perso;	
	}
	
	public int get_i() {
		return(position.i_perso);
	}
	public void set_i(int i_perso) {
		position.i_perso = i_perso;
	}
	
	//R�cup�rer la position d'un symbole dans le tableau
	//En pratique : servira � d�terminer la position du personnage de symbole 0
	
	public int[] recup_pos(char symbole) {
		position.i_perso=0;
		position.j_perso = 0;
		int[] tab_position;
		tab_position =new int[1];	
		
		for(position.i_perso=0; position.i_perso < longueur; position.i_perso++) {
			for(position.j_perso=0; position.j_perso < largeur; position.j_perso++) {
				
			}
				if(plateau_de_jeu[position.i_perso][position.j_perso]== symbole) {
					tab_position[0]= position.i_perso;
					tab_position[1] = position.j_perso;
					return tab_position; 
				}
		}
		return tab_position;
		
	}
	
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
	
	//M�thode de placement
	public void positionner(int i_pos, int j_pos, char symbole) {
		i_pos = i_pos-1; // car les tableaux Java commence en [0][0]
		j_pos=j_pos-1;
		
		if (i_pos <0 || j_pos <0 || i_pos > longueur || j_pos > largeur) {
			//Position hors du plateau : Erreur
			System.out.print("Erreur dans le position du personnage");
			return;
		}
		
		if(plateau_de_jeu[i_pos][j_pos]=='X'){	
			plateau_de_jeu[i_pos][j_pos]= symbole; // 0 repr�sentant le personnage 
		}
			
	}
	
}