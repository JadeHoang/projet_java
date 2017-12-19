import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Carte extends Plateau_de_jeu {
	//parent de type Plateau_de_jeu
	public Plateau_de_jeu parent;
	
	//constructeur 
	public Carte(Plateau_de_jeu plateau) {
		super(plateau.longueur,plateau.largeur);
		super.vider();
		parent = plateau;
	}
	
	//direction = 1: haut ; 2: bas ; 3: gauche 4: droite;
	public Cadre deplacer(int direction, int i_reel, int j_reel) {
		
		if(i_reel == 0 && direction ==1) { //Si personnage est en 0,0 alors il ne peut pas deplacer vers le haut
//			System.out.print("Erreur : vous ne pouvez vous déplacer vers le haut !");
			return null;
		} else if (i_reel == longueur-1 && direction == 2) { //Si personnage est en longueur-1,0 alors il ne peut pas deplacer vers le bas
//	        System.out.println("Erreur : vous ne pouvez vous déplacer vers le bas !");
			return null;
	    } else if(j_reel == 0 && direction == 3) { //Si personnage est en 0,0 alors il ne peut pas deplacer vers la gauche
//			System.out.print("Erreur : vous ne pouvez vous déplacer vers la gauche !");
			return null;
		} else if (j_reel == largeur-1 && direction == 4) { //Si personnage est en 0,largeur-1 alors il ne peut pas deplacer vers la droite
//	        System.out.println("Erreur : vous ne pouvez vous déplacer vers la droite !");
			return null;
	    }         
		Cadre type = null;
		
		//completer la carte 
		completer(i_reel,j_reel);
		
		//deplacer selon la direction
		switch(direction){
			case 1:
				type = parent.gettab(i_reel-1, j_reel);
				super.positionner(i_reel-1, j_reel, 'O');
				break;
			case 2:
				type = parent.gettab(i_reel+1, j_reel);
				super.positionner(i_reel+1, j_reel, 'O');
				break;
			case 3:
				type = parent.gettab(i_reel, j_reel-1);
				super.positionner(i_reel, j_reel-1, 'O');
				break;
			case 4:
				type = parent.gettab(i_reel, j_reel+1);
				super.positionner(i_reel, j_reel+1, 'O');
				break;
		}
		
		return type;
	}
	
	//Méthode compléter la carte avec les valeurs
	public void completer(int i_pos, int j_pos){
		
		if (parent.gettab(i_pos,j_pos).gettype() =='N'){//nourriture
			parent.positionner(i_pos, j_pos, 'X');
		}else if(parent.gettab(i_pos, j_pos).gettype()=='L' && ((Loup)(parent.gettab(i_pos, j_pos))).survie == false){//Loup
			parent.positionner(i_pos, j_pos, 'X');
		}else if(parent.gettab(i_pos,j_pos).gettype() =='V'){//voleur
			parent.positionner(i_pos, j_pos, 'X');
		}
		super.positionner(i_pos, j_pos, parent.gettab(i_pos, j_pos).gettype());
		
	}
	
	//Méthode afficher les parties connues de la carte
	public void afficher(GridPane grid) {
		System.out.println();
        	
		for(int i = 0; i < longueur; i++) {
			for(int j=0; j < largeur; j++) {
				if (plateau_de_jeu[i][j].type != '.'){
//					System.out.print(" | " + plateau_de_jeu[i][j].type);
					ImageView cadre = img_case(plateau_de_jeu[i][j].type);
					GridPane.setRowIndex(cadre, i);
					GridPane.setColumnIndex(cadre,j);
					grid.getChildren().add(cadre);
				}else{
//					System.out.print("    ");
					Rectangle rec = new Rectangle();
					rec.setFill(Color.BLACK);
					rec.setWidth(50);
					rec.setHeight(50);
					GridPane.setRowIndex(rec, i);
					GridPane.setColumnIndex(rec,j);
					grid.getChildren().add(rec);
				}
			}
		System.out.println(); //ligne suivante
		}
	System.out.println(); 
	}
	
	//charger les images
	public ImageView img_case(char nom) {
		ImageView case_carte = new ImageView();
		Image image = new Image(getClass().getResourceAsStream("/images/"+nom+".png"));
        
        	case_carte.setImage(image);
        	
        	return case_carte;
	}
}