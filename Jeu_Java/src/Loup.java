public class Loup extends Cadre {

	boolean survie;
	
	//constructeur
	public Loup(int pniveau, int pi_reel, int pj_reel) {
		super('L', pniveau,pi_reel,pj_reel);
		this.survie = true;
	}

}
