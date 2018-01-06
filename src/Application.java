
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ABR_Reverse a;
		a = new ABR_Reverse(10);
		
		a.Ajouter(15);
		a.Ajouter(4);
		a.Ajouter(5);
		a.Ajouter(30);
		a.Ajouter(25);
	
		a.Supprimer(4);
		
		
		Noeud_AABRR n5 = new Noeud_AABRR(11,14);
		
		AABRR b = new AABRR(2,6);
		b.Ajouter(5);
		b.Ajouter(3);
		b.AjouterNoeud(n5);	
		b.Ajouter(13);		
		
		b.Supprimer(14);
		System.out.println("true :" + b.is_NoeudJoint(n5));
		System.out.println(b.Affichage());


	}

}
