import java.util.Scanner;

public class Jeu {

	 public static void main(String[] args){ 
		    //Vos donn�es, variables, diff�rents traitements�
		 
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
	        System.out.print ("Saisir le nombre de point � mettre en vitalit� (maximum 10) : \n");
	        Scanner scan2 = new Scanner(System.in);
	        int pvie  = scan.nextInt();
	        
	      //D�finition de la r�sistance physique
	        point_restant = point_restant - pvie;
	        System.out.print ("Saisir le nombre de point � mettre en r�sistance physique (maximum 10) : \n");
	        System.out.print ("Il vous reste : " + point_restant + "\n");
	        Scanner scan3 = new Scanner(System.in);
	        int presistance  = scan3.nextInt();
	        
		    //D�finition de la force
	        point_restant = point_restant - presistance;
	        System.out.print ("Saisir le nombre de point � mettre en force (maximum 10) : \n");
	        System.out.print ("Il vous reste : " + point_restant + "\n");
	        Scanner scan4 = new Scanner(System.in);
	        int pforce  = scan4.nextInt();
	        
	        //Cr�ation de l'objet personnage
        	personnage perso = new personnage(pnom,pvie,presistance,pforce); //Instanciation de la classe personnage

	        
	    }
	
}
