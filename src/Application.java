import java.io.IOException;


public class Application {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ABR a;
		a = new ABR(10);
		
		a.Ajouter(15);
		a.Ajouter(4);
		a.Ajouter(5);
		a.Ajouter(30);
		a.Ajouter(25);
	
		
		
		Noeud_AABRR n5 = new Noeud_AABRR(11,14);
		
		AABRR b = new AABRR(2,6);
		b.Ajouter(5);
		b.Ajouter(3);
		b.AjouterNoeud(n5);	
		b.Ajouter(13);		
		//System.out.println(a.AffichageInfixe());
		System.out.println(a.ABRtoAABRR(6).Affichage());
		
		//System.out.println("true :" + b.is_NoeudJoint(n5));
		//System.out.println(b.Affichage());
		
		//System.out.println(b.CreateAABRRfromFile("test", "/home/delanou/workspace/AABRR/AABRR/Folder_output").Affichage());

	}

}
