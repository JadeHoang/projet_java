import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Plateau_de_jeu {

	int largeur;
	int longueur;
	Cadre [][] plateau_de_jeu;
	//char [] t_lettre; //Contient les lettres possibles dans la grille
	
	
	//niveau chaud
		int niv_chaud = 4;
		//niveau froid
		int niv_froid = 4;
		//niveau voleur
		int niv_vole = 3;
		//niveau loup
		int niv_loup = 4;
		
		
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
		plateau_de_jeu = new Cadre[longueur][largeur];	
		int tab[][] = new int[longueur][largeur];
		
		Integer[] array = new Integer[longueur*largeur];
		
		for (int i = 0; i < longueur*largeur*0.1; i++){
			array[i] = 1;//Loup
		}
		
		for (int i = (int) (longueur*largeur*0.1); i < longueur*largeur*0.2; i++){
			array[i] = 2;//Voleur
		}
		
		for (int i = (int) (longueur*largeur*0.2); i < longueur*largeur*0.5; i++){
			array[i] = 3;//Rien
		}
		
		for (int i = (int) (longueur*largeur*0.5); i < longueur*largeur*0.6; i++){
			array[i] = 4;//Nourriture
		}
		
		for (int i = (int) (longueur*largeur*0.6); i < longueur*largeur*0.8; i++){
			array[i] = 5;//Chaud
		}
		
		for (int i = (int) (longueur*largeur*0.8); i < longueur*largeur; i++){
			array[i] = 6;//Froid
		}
		
		List<Integer> arrlist = Arrays.asList(array);
		Collections.shuffle(arrlist);
		Integer[] targetarray = arrlist.toArray(new Integer[arrlist.size()]);
		
		for(int i = 0; i < targetarray.length; i++) {
				tab[(int) (i/largeur)][i % largeur] = targetarray[i]; //Atttribut
//				System.out.print(tab[i][j]);
			
		}
		
//		for(int i = 0; i < longueur; i++) {
//			System.out.println();
//			for(int j=0; j < largeur; j++) {
//				System.out.print(" ");
//				System.out.print(tab[i][j]);
//			}
//		}
		//Remplissage du plateau de jeu
		for(int i = 0; i < longueur; i++) {
			for(int j=0; j < largeur; j++) {
				
				//System.out.println(random);
				if(tab[i][j] == 1) {//revient a attibuer une proba d'apparition de 10% 
					 plateau_de_jeu[i][j]= new Loup(niv_loup,i,j); //Case Loup
				}else if(tab[i][j]  == 2) {//revient a attibuer une proba d'apparition de 10% 
					 plateau_de_jeu[i][j]= new Cadre('V',0,i,j); //Case Voleur
				}else if(tab[i][j]  == 3) {//revient a attibuer une proba d'apparition de 30% 
					 plateau_de_jeu[i][j]= new Cadre('X',0,i,j); //Case rien
				}else if(tab[i][j]  == 4) {//revient a attibuer une proba d'apparition de 10% 
					plateau_de_jeu[i][j]= new Cadre('N',0,i,j); //Case nourriture
				}else if(tab[i][j] == 5) {//revient a attibuer une proba d'apparition de 20% 
					plateau_de_jeu[i][j]= new Cadre('C',niv_chaud,i,j); //Case environnement chaud
				}else if(tab[i][j] == 6) {//revient a attibuer une proba d'apparition de 20% 
					plateau_de_jeu[i][j]= new Cadre('F',niv_froid,i,j); //Case environnement froid
				}
			}
		}
	 }	
	
	//Déclaration des variables de position du personnage en public avec setter & getter		
		
		//Récupérer l'objet de la position i,j
		public Cadre gettab(int i ,int j ) {
			return plateau_de_jeu[i][j];
		}
		
		//Compléter la carte de position i,j avec la valeur voulue
		public void settab(int i ,int j, char valeur,int niv) {
			plateau_de_jeu[i][j].settype(valeur);
			plateau_de_jeu[i][j].setniveau(niv);
		}
		
		//Récupérer la position d'un symbole dans le tableau
		//En pratique : servira à déterminer la position du personnage de symbole O
		public int[] recup_pos(char symbole) {
			int i_perso=-1;
			int j_perso = -1;
			int[] tab_position = new int[2] ;
			tab_position[0] = -1;
			tab_position[1] = -1;
			for(i_perso=0; i_perso < longueur; i_perso++) {
				
				for(j_perso=0; j_perso < largeur; j_perso++) {
					 //System.out.print(i_perso + " | " + j_perso + " . "); Test pour débug
					if(plateau_de_jeu[i_perso][j_perso].gettype() == symbole) {
						tab_position[0]= i_perso;
						tab_position[1] = j_perso;
						return tab_position; 
						}
					} 
			}
			return tab_position;//si pas trouver le symbol, rendre tab[0,0]
			
		}
		
	
	//Méthode pour vider le tableau
	public void vider() {
		for(int i = 0; i < longueur; i++) {
			for(int j=0; j < largeur; j++) {
				 plateau_de_jeu[i][j].settype('.');
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
			plateau_de_jeu[i_pos][j_pos].settype(symbole); // O représentant le personnage 
		//}					
	}
	
}
				
