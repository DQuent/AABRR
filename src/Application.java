import java.io.IOException;
import java.util.Scanner;


public class Application {

	
	
	private static boolean check_input(String val) {
		String pattern = "([0-9])*";
		if(val.length() == 0){
			return false;
		}
		else{
			return java.util.regex.Pattern.compile(pattern).matcher(val).matches();
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String choix = "0";
		Scanner sc = new Scanner(System.in);
		AABRR aabrr = new AABRR();
		ABR abr = new ABR();
		boolean quit = false;
		
		System.out.println("======================================\n=====Distanciel Delanou Legendre======\n======================================\n");
		System.out.println("Bienvenue sur l'application \"DELENDRE\". Cette Application permet la gestion des AABRRs. DELENDRE fonctionne sous forme de" +
				"\nmenu textuel dans la console. Pour chaque opération, entrer le numéro de celle-ci dans la console. Sachez que l'AABRR et l'ABR en cours" +
				"\nne serons pas gardés en mémoire à la fermeture de l'application. N'oubliez donc pas d'enregistrer votre AABRR final dans un fichier.");
		
		while(quit==false){
			
			if(choix == "0"){
				System.out.println("\n\n\n");
				System.out.println("======================================\n=====Distanciel Delanou Legendre======\n======================================\n");
				System.out.println("Que voulez vous faire ?");
				System.out.println("1. Réinitialiser l'AABRR en cours");
				System.out.println("2. Enregistrer son AABRR à partir d'un fichier");
				System.out.println("3. Enregistrer l'AABRR en cours dans un fichier");
				System.out.println("4. Afficher votre AABRR en cours");
				System.out.println("5. Vérifier votre AABRR en cours");
				System.out.println("6. Faire une recherche sur l'AABRR");
				System.out.println("7. Supprimer une valeur dans l'AABRR");
				System.out.println("8. Insérer une valeur dans l'AABRR");
				System.out.println("9. Insérer une valeur dans l'ABR");
				System.out.println("10. Supprimer une valeur dans l'ABR");
				System.out.println("11. Transformer votre ABR en AABRR");
				System.out.println("12. Transformer votre AABRR en ABR");
				System.out.println("13. Afficher votre ABR");
				System.out.println("-1.Quitter le Programme");
				
				choix = sc.nextLine();
				
				switch(choix){
					case "1":
						System.out.println("Vous avez choisi option 1");
						aabrr = new AABRR();
						break;
					case "2":
						System.out.println("Vous avez choisi 2");
						System.out.println("Quel est le nom du fichier ?");
						String name  = sc.nextLine();
						System.out.println("Quel est le chemin du fichier (sans le nom) ?");
						String folder = sc.nextLine();
						aabrr = aabrr.CreateAABRRfromFile(name, folder);
						System.out.println(aabrr.Affichage());
						break;
					case "3":
						if(aabrr.getNoeud().max == null || aabrr.getNoeud().min == null){
							System.out.println("Votre AABRR ne contient aucun Noeud, peut être avez vous oubliez de charger l'AABRR à partir d'un fichier ?");
						}
						else{
							System.out.println("Vous avez choisi 3");
							System.out.println("Quel est le nom du fichier ?");
							String name2  = sc.nextLine();
							System.out.println("Quel est le chemin du fichier (sans le nom) ?");
							String folder2 = sc.nextLine();
							aabrr.SaveAABRR(name2, folder2);
						}
						
						break;
					case "4":
						System.out.println("Vous avez choisi 4");
						if(aabrr.getNoeud().max == null || aabrr.getNoeud().min == null){
							System.out.println("Votre AABRR ne contient aucun Noeud, peut être avez vous oubliez de charger l'AABRR à partir d'un fichier ?");
						}
						else{
							System.out.println("Voici votre AABRR :");
							System.out.println(aabrr.Affichage());
						}
						break;
					case "5":
						System.out.println("Vous avez choisi 5");
						if(aabrr.getNoeud().max == null || aabrr.getNoeud().min == null){
							System.out.println("Votre AABRR ne contient aucun Noeud, peut être avez vous oubliez de charger l'AABRR à partir d'un fichier ?");
						}
						else{
							if(aabrr.Is_AABRR_correct()){
								System.out.println("Votre AABRR est correct");
							}
							else{
								System.out.println("Votre AABRR n'est pas correct");
							}
						}
						break;
					case "6":
						if(aabrr.getNoeud().max == null || aabrr.getNoeud().min == null){
							System.out.println("Votre AABRR ne contient aucun Noeud, peut être avez vous oubliez de charger l'AABRR à partir d'un fichier ?");
						}
						else{
							System.out.println("Vous avez choisi 6");
							System.out.println("Quelle valeur voulez-vous rechercher ?");
							String s_val_recherche  = sc.nextLine();
							
							
							if(Application.check_input(s_val_recherche)){
								int val_recherche = Integer.parseInt(s_val_recherche);
								if(aabrr.Search(val_recherche)){
									System.out.println("La valeur "+String.valueOf(val_recherche) +" est dans l'AABRR");
								}
								else{
									System.out.println("La valeur "+String.valueOf(val_recherche) +" n'est pas dans l'AABRR");
								}
							}
							else{
								System.out.println("La valeur "+String.valueOf(s_val_recherche) +" n'est pas correcte");
							}
						}
						break;
					case "7":		
						if(aabrr.getNoeud().max == null || aabrr.getNoeud().min == null){
							System.out.println("Votre AABRR ne contient aucun Noeud, peut être avez vous oubliez de charger l'AABRR à partir d'un fichier ?");
						}
						else{
							System.out.println("Vous avez choisi 7");
							System.out.println("Quelle valeur voulez-vous supprimer ?");
							String s_val_supp  = sc.nextLine();
							
							if(Application.check_input(s_val_supp)){
								int val_supp = Integer.parseInt(s_val_supp);
								if(aabrr.Supprimer(val_supp)){
									System.out.println("La valeur "+String.valueOf(val_supp) +" est dans l'AABRR et à été supprimée");
								}
								else{
									System.out.println("La valeur "+String.valueOf(val_supp) +" n'est pas dans l'AABRR, aucune suppression n'as été effectuée");
								}
							}
							else{
								System.out.println("La valeur "+s_val_supp +" n'est pas correcte");
							}
						}
						break;
					case "8":				
						if(aabrr.getNoeud().max == null || aabrr.getNoeud().min == null){
							System.out.println("Votre AABRR ne contient aucun Noeud, peut être avez vous oubliez de charger l'AABRR à partir d'un fichier ?");
						}
						else{
							System.out.println("Vous avez choisi 8");
							System.out.println("Quelle valeur voulez-vous insérer ?");
							String s_val_ins  = sc.nextLine();
							
							if(Application.check_input(s_val_ins)){
								int val_ins  = Integer.parseInt(s_val_ins);
								if(aabrr.Ajouter(val_ins)){
									System.out.println("La valeur "+String.valueOf(val_ins) +" a été ajoutée à l'AABRR");
								}
								else{
									System.out.println("La valeur "+String.valueOf(val_ins) +" n'a pas été ajoutée");
								}
							}
							else{
								System.out.println("La valeur "+s_val_ins+" n'est pas valide");

							}
							
							
						}
						break;
					case "9":
						System.out.println("Vous avez choisi 9");
						System.out.println("Quelle valeur voulez-vous insérer dans l'ABR?");
						String s_val_ins2  = sc.nextLine();
						
						if(Application.check_input(s_val_ins2)){
							int val_ins2  = Integer.parseInt(s_val_ins2);
							abr.Ajouter(val_ins2);
							System.out.println("La valeur "+String.valueOf(val_ins2) +" a été ajoutée à l'ABR");	
						}
						else{
							System.out.println("La valeur "+s_val_ins2 +" n'est pas une entrée valide");	

						}
						break;
					case "10":
						
						if(abr.is_Feuille()){
							System.out.println("Vous ne pouvez Supprimer dans un ABR vide !");
						}
						else{
							System.out.println("Vous avez choisi 10");
							System.out.println("Quelle valeur voulez-vous supprimer dans l'ABR ?");
							String s_val_supp2  = sc.nextLine();
							
							if(Application.check_input(s_val_supp2)){
								int val_supp2  = Integer.parseInt(s_val_supp2);
								abr.Supprimer(val_supp2);
								System.out.println("La valeur "+String.valueOf(val_supp2) +" a été supprimée à l'ABR");
							}
							else{
								System.out.println("La valeur "+String.valueOf(s_val_supp2) +" n'est pas correcte");

							}
							
						}
						break;
					case "11":
						
						if(abr.is_Feuille()){
							System.out.println("Votre ABR est vide !");
						}
						else{
							System.out.println("Vous avez choisi 11");
							System.out.println("En combien d'interval voulez-vous que l'ABR soit fractionné ?");
							String s_k = sc.nextLine();
							
							if(Application.check_input(s_k)){
								int k = Integer.parseInt(s_k);
								ABR tmp_abr = abr;
								AABRR tmp_aabrr = tmp_abr.ABRtoAABRR(k);
								System.out.println("Voici l'AABRR obtenu (l'AABRR en cours n'as pas été modifié) :");
								System.out.println(tmp_aabrr.Affichage());
								tmp_abr = new ABR();
								tmp_aabrr = new AABRR();
							}
							else{
								System.out.println("La valeur "+String.valueOf(s_k) +" n'est pas correcte");
							}
							
						}
						break;
					case "12":
						
						if(aabrr.getNoeud().is_ABRR_vide()){
							System.out.println("Votre AABRR est vide !");
						}
						else{
							System.out.println("Vous avez choisi 12");
							ABR tmp_abr2 = new ABR();
							AABRR tmp_aabrr2 = new AABRR();
							tmp_aabrr2 = aabrr;
							tmp_abr2 = tmp_aabrr2.AABRRtoABR(tmp_abr2);
							
							System.out.println("Voici l'ABR obtenu (l'ABR en cours n'as pas été modifié) :");
							System.out.println("Affichage Prefixe : "+tmp_abr2.AffichagePrefixe());
							System.out.println("Affichage Infixe : "+tmp_abr2.AffichageInfixe());
						}
						break;
						case "13":
						
						if(abr.is_Feuille()){
							System.out.println("Votre ABR est vide !");
						}
						else{
							System.out.println("Affichage Préfixe : "+abr.AffichagePrefixe());
							System.out.println("Affichage Infixe : "+abr.AffichageInfixe());
						}
						break;
					case "-1":
						quit = true;
						break;
					default :
						System.out.println("Ceci n'est pas une option valide.");
						break;
				}
			}
			else{
				System.out.println("Action realisée, entrer n'importe quel caractère pour revenir au Menu Principal");
				choix = sc.nextLine();
				choix ="0";
			}
		}
			
		
		
	}

}
