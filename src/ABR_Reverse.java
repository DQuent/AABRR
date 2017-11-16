
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
		if(SaG == null && SaD == null){
			this.is_vide= true;
		}
		else{
			this.is_vide = false;
		}
		
	}
	
	//Constructeur 2
	public ABR_Reverse(int valeur) {
		this.valeur = valeur;
		SaG = null;
		SaD = null;
		this.is_vide= true;
	}
	
	public void Ajouter(int val){
		if(is_vide){
			this.valeur = val;
			this.is_vide = false;
		}
		else{
			if(val <= this.valeur){
				this.SaD.Ajouter(val);
			}
			else if(val > this.valeur){
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
	
	public void Supprimer(int val){
		int y;
		if(!is_vide){
			if(val < this.valeur){
				this.SaD.Supprimer(val);
			}
			//si égalité
			else{
				if(val > this.valeur){
					this.SaG.Supprimer(val);
				}
				//on a trouver le noued a supprimer
				else if(this.SaD.is_vide){
					this.Recopie(this.SaG);
				}
				else if(this.SaG.is_vide){
					this.Recopie(this.SaD);
				}
				else{
					this.valeur = this.SaD.ExtraireMax();
				}
			}
		}
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
	
	
	public void Affichage(){
		if(!this.is_vide && this.SaG != null && this.SaD != null){
			this.SaG.Affichage();
			System.out.println(" "+this.valeur);
			this.SaD.Affichage();
		}
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




}
