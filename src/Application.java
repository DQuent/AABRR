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
	
		
		
		Noeud_AABRR n5 = new Noeud_AABRR(11,16);
		Noeud_AABRR n3 = new Noeud_AABRR(1,5);
		Noeud_AABRR n4 = new Noeud_AABRR(20,30);
		
		
		AABRR b = new AABRR(2,6);
		//b.AjouterNoeud(n5);	
		n5.Ajouter(11);
		n5.Ajouter(16);
		n5.Ajouter(15);
		n5.Ajouter(13);
		//n5.Ajouter(2);
	/*	b.AjouterNoeud(n3);	
		b.AjouterNoeud(n4);	
		b.Ajouter(15);
		b.Ajouter(4);
		b.Ajouter(5);
		b.Ajouter(30);
		b.Ajouter(25);*/
		
		//System.out.println(a.AffichageInfixe());
		
		//System.out.println("true :" + b.is_NoeudJoint(n5));
		//System.out.println(b.Affichage());
		b = b.CreateAABRRfromFile("test", "/home/delanou/workspace/AABRR/AABRR/Folder_output/");
		System.out.println("false :" +String.valueOf(b.Is_AABRR_correct()));
		System.out.println(b.Affichage());

	}

}
