
public class Loup extends Danger {
	//Le voleur est un abstract de la classe méchant, il attaque le personange 
	//et uen capacité spéciale : a définir
	
	 //Constructeur par défaut
	public Loup() {
		vie=6;
		force=3;
		
	}
	public Loup(int vie, int force){
		    this.vie = vie;
		    this.force = force;
	}
	
	//Setters
   public int SetVie(int vie) {
       this.vie = vie;
       return vie;
   }
   
   public int SetForce(int force) {
       this.force = force;
       return force;
   }
   
   //Getters
   public int GetVie(int vie) {
       return vie;
   }
   
   public int GetForce(int force) {
       return force;
   }
	
	    
    //Méthodes & fonctions
    public boolean pouvoir() {
    	int random = (int) Math.floor((Math.random()*100));
    	boolean capacite =false;
    	if(random<= 25) {
    		capacite=true;
    	}
    	return capacite;
    }
    
	public int attaquer(int p_force) {
		if( p_force >= this.GetVie(vie) ) {
			return 0; 
		} else {
			return this.GetVie(vie);
		}
	
	}

}
