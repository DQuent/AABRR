import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class AABRR {

	/**
	 * @param args
	 */

	
	private Noeud_AABRR noeud;
	private AABRR SaG;
	private AABRR SaD;
	
	
	//Constructeur 0
	public AABRR() {
		this.noeud = new Noeud_AABRR();
		this.SaD =null;
		this.SaG = null;
	}
	
	//Constructeur 1
	public AABRR(int m,int M) {
		this.noeud = new Noeud_AABRR(m,M);
		this.SaD =null;
		this.SaG = null;
	}
	
	//Constructeur 2
	public AABRR(int m,int M, AABRR saG, AABRR saD) {
		this.noeud = new Noeud_AABRR(m,M);
		this.SaD =saD;
		this.SaG = saG;
	}
	
	//renvoie true si val a pu être ajouté, false sinon
	public boolean Ajouter(int val){
		boolean result = false;
		if(val>= this.noeud.min && val<this.noeud.max){
			this.noeud.Ajouter(val); //ajoute au noeud
			return true;
		}
		else if(val<=this.noeud.min){
			if(SaG != null){
				result = this.SaG.Ajouter(val); //ajoute a l'AABRR
			}
			else{
				return false;
			}		
		}
		else if(val>this.noeud.max){
			if(SaD != null){
				result = this.SaD.Ajouter(val);
			}
			else{
				return false;
			}	
		}
		return result;
	}
	
	//renvoie true si l'interval entrant est joint à un interval de l'AABRR
	public boolean is_NoeudJoint(Noeud_AABRR interval){
		boolean result = false;
		//cas 1 : interval qui dépasse a gauche
		if(interval.min <= this.noeud.min && interval.max <= this.noeud.max && interval.max >= this.noeud.min){
			result = true;
		}
		//cas 2 : interval qui dépasse a droite
		else if(interval.min >= this.noeud.min && interval.max >= this.noeud.max && interval.min <= this.noeud.max){
			result = true;
		}
		// cas 3 : interval qui est inclu dans celui de l'AABRR
		else if(interval.min >= this.noeud.min && interval.max <= this.noeud.max){
			result = true;
		}
		//cas 4 : interval qui recouvre celui de l'AABRR
		else if(interval.min <= this.noeud.min && interval.max >= this.noeud.max){
			result = true;
		}
		if(this.SaD !=null && this.SaG !=null){
			return result || this.SaD.is_NoeudJoint(interval) || this.SaG.is_NoeudJoint(interval);
		}
		else if(this.SaD ==null && this.SaG !=null){
			return result || this.SaG.is_NoeudJoint(interval);
		}
		else if(this.SaG ==null &&  this.SaD !=null){
			return result || this.SaD.is_NoeudJoint(interval);
		}
		else{
			return result;
		}
		
	}
	
	
	
	//ajoute un noeud à l'AABRR
	public boolean AjouterNoeud(Noeud_AABRR n){
		if (!this.is_NoeudJoint(n)) {
			boolean result = false;
			if (n.min > this.noeud.min) {
				if (this.SaD == null) {
					this.SaD = new AABRR(n.min, n.max);
					this.SaD.noeud.abr = n.abr;
					return true;
				} else {
					return this.SaD.AjouterNoeud(n);
				}
			} else if (n.min < this.noeud.min) {
				if (this.SaG == null) {
					this.SaG = new AABRR(n.min, n.max);
					this.SaG.noeud.abr = n.abr;
					return true;
				} else {
					return this.SaG.AjouterNoeud(n);
				}
			}
			return result;
		}
		else{
			return false;
		}
	}
	
	public void Supprimer(int val){
		if(val >= this.noeud.min){
			if(val <= this.noeud.max){
				if(this.noeud.Supprimer(val)){
					System.out.println("Valeur supprimée.");
				}
				else{
					System.out.println("L'interval ["+this.noeud.min+","+this.noeud.max+"] ne contient pas la valeur à supprimer.");
				}
			}
			else {
				if(this.SaD != null){
					this.SaD.Supprimer(val);
				}
				else{
					System.out.println("Aucun interval ne contient la valeur à supprimer");
				}
				
			}
		}
		else if(val<this.noeud.min){
			if(this.SaG != null){
				this.SaG.Supprimer(val);
			}
			else{
				System.out.println("Aucun interval de contient la valeur à supprimer");
			}	
		}
	}
	
	public void SaveAABRR(String name, String folder){
		try {
			  File file = new File(folder+"/"+name);
			    if (file.exists() && file.isDirectory() ) {
			         /* chemin est correct */
			    	BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			    	writer.write(this.Affichage());
			    	writer.close();
			    } else {
			    	BufferedWriter writer = new BufferedWriter(new FileWriter(new File( "/home/delanou/workspace/AABRR/AABRR/Folder_output"+"/"+name)));
			    	writer.write(this.Affichage());
			    	writer.close();
			    	
			    }	
			}
			catch (IOException e)
			{
			e.printStackTrace();
			}
	}
	
	public AABRR CreateAABRRfromFile(String name, String folder) throws IOException{
		BufferedReader br;
		AABRR result = new AABRR();
		
		br = new BufferedReader(new FileReader(new File(folder+'/'+name)));
		String line;
		String[] s_noeud;
		String[] s_min_max;
		String[] s_liste_noeud_abrr;
		while ((line = br.readLine()) != null) {
			
			s_noeud = line.split(";");
			s_min_max = s_noeud[0].split(":");
			Noeud_AABRR tmp_noeud = new Noeud_AABRR(Integer.parseInt(s_min_max[0]),Integer.parseInt(s_min_max[1]));
			
			s_liste_noeud_abrr = s_noeud[1].split(":");	
			for(int i=0;i<s_liste_noeud_abrr.length;i++){
				tmp_noeud.Ajouter(Integer.parseInt(s_liste_noeud_abrr[i]));
			}
			if(result.noeud.min == -1 && result.noeud.max == -1){
				result.noeud = tmp_noeud;
			}
			result.AjouterNoeud(tmp_noeud);
		}
		br.close();
		return result;
	}
	
	public boolean Search(int val){
		boolean result = false;
		if(val >= this.noeud.min){
			if(val < this.noeud.max){
				if(this.noeud.Search(val)){
					result = true;
				}
				else{
					result= false;
				}
			}
			else {
				if(this.SaD != null){
					result = this.SaD.Search(val);
				}
				else{
					System.out.println("Aucun interval ne contient la valeur");
				}
				
			}
		}
		else if(val<this.noeud.min){
			if(this.SaG != null){
				result= this.SaG.Search(val);
			}
			else{
				System.out.println("Aucun interval de contient la valeur");
			}	
		}
		return result;
	}
	
	
	public Noeud_AABRR getNoeud() {
		return noeud;
	}

	public void setNoeud(Noeud_AABRR noeud) {
		this.noeud = noeud;
	}

	public AABRR getSaG() {
		return SaG;
	}

	public void setSaG(AABRR saG) {
		SaG = saG;
	}

	public AABRR getSaD() {
		return SaD;
	}

	public void setSaD(AABRR saD) {
		SaD = saD;
	}

	//affichage prefixe
	public String Affichage(){
		String s ="";
		s += this.noeud.AfficherNoeud()+"\n";
		if(this.SaG!=null){
			s += this.SaG.Affichage();
		}
		if(this.SaD!=null){
			s += this.SaD.Affichage();
		}
		return s;
	}

}
