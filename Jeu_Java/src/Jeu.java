import java.util.Scanner;

public class Jeu {

	 public static void main(String[] args){ 
		    //Vos données, variables, différents traitements…
		 
		 afficher_menu();
		 int i = 0;
		 Scanner scan = new Scanner(System.in);
		 i = scan.nextInt();
         String s = scan.nextLine();
         System.out.println(i);
     
         switch (i) {
             case 1:
                 creation_personnage();
                 break;
             case 2:
                 quitter();
                 break;
         }//Fin switch
		 
	}//Fin de la méthode main
	 
	 // Affichage du menu de départ
	  public static void afficher_menu() {
	        System.out.println("///////////Jeu de survie///////////\n\n"
	        		+ "\n MENU :Faites votre choix\n\n"
	        		+ " 1 Pour créer votre personnage\n"
	                + "\n 2 Pour quitter l'application\n");
	  }
	  
	    public static void quitter() {
	        System.out.println("Vous fermez l'application");
	        System.exit(0);//Ne marche pas trouver une alternative
	    }
	    
	    public static void creation_personnage() {
	    	int point_restant = 15;
	    	
	        System.out.println("Création de votre personnage\n");	

	        // A ajouter :
	        //1- une condition de 10 points max par caractéristique
	        //2- vérifier que la saisie de dépasse pas le nombre de points restants
	        
	        //Définition du nom 
	        System.out.print ("Saisir son nom : \n");
	        Scanner scan = new Scanner(System.in);
	        String pnom  = scan.nextLine();
	        
	        System.out.println("Vous disposez de " + point_restant + "points restants à répartir dans les trois caractériques (vie, résistance et force) pour " + pnom + "\n" );
	        
	        //Définition de la vitalite
	        System.out.print ("Saisir le nombre de point à mettre en vitalité (maximum 10) : \n");
	        Scanner scan2 = new Scanner(System.in);
	        int pvie  = scan.nextInt();
	        
	      //Définition de la résistance physique
	        point_restant = point_restant - pvie;
	        System.out.print ("Saisir le nombre de point à mettre en résistance physique (maximum 10) : \n");
	        System.out.print ("Il vous reste : " + point_restant + "\n");
	        Scanner scan3 = new Scanner(System.in);
	        int presistance  = scan3.nextInt();
	        
		    //Définition de la force
	        point_restant = point_restant - presistance;
	        System.out.print ("Saisir le nombre de point à mettre en force (maximum 10) : \n");
	        System.out.print ("Il vous reste : " + point_restant + "\n");
	        Scanner scan4 = new Scanner(System.in);
	        int pforce  = scan4.nextInt();
	        
	        //Création de l'objet personnage
        	personnage perso = new personnage(pnom,pvie,presistance,pforce); //Instanciation de la classe personnage

	        
	    }
	
}
