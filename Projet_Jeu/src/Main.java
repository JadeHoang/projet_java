import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.time.format.DateTimeParseException;


    public static void main(String[] args) {
        int i = 0;
        Scanner scan = new Scanner(System.in);
        do {
            afficher_menu();
            i = scan.nextInt();
            String s = scan.nextLine();
            System.out.println(i);
            switch (i) {
                case 1:
                    creer();
                    break;
                case 2:
                    ouvrir();
                    break;
                case 3:
                    sauvegarder();
                    break;
                case 4:
                    inserer();
                    break;
                case 5:
                    supprimer();
                    break;
                case 6:
                    afficher();
                    break;
                case 7:
                    rechercher();
                    break;
                case 8:
                    quitter();
                    break;
            }
        } while (i != 8);
    }

    public static void afficher_menu() {
        System.out.println("\nFaites votre choix\n 1 pour créer\n"
                + "\n 2 pour ouvrir\n"
                + "\n 3 pour sauvegarder\n"
                + "\n 4 pour insÃ©rer\n"
                + "\n 5 pour supprimer\n"
                + "\n 6 pour afficher\n"
                + "\n 7 pour rechercher\n"
                + "\n 8 pour quitter\n");
    }

    public static void creer() {
        System.out.println("on crée la base");
        bdn = new BaseDeNews();
        bdn.initialise();
    }

    public static void ouvrir() {
        System.out.println("on ouvre");
    }

    public static void sauvegarder() {
        System.out.println("on sauvegarde");
    }

    public static void inserer() {
    	//Pour insérer un article 1 et une Photo 2
        System.out.println ("Insertion d'une nouvelle news\n" + "Tapez 1 pour insérer un article et 2 pour une photo");
        Scanner scan5 = new Scanner(System.in);
        int choix_saisie =0;
        choix_saisie =scan5.nextInt(); 
        
     
        
        
        //Choix dans le type de création
        switch (choix_saisie) {
        case 1:// Article
        	System.out.print ("saisir le titre \n");
            Scanner scan6= new Scanner(System.in);
            String titre  = scan6.nextLine();
            System.out.print ("saisir l'auteur \n");
            Scanner scan7 = new Scanner(System.in);
            String auteur  = scan7.nextLine();System.out.print ("saisir la source \n");
            Scanner scan8 = new Scanner(System.in);
            String ssource = scan8.nextLine();
            //puis créer l'URL (try/catch)
            URL source=null;
            try
            {
                source = new URL(ssource);
            }
            catch (MalformedURLException e)
            {
                System.err.println("New URL failed");
                System.err.println("exception thrown:"+e.getMessage()) ;
            }
            System.out.print ("saisir la date \n");
            Scanner scan9 = new Scanner(System.in);
            String ddate = scan9.nextLine();
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.now();
            try 
            {
                date = LocalDate.parse(ddate, formatter);
            } 
            catch (DateTimeParseException e) 
            {
                System.err.println("New Date failed");
                System.err.println("exception thrown:"+e.getMessage()) ;
            }
            
            System.out.print ("saisir le lien \n");
            Scanner scan10 = new Scanner(System.in);
            String  lien2 = scan10.nextLine();
            URL lien=null;
            try
            {
                lien = new URL(lien2);
            }
            catch (MalformedURLException e)
            {
                System.err.println("New URL failed");
                System.err.println("exception thrown:"+e.getMessage()) ;
            }
            
            System.out.print ("saisir le texte \n");
            Scanner scan11 = new Scanner(System.in);
            String  texte = scan11.nextLine();
            
            System.out.print ("saisir la version \n");
            Scanner scan12 = new Scanner(System.in);
            int  version = scan12.nextInt();
            
            //Déclaration d'un nouvel objet news
        	News n = new Articles(titre,auteur,source,date,lien,texte,version); //Instanciation Classe Articles
            
            bdn.ajoute (n);
            System.out.println(n);
            break;
        case 2://Photo
        	
        	
        	//Déclaration d'un nouvel objet news
        	News m = new Photos(titre,auteur,source,date,photo,format,couleur); //Instanciation Classe Photos
            bdn.ajoute (m);
            System.out.println(m);
        	break;
        }
        
//        System.out.print ("saisir le titre \n");
//        Scanner scan = new Scanner(System.in);
//        String titre  = scan.nextLine();
//        System.out.print ("saisir l'auteur \n");
//        Scanner scan2 = new Scanner(System.in);
//        String auteur  = scan2.nextLine();System.out.print ("saisir la source \n");
//        Scanner scan3 = new Scanner(System.in);
//        String ssource = scan3.nextLine();
//        //puis créer l'URL (try/catch)
//        URL source=null;
//        try
//        {
//            source = new URL(ssource);
//        }
//        catch (MalformedURLException e)
//        {
//            System.err.println("New URL failed");
//            System.err.println("exception thrown:"+e.getMessage()) ;
//        }
//        System.out.print ("saisir la date \n");
//        Scanner scan4 = new Scanner(System.in);
//        String ddate = scan4.nextLine();
//        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
// LocalDate date = LocalDate.now();
//  try 
//   {
//        date = LocalDate.parse(ddate, formatter);
//     } 
//     catch (DateTimeParseException e) 
//     {
//         System.err.println("New Date failed");
//          System.err.println("exception thrown:"+e.getMessage()) ;
//        }
        
//        News n = new News(titre,auteur,source,date);
// //         n.saisir();  
//        bdn.ajoute (n);
//        System.out.println(n);
}
public static void supprimer() {
        System.out.println("on supprime");
        bdn.supprimer();
    }
    public static void afficher() {
        System.out.println("on affiche la base");
        bdn.affiche();
    }
    public static void rechercher() {
        System.out.println("on recherche");
    }
    public static void quitter() {
        System.out.println("quitter");
    }
    
}
