import java.io.IOException;
import java.util.Scanner;


public class Application {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		
		while(choix != -1){
			
			if(choix == 0){
				System.out.println("\n\n\n");
				System.out.println("======================================\n=====Distanciel Delanou Legendre======\n======================================\n");
				System.out.println("Que voulez vous faire ?");
				System.out.println("1.");
				System.out.println("2.");
				System.out.println("3.");
				System.out.println("4.");
				System.out.println("5.");
				System.out.println("6.");
				System.out.println("7.");
				System.out.println("-1.Quitter le Programme");
				
				choix = sc.nextInt();
				
				switch(choix){
					case 1:
						System.out.println("Vous avez choisi 1");
						break;
					case 2:
						System.out.println("Vous avez choisi 2");
						break;
					case 3:
						System.out.println("Vous avez choisi 3");
						break;
					case 4:
						System.out.println("Vous avez choisi 4");
						break;
				}
			}
			else{
				System.out.println("Entrer un entier pour revenir au Menu principal.");
				choix = sc.nextInt();
				choix = 0;
			}
		}
			
		
		
	}

}
