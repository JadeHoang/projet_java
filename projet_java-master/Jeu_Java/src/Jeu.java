import java.util.Scanner;

public class Jeu {
	
	//Declarer les variables
	
	public static Plateau_de_jeu plateau;
	public static Carte carte;
	public static Scanner scan;
	
	//Fonction principale
	 public static void main(String[] args){ 
		 //Afficher le menu
		 afficher_menu();
		 int i = 0;
		 scan = new Scanner(System.in);
		 i = scan.nextInt();
         //String s = scan.nextLine();
         System.out.println(i);
         
         switch (i) {
             case 1:
                 creation_personnage();//creation du personnage
                 break;
             case 2:
                 quitter();//arreter le jeu 
                 break;
         }//Fin switch
		 
		 
		 param_jeu();
		 boucle_jeu();
		 
		 scan.close();
	}//Fin de la méthode main
	 
	 // Affichage du menu de départ
	  public static void afficher_menu() {
	        System.out.println("///////////Jeu de survie///////////\n\n"
	        		+ "\n MENU :Faites votre choix\n\n"
	        		+ " 1 Pour créer votre personnage\n"
	                + "\n 2 Pour quitter l'application\n");
	  }
	  
	 //Arreter le jeu
	    public static void quitter() {
	        System.out.println("Vous fermez l'application");
	        System.exit(0);
	    }
	   
	 // Création du personnage
	    public static void creation_personnage() {
	    	int point_restant = 15;
	    	
	        System.out.println("Création de votre personnage\n");	

	        // A ajouter :
	        //1- une condition de 10 points max par caractéristique
	        //2- vérifier que la saisie de dépasse pas le nombre de points restants
	        
	        //Définition du nom 
	        System.out.print ("Saisir son nom : \n");
	        
	        String pnom  = scan.next();
	        
	        System.out.println("Vous disposez de " + point_restant + "points restants à répartir dans les trois caractériques (vie, résistance et force) pour " + pnom + "\n" );
	        
	        //Définition de la vitalité
	        int settings = 0;
	        int pvie = 0;
	        do {
	        	System.out.print ("Saisir le nombre de point à mettre en vitalité (maximum 10) : \n");
	        	pvie  = scan.nextInt();
	        	if (pvie > 0 && pvie <= 10) settings++; //condition entre 1 et 10 points
	        	else System.out.println("Saisir un nombre eligible svp!!");
	        } while(settings != 1);
	        
	      //Définition de la résistance physique
	        point_restant = point_restant - pvie;
	        int presistance = 0;
	        do {
	        	System.out.print ("Saisir le nombre de point à mettre en résistance physique (maximum 10) : \n");
	        	System.out.print ("Il vous reste : " + point_restant + "points" + "\n");
	        	presistance  = scan.nextInt();
	        	point_restant = point_restant - presistance;
	        	if (presistance >= 0 && presistance <= 10 && point_restant >= 0) settings++;//condition bien vérifiée
	        	else { 
	        		System.out.println("Saisir un nombre eligible svp!!");
	        		point_restant = point_restant + presistance; //réinitialiser les points restants
	        	}
	        } while (settings != 2);
	        
		    //Définition de la force
	        int pforce = 0;
	        do {
	        	System.out.print ("Saisir le nombre de point à mettre en force (maximum 10) : \n");
	        	System.out.print ("Il vous reste : " + point_restant + "\n");
		        pforce = scan.nextInt();
		        if (pforce >= 0 && pforce <= 10 && point_restant >= pforce) settings++; //condition vérifiée
	        	else System.out.println("Saisir un nombre eligible svp!!");
	        } while (settings != 3);
	        
	        //Création de l'objet personnage
        	personnage perso = new personnage(pnom,pvie,presistance,pforce); //Instanciation de la classe personnage
        	
        	
	    }
	    
	   //Parametrage du jeu
	    public static void param_jeu(){
	    	
	    	//Création du plateau 
        	plateau = new Plateau_de_jeu(5,4);
        	carte = new Carte(plateau);
        	
        	//Placement du joueur
        	carte.positionner(0,0,'O');
        	carte.afficher();
	    }
	    
	    //Boucle principale de jeu
	    public static void boucle_jeu(){
	    	//Variables de position contenant la position réelle dans le tableau
        	int i_reel =0;
        	int j_reel =0;
        	
        	int compteur= 0;
        
        	// until compteur = 20 (verifier les tours)
	        do {
	        	
	        	i_reel = carte.recup_pos('O')[0];
	        	j_reel = carte.recup_pos('O')[1];
	        	
	        	System.out.println("Le personnage est en : " + (i_reel+1) + " | "+ (j_reel+1));	
	        	compteur = compteur +1;//a rajouter ne pas incrémenter en qu'a d'erreur de saisie
	
	        	boolean test = false;
	       	 	
	        	do { //until test=false (lire les deplacements)
	            	test = false;
	        		System.out.println("///////////Tour " + compteur + " ///////////\n"
	        	        		+ "\n Phase de déplacement - Faites un choix : \n\n"
	        	        		+ "1 Déplacer vers le haut \n"
	        	                + "2 Déplacer vers le bas\n"
	        	                + "3 Déplacer vers la gauche\n"
	        	                + "4 Déplacer vers la droite");
	        		int i_deplacement = 0;
	        		
	        		i_deplacement = scan.nextInt();
	        	
	        		test = carte.deplacer(i_deplacement, i_reel, j_reel);
					if(test==true) {//quand personnage deplacer
						carte.afficher();
					}else {
    					System.out.println("Saisie Incorrecte, recommencer ! ");
    					carte.afficher();
					}
					
	        	} while(test==false);   		 
	        }while(compteur<21);
	    }
}
