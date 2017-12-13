import java.util.Scanner;

public class Jeu {

	 public static void main(String[] args){ 		 
		 afficher_menu();
		 int i = 0;
		 Scanner scan = new Scanner(System.in);
		 i = scan.nextInt();
         //String s = scan.nextLine();
         System.out.println(i);
         
         switch (i) {
             case 1:
                 creation_personnage();
                 break;
             case 2:
                 quitter();
                 break;
         }//Fin switch
		 scan.close();
	}//Fin de la m�thode main
	 
	 // Affichage du menu de d�part
	  public static void afficher_menu() {
	        System.out.println("///////////Jeu de survie///////////\n\n"
	        		+ "\n MENU :Faites votre choix\n\n"
	        		+ " 1 Pour cr�er votre personnage\n"
	                + "\n 2 Pour quitter l'application\n");
	  }
	  
	    public static void quitter() {
	        System.out.println("Vous fermez l'application");
	        System.exit(0);//Ne marche pas trouver une alternative
	    }
	    
	    public static void creation_personnage() {
	    	int point_restant = 15;
	    	
	        System.out.println("Cr�ation de votre personnage\n");	

	        // A ajouter :
	        //1- une condition de 10 points max par caract�ristique
	        //2- v�rifier que la saisie de d�passe pas le nombre de points restants
	        
	        //D�finition du nom 
	        System.out.print ("Saisir son nom : \n");
	        Scanner scan = new Scanner(System.in);
	        String pnom  = scan.nextLine();
	        
	        System.out.println("Vous disposez de " + point_restant + "points restants � r�partir dans les trois caract�riques (vie, r�sistance et force) pour " + pnom + "\n" );
	        
	        //D�finition de la vitalite
	        int settings = 0;
	        int pvie =0;
	        do {
	        	System.out.print ("Saisir le nombre de point � mettre en vitalit� (maximum 10) : \n");
	        	pvie  = scan.nextInt();
	        	if (pvie > 0 && pvie <= 10) settings++;
	        	else System.out.println("Saisir un nombre eligible svp!!");
	        } while(settings != 1);
	        
	      //D�finition de la r�sistance physique
	        point_restant = point_restant - pvie;
	        int presistance = 0;
	        do {
	        	System.out.print ("Saisir le nombre de point � mettre en r�sistance physique (maximum 10) : \n");
	        	System.out.print ("Il vous reste : " + point_restant + "\n");
	        	presistance  = scan.nextInt();
	        	point_restant = point_restant - presistance;
	        	if (presistance >= 0 && presistance <= 10 && point_restant >= 0) settings++;
	        	else { 
	        		System.out.println("Saisir un nombre eligible svp!!");
	        		point_restant = point_restant + presistance;
	        	}
	        } while (settings != 2);
	        
		    //D�finition de la force
	        int pforce = 0;
	        do {
	        	System.out.print ("Saisir le nombre de point � mettre en force (maximum 10) : \n");
	        	System.out.print ("Il vous reste : " + point_restant + "\n");
		        pforce = scan.nextInt();
		        if (pforce >= 0 && pforce <= 10 && point_restant >= pforce) settings++;
	        	else System.out.println("Saisir un nombre eligible svp!!");
	        } while (settings != 3);
	        
	        //Cr�ation de l'objet personnage
        	personnage perso = new personnage(pnom,pvie,presistance,pforce); //Instanciation de la classe personnage

	        //Cr�ation du plateau 
        	Plateau_de_jeu plateau = new Plateau_de_jeu(5,4);
        	Plateau_de_jeu carte = new Plateau_de_jeu(5,4);

        	//Afficher le tableau 
        	carte.vider();
        	
        	//Placement du joueur
        	carte.positionner(1,1,'O');
        	carte.afficher();
        	
        	//Variables de position pour afficher afficher au joueur 
        	int i_personnage =0;
        	int j_personnage =0;
        	
        	//Variables de position contenant la position r�elle dans le tableau
        	int i_reel =0;
        	int j_reel =0;
        	
        	int compteur= 0;
        
        do {// until compteur = 20
        	i_personnage = carte.recup_pos('O')[0];
        	i_personnage = i_personnage +1;// pour correspondre � note plateau de jeu
        	j_personnage = carte.recup_pos('O')[1];
        	j_personnage = j_personnage +1;
        	
        	i_reel = carte.recup_pos('O')[0];
        	j_reel = carte.recup_pos('O')[1];
        	
        	System.out.println("Le personnage est en : " + i_personnage + " | "+ j_personnage);	
        	compteur = compteur +1;//a rajouter ne pas incr�menter en qu'a d'�rreur de saisie

        	boolean test = false;
       	 	char valeur = ' ';
        	do { //until test=false
            	test = false;
        		System.out.println("///////////Tour " + compteur + " ///////////\n"
        	        		+ "\n Phase de d�placement - Faites un choix : \n\n"
        	        		+ "1 D�placer vers le haut \n"
        	                + "2 D�placer vers le bas\n"
        	                + "3 D�placer vers la gauche\n"
        	                + "4 D�placer vers la droite");
        		int i_deplacement = 0;
        		i_deplacement = scan.nextInt();
        	
        		switch (i_deplacement) {
        			case 1:
        					test = carte.deplacer_vertical(i_deplacement, i_reel, j_reel);
        					if(test==true) {
        						valeur = plateau.gettab(i_reel, j_reel);
        						carte.settab(i_reel, j_reel, valeur);
        						carte.settab(i_reel-1, j_reel, 'O');
        						carte.afficher();
        					}
        					break;
         
        			case 2:
        				test = carte.deplacer_vertical(i_deplacement, i_reel, j_reel);
        				if(test==true) {
        					//plateau.afficher();
        					valeur = plateau.gettab(i_reel, j_reel);
        					carte.settab(i_reel, j_reel, valeur);
        					carte.settab(i_reel+1, j_reel, 'O');
        					carte.afficher();
        				}
        				break;
        			
        			case 3:
        				test=carte.deplacer_horizontal(i_deplacement, i_reel, j_reel);
        				if(test==true) {
        					//plateau.afficher();
        					valeur = plateau.gettab(i_reel, j_reel);
        					carte.settab(i_reel, j_reel, valeur);
        					carte.settab(i_reel, j_reel -1, 'O');
        					carte.afficher();
        				}
        				break;
        			
        			case 4:
        				test=carte.deplacer_horizontal(i_deplacement, i_reel, j_reel);
        				if(test==true) {
        					//plateau.afficher();
        					valeur = plateau.gettab(i_reel, j_reel);
        					carte.settab(i_reel, j_reel, valeur);
        					carte.settab(i_reel, j_reel +1, 'O');
        					carte.afficher();
        				}
        			default : 
        				if(test==true) {
        					System.out.println("D�placement effectu� ! ");	
        				}else {
        					System.out.println("Saisie Incorrecte, recommencer ! ");	
        					compteur=compteur-1;
        				}
        		}//Fin switch
        	} while(test==false);   		 
        }while(compteur<21);
        scan.close();
	  }
}
