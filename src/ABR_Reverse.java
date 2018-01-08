
public class ABR_Reverse {
	
	
	private int valeur;
	private ABR_Reverse SaG;
	private ABR_Reverse SaD;
	private boolean is_vide;
	
	
	//Constructeur 1
	public ABR_Reverse(int valeur, ABR_Reverse saG, ABR_Reverse saD) {
		this.valeur = valeur;
		SaG = saG;
		SaD = saD;
		this.is_vide = false;
	}
	
	//Constructeur 1
	public ABR_Reverse(int valeur) {
		this.valeur = valeur;
		SaG = new ABR_Reverse();
		SaD = new ABR_Reverse();
		this.is_vide = false;
	}
	
	
	//Constructeur 2
	public ABR_Reverse() {
		this.valeur = -1; 
		SaG = null;
		SaD = null;
		this.is_vide= true;
	}
	
	public void Ajouter(int val){
		if(this.is_vide){
			this.valeur = val;
			this.is_vide = false;
			this.SaD = new ABR_Reverse();
			this.SaG = new ABR_Reverse();
		}
		else{
			if(val <= this.valeur && this.SaD!=null){
				this.SaD.Ajouter(val);
			}
			else if(val > this.valeur && this.SaG!=null){
				this.SaG.Ajouter(val);
			}
		}
	}
	
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
			if(val < this.valeur){
				result = this.SaD.Supprimer(val);
			}
			//si égalité
			else{
				if(val > this.valeur){
					result = this.SaG.Supprimer(val);
				}
				//on a trouver le noeud a supprimer
				else if(this.SaD.is_vide){
					this.Recopie(this.SaG);
					result = true;
				}
				else if(this.SaG.is_vide){
					this.Recopie(this.SaD);
					result = true;
				}
				else{
					this.valeur = this.SaD.ExtraireMax();
					return true;
				}
			}
		}
			return result;
	}
	
	//extraire la valeur maximum du fils gauche
	private int ExtraireMax() {
		if(this.SaG.is_vide){
			int result= this.valeur;
			this.Recopie(this.SaD);
			return result;
		}
		else{
			return this.SaG.ExtraireMax();
		}
	}

	public boolean is_Feuille(){
		if(this.SaD ==  null && this.SaG == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public String AffichageInfixe(){
		String s ="";
		if(!this.is_vide ){
			if(this.SaG!=null && !this.SaG.is_vide){
				s += "\n";
				s += this.SaG.AffichageInfixe();
			}
			s += this.valeur+"|";
			if(this.SaG!=null && !this.SaD.is_vide){
				s += "\n";
				s += this.SaD.AffichageInfixe();
			}
		}
		return s;
	}
	
	public String AffichagePrefixe(){
		String s ="";
		s += this.valeur+":";
		if(!this.is_vide ){
			if(this.SaG!=null && !this.SaG.is_vide){
				s += this.SaG.AffichagePrefixe();
			}
			if(this.SaG!=null && !this.SaD.is_vide){
				s += this.SaD.AffichagePrefixe();
			}
		}
		return s;
	}
	
	
	
	//Getter and Setter
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
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

	public boolean Search(int val) {
		boolean result = false;
		if(!is_vide){
			if(val < this.valeur){
				result = this.SaD.Search(val);
			}
			//si égalité
			else{
				if(val > this.valeur){
					result = this.SaG.Search(val);
				}
				//on a trouver le noeud a supprimer
				else{
					result = true;
				}
			}
		}
		return result;
	}	




}
