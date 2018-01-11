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
		if(val>= this.noeud.getMin() && val<=this.noeud.getMax()){
			this.noeud.Ajouter(val); //ajoute au noeud
			return true;
		}
		else if(val<=this.noeud.getMin()){
			if(SaG != null){
				result = this.SaG.Ajouter(val); //ajoute a l'AABRR
			}
			else{
				return false;
			}		
		}
		else if(val>this.noeud.getMax()){
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
		if(interval.getMin() <= this.noeud.getMin() && interval.getMax() <= this.noeud.getMax() && interval.getMax() >= this.noeud.getMin()){
			result = true;
		}
		//cas 2 : interval qui dépasse a droite
		else if(interval.getMin() >= this.noeud.getMin() && interval.getMax() >= this.noeud.getMax() && interval.getMin() <= this.noeud.getMax()){
			result = true;
		}
		// cas 3 : interval qui est inclu dans celui de l'AABRR
		else if(interval.getMin() >= this.noeud.getMin() && interval.getMax() <= this.noeud.getMax()){
			result = true;
		}
		//cas 4 : interval qui recouvre celui de l'AABRR
		else if(interval.getMin() <= this.noeud.getMin() && interval.getMax() >= this.noeud.getMax()){
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
		//test si le noeud peut être ajouté
		if (!this.is_NoeudJoint(n)) {
			boolean result = false;
			if (n.getMin() > this.noeud.getMin()) {
				if (this.SaD == null) {
					this.SaD = new AABRR(n.getMin(), n.getMax());
					this.SaD.noeud.abr = n.abr;
					return true;
				} else {
					return this.SaD.AjouterNoeud(n);
				}
			} else if (n.getMin() < this.noeud.getMin()) {
				if (this.SaG == null) {
					this.SaG = new AABRR(n.getMin(), n.getMax());
					this.SaG.noeud.abr = n.abr;
					return true;
				} else {
					return this.SaG.AjouterNoeud(n);
				}
			}
			return result;
		}
		else{
			System.out.println("Le noeud ajouté n'est pas correct : il recouvre tout ou partie d'un des intervals de l'AABRR");
			return false;
		}
	}
	
	//ajoute un noeud à l'AABRR
		public boolean AjouterNoeudSansVerif(Noeud_AABRR n){
				boolean result = false;
				if (n.getMin() > this.noeud.getMin()) {
					if (this.SaD == null) {
						this.SaD = new AABRR(n.getMin(), n.getMax());
						this.SaD.noeud.abr = n.abr;
						return true;
					} else {
						return this.SaD.AjouterNoeud(n);
					}
				} else if (n.getMin() < this.noeud.getMin()) {
					if (this.SaG == null) {
						this.SaG = new AABRR(n.getMin(), n.getMax());
						this.SaG.noeud.abr = n.abr;
						return true;
					} else {
						return this.SaG.AjouterNoeud(n);
					}
				}
				return result;
		}
	
	public void Supprimer(int val){
		if(val >= this.noeud.getMin()){
			if(val <= this.noeud.getMax()){
				if(this.noeud.Supprimer(val)){
					System.out.println("Valeur supprimée.");
				}
				else{
					System.out.println("L'interval ["+this.noeud.getMin()+","+this.noeud.getMax()+"] ne contient pas la valeur à supprimer.");
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
		else if(val<this.noeud.getMin()){
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
	
	public boolean Is_AABRR_correct(){
		return this.is_ABR_sur_min() && !this.is_ToutNoeudJoint() && is_ToutNoeudIsABR();
	}
	
	//renvoie true si un des noeud recouvre tout ou partie d'un autre
	public boolean is_ToutNoeudJoint() {
		if(this.SaD !=null && this.SaG !=null){
			
			//cas 1 : interval qui dépasse a gauche
			if(this.SaG.noeud.getMin() <= this.noeud.getMin() && this.SaG.noeud.getMax() <= this.noeud.getMax() && this.SaG.noeud.getMax() >= this.noeud.getMin()){
				return true;
			}
			//cas 2 : interval qui dépasse a droite
			else if(this.SaD.noeud.getMin() >= this.noeud.getMin() && this.SaD.noeud.getMax() >= this.noeud.getMax() && this.SaD.noeud.getMin() <= this.noeud.getMax()){
				return true;
			}
			// cas 3 : interval qui est inclu dans celui de l'AABRR
			else if(this.SaD.noeud.getMin() >= this.noeud.getMin() && this.SaD.noeud.getMax() <= this.noeud.getMax()){
				return true;
			}
			//cas 4 : interval qui recouvre celui de l'AABRR
			else if(this.SaG.noeud.getMin() <= this.noeud.getMin() && this.SaG.noeud.getMax() >= this.noeud.getMax()){
				return true;
			}
			return this.SaD.is_ToutNoeudJoint() || this.SaG.is_ToutNoeudJoint();
		}
		else if(this.SaD ==null && this.SaG !=null){
			
			//cas 1 : interval qui dépasse a gauche
			if(this.SaG.noeud.getMin() <= this.noeud.getMin() && this.SaG.noeud.getMax() <= this.noeud.getMax() && this.SaG.noeud.getMax() >= this.noeud.getMin()){
				return true;
			}
			//cas 4 : interval qui recouvre celui de l'AABRR
			else if(this.SaG.noeud.getMin() <= this.noeud.getMin() && this.SaG.noeud.getMax() >= this.noeud.getMax()){
				return true;
			}
			return this.SaG.is_ToutNoeudJoint();
		}
		else if(this.SaG ==null &&  this.SaD !=null){
			//cas 2 : interval qui dépasse a droite
			if(this.SaD.noeud.getMin() >= this.noeud.getMin() && this.SaD.noeud.getMax() >= this.noeud.getMax() && this.SaD.noeud.getMin() <= this.noeud.getMax()){
				return true;
			}
			// cas 3 : interval qui est inclu dans celui de l'AABRR
			else if(this.SaD.noeud.getMin() >= this.noeud.getMin() && this.SaD.noeud.getMax() <= this.noeud.getMax()){
				return true;
			}
			return this.SaD.is_ToutNoeudJoint();
		}
		return false;		
	}
	
	public boolean is_ToutNoeudIsABR() {
		if(this.noeud.is_ABRR_vide()){
			return true;
		}
		else{
			if(this.SaD !=null && this.SaG !=null){
				return this.SaG.is_ToutNoeudIsABR() && this.SaD.is_ToutNoeudIsABR() && this.noeud.is_ABRR_correct();
			}
			else if(this.SaD ==null && this.SaG ==null){
				return this.noeud.abr.is_ABRR_correct();
			}
			else if(this.SaD ==null && this.SaG !=null){
				return this.SaG.is_ToutNoeudIsABR() && this.noeud.is_ABRR_correct();
			}
			else if(this.SaG ==null &&  this.SaD !=null){
				return this.SaD.is_ToutNoeudIsABR() && this.noeud.is_ABRR_correct();
			}
			else{
				return true;
			}
		}
		
	}
	
	
	
	
	public String AffichageInfixeSurMin(){
		String s =this.SousFonctionAffichageInfixeSurMin();
		if(s.length() > 1){
			return s.substring(0, s.length() - 1);
		}
		else{
			return s;
		}
	}
	
	public String SousFonctionAffichageInfixeSurMin(){
		String s ="";
		if(this.SaG!=null ){
			s += this.SaG.AffichageInfixeSurMin();
		}
		s += this.noeud.getMin()+":";
		if(this.SaD!=null){
			s += this.SaD.AffichageInfixeSurMin();
		}
		return s;
	}
	
	public boolean is_ABR_sur_min() {
		String s = this.AffichageInfixeSurMin();
		String[] s_tab = s.split(":");
		int[] i_tab = new int[s_tab.length];;
		
		for(int i=0;i<s_tab.length-1;i++){
			i_tab[i] = Integer.parseInt(s_tab[i]);
			if(i_tab[i]>i_tab[i+1]){
				return false;
			}
		}
		return true;
	}
	
	// Problème : créer l'arbre en Ajoutant successivement les valeurs des noeuds. 
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
				if(s_liste_noeud_abrr[i] != null){
					tmp_noeud.Ajouter(Integer.parseInt(s_liste_noeud_abrr[i]));
				}	
			}
			if(result.noeud.min == null && result.noeud.max == null){
				result.noeud = tmp_noeud;
			}
			result.AjouterNoeudSansVerif(tmp_noeud);
		}
		br.close();
		return result;
	}
	
	public boolean Search(int val){
		boolean result = false;
		if(val >= this.noeud.getMin()){
			if(val < this.noeud.getMax()){
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
		else if(val<this.noeud.getMin()){
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
	
	public ABR AABRRtoABR(ABR result){
		if(!this.noeud.is_ABRR_vide()){
			result.AjouterToutLesElements(this.noeud.abr);
		}
		if(this.SaD == null && this.SaG != null){
			this.SaG.AABRRtoABR(result);
		}
		if(this.SaD != null && this.SaG == null){
			this.SaD.AABRRtoABR(result);
		}
		if(this.SaD != null && this.SaG != null){
			this.SaG.AABRRtoABR(result);
			this.SaD.AABRRtoABR(result);
		}
		return result;
		
	}
	
	

}
