import java.lang.reflect.Array;
import java.util.ArrayList;


public class ABR_Reverse {
	
	
	Integer valeur;
	private ABR_Reverse SaG;
	private ABR_Reverse SaD;
	private boolean is_vide;
	
	
	//Constructeur 1
	public ABR_Reverse(int valeur, ABR_Reverse saG, ABR_Reverse saD) {
		this.valeur = new Integer(valeur);
		SaG = saG;
		SaD = saD;
		this.is_vide = false;
	}
	
	//Constructeur 1
	public ABR_Reverse(int valeur) {
		this.valeur = new Integer(valeur);
		SaG = new ABR_Reverse();
		SaD = new ABR_Reverse();
		this.is_vide = false;
	}
	
	
	//Constructeur 2
	public ABR_Reverse() {
		this.valeur = null; 
		SaG = null;
		SaD = null;
		this.is_vide= true;
	}
	
	//Permet d'ajouter val à l'ABRR
	public void Ajouter(int val){
		if(this.is_vide){
			this.valeur = new Integer(val);
			this.is_vide = false;
			this.SaD = new ABR_Reverse();
			this.SaG = new ABR_Reverse();
		}
		else{
			if(val <= this.getValeur() && this.SaD!=null){
				this.SaD.Ajouter(val);
			}
			else if(val > this.getValeur() && this.SaG!=null){
				this.SaG.Ajouter(val);
			}
		}
	}
	
	// Ajoute val à l'ABRR
	public void Recopie(ABR_Reverse abr){
		this.valeur = abr.valeur;
		this.is_vide = abr.is_vide;
		this.SaG = abr.SaG;
		this.SaD = abr.SaD;
	}
	
	//renvoie true si la suppression a été effectué, false sinon
	public boolean Supprimer(int val){
		boolean result = false;
		if(!is_vide){
			if(val < this.getValeur()){
				result = this.SaD.Supprimer(val);
			}
			//si égalité
			else{
				if(val > this.getValeur()){
					result = this.SaG.Supprimer(val);
				}
				//on a trouver le noeud a supprimer
				//un seul fils : SaG
				else if(this.SaD.is_vide){
					this.Recopie(this.SaG);
					result = true;
				}
				//un seul fils : SaG
				else if(this.SaG.is_vide){
					this.Recopie(this.SaD);
					result = true;
				}
				else{//si noeud posssède deux fils
					this.setValeur(this.SaD.ExtraireMax()); //extraire et supprime la valeur
					result = true;
				}
			}
		}
			return result;
	}
	
	//extraire la valeur maximum du fils gauche
	public int ExtraireMax() {
		if(this.SaG.is_vide){
			int result= this.getValeur();
			this.Recopie(this.SaD);
			return result;
		}
		else{
			return this.SaG.ExtraireMax();
		}
	}
	
	//extraire la valeur minimum du fils gauche d'un ABR
	public int getMin() {
		if(this.SaG.is_vide){
			int result= this.getValeur();
			return result;
		}
		else{
			return this.SaG.getMin();
		}
	}
	
	//extraire la valeur minimum du fils gauche d'un ABR
	public int getMax() {
		if(this.SaD.is_vide){
			int result= this.getValeur();
			return result;
		}
		else{
			return this.SaD.getMax();
		}
	}
	
	//Renvoie true si le noeud de l'arbre est une feuille
	public boolean is_Feuille(){
		if(this.SaD ==  null && this.SaG == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Permet l'affichage infixe+':'
	public String SousFonctionAffichageInfixe(){
		String s ="";
		if(!this.is_vide ){
			if(this.SaG!=null && !this.SaG.is_vide){
				s += this.SaG.SousFonctionAffichageInfixe();
			}
			s += this.valeur+":";
			if(this.SaG!=null && !this.SaD.is_vide){
				s += this.SaD.SousFonctionAffichageInfixe();
			}
		}
		return s;
	}
	
	//Encapsule SousFonctionAffichageInfixe et permet l'affichageInfixe de l'ABRR sans le ':' final
	public String AffichageInfixe(){
		String s =this.SousFonctionAffichageInfixe();
		if(s.length() > 1){
			return s.substring(0, s.length() - 1);
		}
		else{
			return s;
		}
	}
	
	//Permet l'Affichage Préfixe+':'
	public String AffichagePrefixe(){
		String s ="";
		if(this.valeur != null){
			s += this.valeur+":";			
		}
		if(!this.is_vide){
			if(this.SaG!=null && !this.SaG.is_vide){
				s += this.SaG.AffichagePrefixe();
			}
			if(this.SaG!=null && !this.SaD.is_vide){
				s += this.SaD.AffichagePrefixe();
			}
		}
		return s;
	}
	
	//Retourne vrai si la valeur est dans l'ABRR
	public boolean Search(int val) {
		boolean result = false;
		if(!is_vide){
			if(val < this.getValeur()){
				result = this.SaD.Search(val);
			}
			//si égalité
			else{
				if(val > this.getValeur()){
					result = this.SaG.Search(val);
				}
				else{
					result = true;
				}
			}
		}
		return result;
	}
	
	//Retourne true si ABRR correct
	public boolean is_ABRR_correct(){
		String s = this.AffichageInfixe();
		String[] s_tab = s.split(":");
		int[] i_tab = new int[s_tab.length];;
		
		for(int i=0;i<s_tab.length-1;i++){
			i_tab[i] = Integer.parseInt(s_tab[i]);
			if(i_tab[i]<i_tab[i+1]){
				return false;
			}
		}
		
		return true;
	}
	
	//Getter and Setter
	
		public boolean isIs_vide() {
			return is_vide;
		}

		public void setIs_vide(boolean is_vide) {
			this.is_vide = is_vide;
		}

		public void setValeur(Integer valeur) {
			this.valeur = valeur;
		}
		
		public int getValeur() {
			return valeur.intValue();
		}
		public void setValeur(int valeur) {
			this.valeur = new Integer(valeur);
		}
		public ABR_Reverse getSaG() {
			return SaG;
		}
		public void setSaG(ABR_Reverse saG) {
			SaG = saG;
		}
		public ABR_Reverse getSaD() {
			return SaD;
		}
		public void setSaD(ABR_Reverse saD) {
			SaD = saD;
		}

}