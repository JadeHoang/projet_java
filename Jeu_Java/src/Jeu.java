import java.util.Scanner;

public class Jeu {
	
	//Declarer les variables
	
	public static Plateau_de_jeu plateau;
	public static Carte carte;
	public static Scanner scan;
	public static Personnage perso;
	
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
	    	int point_restant = 10;
	    	
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
	        	System.out.print ("Saisir le nombre de point à mettre en vitalité (maximum 5) : \n");
	        	pvie  = scan.nextInt();
	        	if (pvie > 0 && pvie <= 5) settings++; //condition entre 1 et 5 points
	        	else System.out.println("Saisir un nombre eligible svp!!");
	        } while(settings != 1);
	        
	      //Définition de la résistance physique
	        point_restant = point_restant - pvie;
	        int presistance = 0;
	        do {
	        	System.out.print ("Saisir le nombre de point à mettre en résistance physique (maximum 3) : \n");
	        	System.out.print ("Il vous reste : " + point_restant + "points" + "\n");
	        	presistance  = scan.nextInt();
	        	point_restant = point_restant - presistance;
	        	if (presistance >= 0 && presistance <= 3 && point_restant >= 0) settings++;//condition bien vérifiée
	        	else { 
	        		System.out.println("Saisir un nombre eligible svp!!");
	        		point_restant = point_restant + presistance; //réinitialiser les points restants
	        	}
	        } while (settings != 2);
	        
		    //Définition de la force
	        int pforce = 0;
	        do {
	        	System.out.print ("Saisir le nombre de point à mettre en force (maximum 2) : \n");
	        	System.out.print ("Il vous reste : " + point_restant + "\n");
		        pforce = scan.nextInt();
		        if (pforce >= 0 && pforce <= 2 && point_restant >= pforce) settings++; //condition vérifiée
	        	else System.out.println("Saisir un nombre eligible svp!!");
	        } while (settings != 3);
	        
	        //choix des objets
	        System.out.println("///////////Choix d'objet///////////\n\n"
	        		+ "\n OBJET :Faites votre choix\n\n"
	        		+ " 1 Allumettes\n"
	                + "\n 2 Eau\n");
	        int poutil = scan.nextInt();
	        
	      //création le personnage
	        if (poutil == 1){
	        	//Instanciation de la classe personnage
	        	perso = new Personnage(pnom,pvie,presistance,pforce,new Outil('A',2));
	        }else{
	        	perso = new Personnage(pnom,pvie,presistance,pforce,new Outil('E',2));
	        }
        }
	        
	    
	   //Parametrage du jeu
	    public static void param_jeu(){
	    	
	    	//Création du plateau 
        	plateau = new Plateau_de_jeu(5,6);
        	carte = new Carte(plateau);
        	
        	//Placement du joueur
        	int i_x = carte.parent.recup_pos('X')[0];
        	int j_x = carte.parent.recup_pos('X')[1];
        	carte.positionner(i_x,j_x,'O');
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
	        	
	        	Cadre test;
	        	do { //until test=false (lire les deplacements)
	            	
	            	//afficher la valeur de zone actuelle
	            	System.out.println("Vous êtes dans une zone de " + carte.parent.gettab(i_reel,j_reel).gettype());
	            	
	            	//saute de ligne
	            	System.out.println();
	            	
	            	//afficher les choix de deplacement
	        		System.out.println("///////////Tour " + compteur + " ///////////\n"
	        	        		+ "\n Phase de déplacement - Faites un choix : \n\n"
	        	        		+ "1 Déplacer vers le haut \n"
	        	                + "2 Déplacer vers le bas\n"
	        	                + "3 Déplacer vers la gauche\n"
	        	                + "4 Déplacer vers la droite \n");
	        		int i_deplacement = 0;
	        		
	        		i_deplacement = scan.nextInt();
	        	
	        		test = carte.deplacer(i_deplacement, i_reel, j_reel);
					if(test!=null) {//quand personnage deplacer
						carte.afficher();
						
						//afficher la valeur de zone actuelle
		            	System.out.println("Vous êtes dans une zone de " + test.gettype());
		            	
		            	int utilise_outil = 0;
		            	//utiliser outil ou non
		            	if(perso.outil.getvie()>0){//si outil a encore de vie
		            		System.out.println("Voulez-vous utiliser " + perso.outil.gettype()
		            						+ "\n 1 Oui\n"
		    	        	                + "2 Non\n");
		            		utilise_outil = scan.nextInt();
		            	}
		            	
		            	if (utilise_outil == 1){
		            		if((perso.outil.gettype()=='A' && test.gettype()=='F') || (perso.outil.gettype()=='E' && test.gettype()=='C')){
		            			test.setniveau(test.getniveau()-1);//niveau de zone diminue 1 
		            			carte.settab(test.i_reel, test.j_reel, 'O', test.getniveau());//niveau de zone dans la carte diminue
		            			carte.parent.settab(test.i_reel, test.j_reel, test.gettype(), test.getniveau());//niveau de zone dans la mere de la carte diminue
		            			perso.outil.setvie(perso.outil.getvie()-1);//niveau de vie de l'outil diminue
		            		}else{
		            			perso.update_carac(test);//mise a jour l'etat des caractéristiques
		            			carte.settab(test.i_reel, test.j_reel, 'O', test.getniveau());
			            		carte.parent.settab(test.i_reel, test.j_reel, test.gettype(), test.getniveau());
		            		}
		            	}else{
		            		perso.update_carac(test);//mise a jour l'etat des caractéristiques
		            		carte.settab(test.i_reel, test.j_reel, 'O', test.getniveau());
		            		carte.parent.settab(test.i_reel, test.j_reel, test.gettype(), test.getniveau());
		            	}
						//afficher les caractéristiques du personnage
		            	System.out.println("///////////Caractéristiques//////////\n"
		            				+ "\n Vitalité: " + perso.getvitalite()+ "\n"
		            				+ "\n Résistance physique: " + perso.getresistance() + "\n"
									+ "\n Force: " + perso.getforce());
		            	
		            	System.out.println("///////////Outil//////////\n"
	            				+ "\n Nombre d'utilisation: " + perso.outil.getvie() + "\n");
		            	
		            	carte.afficher();
		            	
		            	if (perso.getvitalite() == 0) { //si personnage n'a plus de vitalité, quitter le jeu
		            		System.out.println("GAME OVER");
		            		quitter();
		            	}
					}
					
	        	} while(test==null);   	
	        	
	        	int pos = -1;
	        	pos = carte.recup_pos('.')[0];//recuperer la position des '.'
	        	if (pos == -1){//si les '.' n'existent plus, personnage gagne
	        		System.out.println("WIN!!!!!");
	        		quitter();
	        	}
	        	
	        }while(compteur<plateau.longueur*plateau.largeur +10);
	    }
}
