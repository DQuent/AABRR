
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
		System.out.println(a.Affichage());


	}

}
