import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Jeu extends Application{
	
	//Declarer les variables
	
	public static Plateau_de_jeu plateau;
	public static Carte carte;
	public static Scanner scan;
	public static Personnage perso;
	
	private VBox menuBox = new VBox();
	private VBox titleBox = new VBox();
	private StackPane root = new StackPane();
	private Scene scene;
	private int utilise_outil = 0;
	
	private boolean jeu = true;
	private 	int compteur = 0;
	private enum DEPLACESTATE{
		NOMOVE,
		MOVEUP,
		MOVEDOWN,
		MOVELEFT,
		MOVERIGHT
	};
	
	private static DEPLACESTATE mouve = DEPLACESTATE.NOMOVE;
	
	//creer graphic du menu
	private Parent cree_menu() {
		root.getChildren().clear();//vider la fenetre
		titleBox.getChildren().clear();//vider la box des titres
		menuBox.getChildren().clear();
		
		//titre
		Text text = new Text("SURVIE ILE DESERT");
		text.setFill(Color.BLACK);
		Font f0nt = Font.font("Arial",FontWeight.BOLD,30);
		text.setFont(f0nt);
		
		//bouton Demarrer
        Font f0nt1 = Font.font("Arial",FontWeight.BOLD,30);
		Button StartBtn = new Button("Démarrer");
		StartBtn.setFont(f0nt1);
		StartBtn.setOnAction(e -> cree_menu_perso());
		
		//bouton quitter
		Button ExitBtn = new Button("Quitter");
		ExitBtn.setFont(f0nt1);
		ExitBtn.setOnAction(e -> quitter());
		
		titleBox.getChildren().add(text);
		menuBox.getChildren().addAll(StartBtn,ExitBtn);
		menuBox.setStyle("-fx-spacing: 20");
		menuBox.setAlignment(Pos.CENTER);
		titleBox.setStyle("-fx-padding: 25");
		titleBox.setAlignment(Pos.TOP_CENTER);
		root.getChildren().addAll(titleBox,menuBox);
        
		return root;
	}
	
	//creer menu pour creation du personnage
	private Parent cree_menu_perso() {
		jeu = true;
		root.getChildren().clear();//vider la fenetre
		titleBox.getChildren().clear();//vider la box des titres
		menuBox.getChildren().clear();
		//titre
		Text text = new Text("CREER VOTRE PERSONNAGE");
		text.setFill(Color.BLACK);
		Font f0nt = Font.font("Arial",FontWeight.BOLD,20);
		text.setFont(f0nt);
		
		//les caracteriistiques
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		HBox hb4 = new HBox();
		HBox hb5 = new HBox();
		
		TextField textfield = new TextField("Votre nom");
		
		Label label1 = new Label("Vitalité");
		Label label2 = new Label("Résistance");
		Label label3 = new Label("Force");
		
		Slider slider1 = new Slider(1, 5, 0);
		Slider slider2 = new Slider(0, 3, 0);
		Slider slider3 = new Slider(0, 2, 0);
		
		Text value1 = new Text();
		Text value2 = new Text();
		Text value3 = new Text();
		
		//vitalite
		slider1.setShowTickLabels(true);
		slider1.setMajorTickUnit(1f);
		slider1.valueProperty().addListener(
		        (observable, oldvalue, newvalue) ->
		        {
		            int i = newvalue.intValue();
		            value1.setText(Integer.toString(i));
		        } );

		hb1.getChildren().addAll(label1,slider1,value1);
		
		//resistance
		slider2.setShowTickLabels(true);
		slider2.setMajorTickUnit(1f);
		slider2.valueProperty().addListener(
		        (observable, oldvalue, newvalue) ->
		        {
		            int i = newvalue.intValue();
		            value2.setText(Integer.toString(i));
		        } );

		hb2.getChildren().addAll(label2,slider2,value2);
		
		//force
		slider3.setShowTickLabels(true);
		slider3.setMajorTickUnit(1f);
		slider3.valueProperty().addListener(
		        (observable, oldvalue, newvalue) ->
		        {
		            int i = newvalue.intValue();
		            value3.setText(Integer.toString(i));
		        } );

		hb3.getChildren().addAll(label3,slider3,value3);
		
		//outil
		Text outil = new Text("Outil");

		final ToggleGroup group = new ToggleGroup();
		
		RadioButton rb1 = new RadioButton("Allumettes");
		rb1.setToggleGroup(group);

		RadioButton rb2 = new RadioButton("Eau");
		rb2.setToggleGroup(group);
	
		ImageView icon = new ImageView();

		rb1.setUserData("Allumettes");
		rb2.setUserData("Eau");
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (group.getSelectedToggle() != null) {
	                final Image image = new Image(
	                    getClass().getResourceAsStream("/images/"+
	                        group.getSelectedToggle().getUserData().toString() + 
	                            ".png"
	                    )
	                );
	                icon.setImage(image);
	            }       
				
			}
		});
		
		rb2.setSelected(true);
		rb2.requestFocus();
		
		hb4.getChildren().addAll(outil,rb1,rb2);
		
		//bouton valider
		Font f0nt2 = Font.font("Arial",FontWeight.BOLD,15);
		Button OkBtn = new Button("Valider");
		OkBtn.setFont(f0nt2);
		OkBtn.setOnAction(e -> creation_personnage(textfield.getText(),(int)slider1.getValue(),(int)slider2.getValue(),(int)slider3.getValue(),group.getSelectedToggle().getUserData().toString()));
		Button ReBtn = new Button("Retour vers menu");
		ReBtn.setFont(f0nt2);
		ReBtn.setOnAction(e -> cree_menu());
		hb5.getChildren().addAll(OkBtn,ReBtn);
		
		menuBox.getChildren().addAll(textfield,hb1,hb2,hb3,hb4,icon,hb5);
		
		titleBox.getChildren().add(text);
		titleBox.setStyle("-fx-padding: 25");
		titleBox.setAlignment(Pos.TOP_CENTER);
		
		menuBox.setAlignment(Pos.CENTER);
		menuBox.setStyle("-fx-padding: 0 250 0 250; -fx-spacing: 30");
		
		
		hb1.setStyle("-fx-spacing: 50");
		hb2.setStyle("-fx-spacing: 40");
		hb3.setStyle("-fx-spacing: 55");
		hb4.setStyle("-fx-spacing: 65");
		hb5.setStyle("-fx-spacing: 60");
		root.getChildren().addAll(titleBox,menuBox);
		return root;
	}
	
	
	//Fonction principale
	 public static void main(String[] args){ 
		 launch(args);
	}//Fin de la méthode main
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		//Afficher le menu
		
		//la fenetre du jeu 
		primaryStage.setTitle("Survie île déserte");
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		
		//
		scene = new Scene(cree_menu());
		primaryStage.setScene(scene);
		
		
//		Canvas canvas = new Canvas(700,400);
//		root.getChildren().add(canvas);
//		
//		GraphicsContext gp = canvas.getGraphicsContext2D();
//		
//		gp.setFill(Color.BLACK);
//		gp.setLineWidth(2);
//		Font f0nt = Font.font("Arial",FontWeight.BOLD,30);
//		gp.setFont(f0nt);
//		gp.fillText("SURVIE ÎLE DESERTE",200,50);
//		
		
		
		primaryStage.show();
		primaryStage.setResizable(false);//fixe la taille de la fenetre
//		 afficher_menu();
//		 int i = 0;
//		 scan = new Scanner(System.in);
//		 i = scan.nextInt();
//        //String s = scan.nextLine();
//        System.out.println(i);
//        
//        switch (i) {
//            case 1:
//                creation_personnage();//creation du personnage
//                break;
//            case 2:
//                quitter();//arreter le jeu 
//                break;
//        }//Fin switch
//		 
//		 
//		 param_jeu();
//		 boucle_jeu();
//		 
//		 scan.close();
	}
	
	 
	 // Affichage du menu de départ
	  /*public void afficher_menu() {
	        System.out.println("///////////Jeu de survie///////////\n\n"
	        		+ "\n MENU :Faites votre choix\n\n"
	        		+ " 1 Pour créer votre personnage\n"
	                + "\n 2 Pour quitter l'application\n");
	  }*/
	  
	 //Arreter le jeu
	    public static void quitter() {
	        System.out.println("Vous fermez l'application");
	        System.exit(0);
	    }
	   
	 // Création du personnage
	    public void creation_personnage(String pnom, int pvie, int presistance, int pforce, String poutil) {

	    /*	int point_restant = 10;
	    	
	        System.out.println("Création de votre personnage\n");	

	         A ajouter :
	        1- une condition de 10 points max par caractéristique
	        2- vérifier que la saisie de dépasse pas le nombre de points restants
	        
	        Définition du nom 
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
	        int poutil = scan.nextInt();*/
	        
	      //création le personnage
	        if (poutil == "Allumettes"){
	        	//Instanciation de la classe personnage
	        	perso = new Personnage(pnom,pvie,presistance,pforce,new Outil('A',2));
	        }else{
	        	perso = new Personnage(pnom,pvie,presistance,pforce,new Outil('E',2));
	        }
	        
	        param_jeu();
        }
	        
	 //Parametrage du jeu
	    public void param_jeu(){
	    	
	    	GridPane grid = new GridPane();
	    	//Création du plateau 
        	plateau = new Plateau_de_jeu(5,6);
        	carte = new Carte(plateau);
        	
        	//Placement du joueur
        	int i_x = carte.parent.recup_pos('X')[0];
        	int j_x = carte.parent.recup_pos('X')[1];
        	carte.positionner(i_x,j_x,'O');
        	carte.afficher(grid);
        	
        	grid.setAlignment(Pos.CENTER);//centrer la carte
        	root.getChildren().clear();
        	Rectangle bg = new Rectangle(800,600);
        	bg.setFill(Color.BLACK);
        	
        	Text zone = new Text("Vous êtes dans une zone de " + carte.parent.gettab(i_x,j_x).gettype());
        	Text tour = new Text("Il reste " + (plateau.longueur*plateau.largeur) + " de tours");
        	//affiche les propriétés du jeu a l'ecran
        	
		affiche_ecran(zone,tour,new VBox(),grid, bg );
        	
        	
        	scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

				@Override
				public void handle(KeyEvent event) {
					if (jeu) {
						switch(event.getCode()) {
							case UP: 
								mouve = DEPLACESTATE.MOVEUP;
								boucle_jeu();
								break;
							case DOWN:
								mouve = DEPLACESTATE.MOVEDOWN;
								boucle_jeu();
								break;
							case LEFT:
								mouve = DEPLACESTATE.MOVELEFT;
								boucle_jeu();
								break;
							case RIGHT:
								mouve = DEPLACESTATE.MOVERIGHT;
								boucle_jeu();
								break;
							default:
								mouve = DEPLACESTATE.NOMOVE;
								break;		
						}
					}
				}
        	});
	    }
	    
	    //Boucle principale de jeu
	    public void boucle_jeu(){
	    	//Variables de position contenant la position réelle dans le tableau
        	int i_reel =0;
        	int j_reel =0;

        	// until compteur = 20 (verifier les tours)
//	        do {
        		if (compteur<plateau.longueur*plateau.largeur) {
	        	
	        	i_reel = carte.recup_pos('O')[0];
	        	j_reel = carte.recup_pos('O')[1];
	        GridPane grid = new GridPane();
	        
	        
//	        	System.out.println("Le personnage est en : " + (i_reel+1) + " | "+ (j_reel+1));
	        	
	        	Cadre test;
//	        	do { //until test=false (lire les deplacements)
	            	
	            	//afficher la valeur de zone actuelle
//	            	System.out.println("Vous êtes dans une zone de " + carte.parent.gettab(i_reel,j_reel).gettype());
//	            	Text zone = new Text("1/Vous êtes dans une zone de " + carte.parent.gettab(i_reel,j_reel).gettype());
	            
	            	//saute de ligne
//	            	System.out.println();
	            	
	            	//afficher les choix de deplacement
//	        		System.out.println("///////////Tour " + compteur + " ///////////\n"
//	        	        		+ "\n Phase de déplacement - Faites un choix : \n\n"
//	        	        		+ "1 Déplacer vers le haut \n"
//	        	                + "2 Déplacer vers le bas\n"
//	        	                + "3 Déplacer vers la gauche\n"
//	        	                + "4 Déplacer vers la droite \n");
	            	Text tour = new Text("Il reste " + (plateau.longueur*plateau.largeur - compteur) + " de tours");
	            	
	        		int i_deplacement = 0;
	        		
	        		switch(mouve) {
	        			case MOVEUP:
	        				i_deplacement = 1;
	        				break;
	        			case MOVEDOWN:
	        				i_deplacement = 2;
	        				break;
	        			case MOVELEFT:
	        				i_deplacement = 3;
	        				break;
	        			case MOVERIGHT:
	        				i_deplacement = 4;
	        				break;
	        			case NOMOVE:
	        				break;
	        		}
	        		
//	        		i_deplacement = scan.nextInt();
	        		Rectangle bg = new Rectangle(800,600);
		        	bg.setFill(Color.BLACK);
		        	VBox menuBox_err = new VBox();//affichage une message erreur 
		        	
	        		test = carte.deplacer(i_deplacement, i_reel, j_reel);
					if(test!=null) {//quand personnage deplacer
						
						compteur = compteur +1;//a rajouter ne pas incrémenter en qu'a d'erreur de saisie
						
				        	carte.afficher(grid);
				        	
				        	grid.setAlignment(Pos.CENTER);//centrer la carte
				        	root.getChildren().clear();
				        
				        	root.getChildren().addAll(bg,grid);
						
						//afficher la valeur de zone actuelle
//		            	System.out.println("Vous êtes dans une zone de " + test.gettype());
				    Text zone = new Text("Vous êtes dans une zone de " + carte.parent.gettab(carte.recup_pos('O')[0],carte.recup_pos('O')[1]).gettype());
					    
				    
						utilise_outil = 0;
			            	//utiliser outil ou non
			            	if((perso.outil.gettype()=='A' && test.gettype()=='F') || (perso.outil.gettype()=='E' && test.gettype()=='C')){
			            		if(perso.outil.getvie()>0){//si outil a encore de vie
	//		            		System.out.println("Voulez-vous utiliser " + perso.outil.gettype()
	//		            						+ "\n 1 Oui\n"
	//		    	        	                + "2 Non\n");
	//		            		utilise_outil = scan.nextInt();
			            			
			            			Stage stage_outil = new Stage();
			            			stage_outil.setTitle("Utilisation de l'outil");
			            			stage_outil.setHeight(100);
			            			stage_outil.setWidth(200);
			            			
			            			VBox menubox_outil = new VBox();
			            			HBox hb_outil = new HBox();
			            			StackPane pane_outil = new StackPane();
			            			Scene scene_outil = new Scene(pane_outil);
			            			
			            			Label label_outil = new Label("Voulez-vous utiliser l'outil?");
			            			Button Obtn = new Button("Oui");
			            			Button Nbtn = new Button("Non");
			            			
			            			hb_outil.getChildren().addAll(Obtn,Nbtn);
			            			hb_outil.setStyle("-fx-spacing: 20;");
			            			hb_outil.setAlignment(Pos.CENTER);
			            			
			            			menubox_outil.getChildren().addAll(label_outil,hb_outil);
			            			menubox_outil.setAlignment(Pos.CENTER);
			            			menubox_outil.setStyle("-fx-padding: 10 0 20 0; -fx-spacing: 10;");
			            			
			            			pane_outil.getChildren().addAll(menubox_outil);
			            			
			            			stage_outil.setScene(scene_outil);
			            			
			            			//fenetre pour demandee le joueur si il veut utiliser l'outil
			            			Obtn.setOnAction(response -> {stage_outil.close();utilise_outil = 1;});//si oui,ferme la fenetre et initialise utilise_outil = 1
			            			Nbtn.setOnAction(response -> {stage_outil.close();});// si non, ferme la fenetre
			            			stage_outil.initModality(Modality.APPLICATION_MODAL);//pauser le programme actuel
			            			stage_outil.showAndWait();//attend input du joueur
			            			
			            		}
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
			            	
			            	affiche_ecran(zone,tour, menuBox_err,grid, bg );
					}else{
						
						Text zone = new Text("Vous êtes dans une zone de " + carte.parent.gettab(i_reel,j_reel).gettype());
						
						carte.afficher(grid);
			        	
						grid.setAlignment(Pos.CENTER);//centrer la carte
			        		root.getChildren().clear();
			        		root.getChildren().addAll(bg,grid);
					
						Text err = new Text("Vous ne pouvez vous déplacer vers cette direction!");
						Font f = Font.font("Arial",15);
			            	err.setFont(f);
			            	err.setFill(Color.WHITE);
			            	menuBox_err.getChildren().addAll(err);
			            	menuBox_err.setAlignment(Pos.BOTTOM_CENTER);
			            	menuBox_err.setStyle("-fx-padding:10");
			            	
			            	affiche_ecran(zone,tour, menuBox_err,grid, bg );
					}
		            	
						//afficher les caractéristiques du personnage
//		            	System.out.println("///////////Caractéristiques//////////\n"
//		            				+ "\n Vitalité: " + perso.getvitalite()+ "\n"
//		            				+ "\n Résistance physique: " + perso.getresistance() + "\n"
//									+ "\n Force: " + perso.getforce());
		            
//		            	Font f = Font.font("Arial",15);
//		            	VBox menuBox_zone = new VBox();
//		            	zone.setFont(f);
//		            	zone.setFill(Color.WHITE);
//		            	menuBox_zone.getChildren().addAll(zone);
//		            	menuBox_zone.setAlignment(Pos.BOTTOM_CENTER);
//		            	
//		            	VBox message = new VBox();
//		            	message.getChildren().addAll(menuBox_zone,menuBox_err);
//		            	message.setAlignment(Pos.BOTTOM_CENTER);
//		            	message.setStyle("-fx-padding:10");
//		            	
//		            	Text vie = new Text("Vitalité: " + perso.getvitalite());
//		            	Text resis = new Text("Résistance: " + perso.getresistance());
//		            	Text force = new Text("Force: " + perso.getforce());
//		            	
//		            	tour.setFont(f);
//		            	tour.setFill(Color.WHITE);
//		            	vie.setFont(f);
//		            	vie.setFill(Color.WHITE);
//		            	resis.setFont(f);
//		            	resis.setFill(Color.WHITE);
//		            	force.setFont(f);
//		            	force.setFill(Color.WHITE);
//		            	
//		            	
//		            	menuBox.getChildren().clear();
//		            	menuBox.getChildren().addAll(tour,vie,resis,force);
//		            	menuBox.setAlignment(Pos.TOP_RIGHT);
//		            	menuBox.setStyle("-fx-padding:10 10 0 40");
//		            
////		            	System.out.println("///////////Outil//////////\n"
////	            				+ "\n Nombre d'utilisation: " + perso.outil.getvie() + "\n");
//		            	Text nb_outil = new Text("Nombre d'utilisation: "+perso.outil.getvie());
//		            	Text outil = new Text("Outil: " + perso.outil.gettype());
//		            	
//		            	outil.setFont(f);
//		            	outil.setFill(Color.WHITE);
//		            	nb_outil.setFont(f);
//		            	nb_outil.setFill(Color.WHITE);
//		            	
//		            	VBox menuBox_outil = new VBox();
//		            	menuBox_outil.getChildren().addAll(outil,nb_outil);
//		            	menuBox_outil.setAlignment(Pos.TOP_LEFT);
//		            	menuBox_outil.setStyle("-fx-padding:10 40 0 10");
//		            	
//		            	
//		            	carte.afficher(grid);
//		            	grid.setAlignment(Pos.CENTER);//centrer la carte
//			        	root.getChildren().clear();
//			        	root.getChildren().addAll(bg,grid,menuBox,menuBox_outil,message);
					
		            	//affiche les propriétés du jeu a l'ecran
//					affiche_ecran(zone,tour, menuBox_err,grid, bg );
		            	
		            	if (perso.getvitalite() == 0 || compteur == plateau.longueur*plateau.largeur) { //si personnage n'a plus de vitalité, quitter le jeu
//		            		System.out.println("GAME OVER");
		            		
		            		root.getChildren().add(resultat('L'));
		            		
		           
		            	}
					
					
//	        	} while(test==null);   	
	        	
	        	int pos = -1;
	        	pos = carte.recup_pos('.')[0];//recuperer la position des '.'
	        	if (pos == -1){//si les '.' n'existent plus, personnage gagne
//	        		System.out.println("WIN!!!!!");
	        		root.getChildren().add(resultat('W'));
	        		
	        	}
        	}
//	        }while(compteur<plateau.longueur*plateau.largeur +10);
	    }
	    
	    public Parent affiche_ecran(Text zone, Text tour, VBox menuBox_err, GridPane grid, Rectangle bg ) {
	    		
	    		Font f = Font.font("Arial",15);
	        	VBox menuBox_zone = new VBox();
	        	zone.setFont(f);
	        	zone.setFill(Color.WHITE);
	        	menuBox_zone.getChildren().addAll(zone);
	        	menuBox_zone.setAlignment(Pos.BOTTOM_CENTER);
	        	
	        	VBox message = new VBox();
	        	message.getChildren().addAll(menuBox_zone,menuBox_err);
	        	message.setAlignment(Pos.BOTTOM_CENTER);
	        	message.setStyle("-fx-padding:10");
	        	
	        	Text vie = new Text("Vitalité: " + perso.getvitalite());
	        	Text resis = new Text("Résistance: " + perso.getresistance());
	        	Text force = new Text("Force: " + perso.getforce());
	        	
	        	tour.setFont(f);
	        	tour.setFill(Color.WHITE);
	        	vie.setFont(f);
	        	vie.setFill(Color.WHITE);
	        	resis.setFont(f);
	        	resis.setFill(Color.WHITE);
	        	force.setFont(f);
	        	force.setFill(Color.WHITE);
	        	
	        	
	        	menuBox.getChildren().clear();
	        	menuBox.getChildren().addAll(tour,vie,resis,force);
	        	menuBox.setAlignment(Pos.TOP_RIGHT);
	        	menuBox.setStyle("-fx-padding:10 10 0 40");
	        
	        	Text nb_outil = new Text("Nombre d'utilisation: "+perso.outil.getvie());
	        	Text outil = new Text("Outil: " + perso.outil.gettype());
	        	
	        	outil.setFont(f);
	        	outil.setFill(Color.WHITE);
	        	nb_outil.setFont(f);
	        	nb_outil.setFill(Color.WHITE);
	        	
	        	VBox menuBox_outil = new VBox();
	        	menuBox_outil.getChildren().addAll(outil,nb_outil);
	        	menuBox_outil.setAlignment(Pos.TOP_LEFT);
	        	menuBox_outil.setStyle("-fx-padding:10 40 0 10");
	        	
	        	
	        	carte.afficher(grid);
	        	grid.setAlignment(Pos.CENTER);//centrer la carte
	        	root.getChildren().clear();
	        	root.getChildren().addAll(bg,grid,menuBox,menuBox_outil,message);
	        	
	        	return root;
	    }
	    
	    public VBox resultat(char type) {
	    		jeu = false;
		    	Text text;
	    		if (type == 'W') {
	    			text = new Text("GAGNÉ !!!");
	    		}else {
	    			text = new Text("PERDU !!!");
	    		}
    		
	    		root.getChildren().clear();
	    		titleBox.getChildren().clear();
	    		
	    		VBox res = new VBox();
//	    		Button restart = new Button("RECOMMENCER");
//	    		restart.setOnAction(e -> cree_menu());
	    		Button quitter = new Button("QUITTER");
	    		quitter.setOnAction(e -> quitter());
	    		
	    		Font f = Font.font("Arial",FontWeight.BOLD,25);
	    		Font fbtn = Font.font("Arial",FontWeight.BOLD,15);
//	    		restart.setFont(fbtn);
	    		quitter.setFont(fbtn);
	    		text.setFont(f);
	    		text.setFill(Color.BLUE);
	    		
	    		res.setStyle("-fx-spacing: 20");
	    		res.setAlignment(Pos.CENTER);
	    		
	    		titleBox.setStyle("-fx-padding: 25");
	    		titleBox.setAlignment(Pos.CENTER);
	    		
	    		titleBox.getChildren().addAll(text);
	    		res.getChildren().addAll(titleBox,quitter);
	    		
	    		return res;
	    }
}
