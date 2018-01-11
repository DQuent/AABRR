
public class ABR {
	private Integer valeur;
	private ABR SaG;
	private ABR SaD;
	private boolean is_vide;
	
	
	//Constructeur 1
	public ABR(int valeur, ABR saG, ABR saD) {
		this.valeur = new Integer(valeur);
		SaG = saG;
		SaD = saD;
		this.is_vide = false;
	}
	
	//Constructeur 1
	public ABR(int valeur) {
		this.valeur = new Integer(valeur);
		SaG = new ABR();
		SaD = new ABR();
		this.is_vide = false;
	}
	
	
	//Constructeur 2
	public ABR() {
		this.valeur = null; 
		SaG = null;
		SaD = null;
		this.is_vide= true;
	}
	
	public void Ajouter(int val){
		if(this.is_vide){
			this.valeur = new Integer(val);
			this.is_vide = false;
			this.SaD = new ABR();
			this.SaG = new ABR();
		}
		else{
			if(val <= this.valeur.intValue() && this.SaG!=null){
				this.SaG.Ajouter(val);
			}
			else if(val > this.valeur.intValue() && this.SaD!=null){
				this.SaD.Ajouter(val);
			}
		}
	}
	
	public void Recopie(ABR abr){
		this.valeur = abr.valeur;
		this.is_vide = abr.is_vide;
		this.SaG = abr.SaG;
		this.SaD = abr.SaD;
	}
	
	//renvoie true si la suppression a été effectué, false sinon
	public boolean Supprimer(int val){
		boolean result = false;
		if(!is_vide){
			if(val < this.valeur.intValue()){
				result = this.SaG.Supprimer(val);
			}
			//si égalité
			else{
				if(val > this.valeur.intValue()){
					result = this.SaD.Supprimer(val);
				}
				//on a trouver le noeud a supprimer
				else if(this.SaG.is_vide){
					this.Recopie(this.SaD);
					result = true;
				}
				else if(this.SaD.is_vide){
					this.Recopie(this.SaG);
					result = true;
				}
				else{
					this.valeur = this.SaG.ExtraireMax();
					return true;
				}
			}
		}
			return result;
	}
	
	//extraire la valeur maximum du fils gauche
	public Integer ExtraireMax() {
		if(this.SaD.is_vide){
			Integer result= this.valeur;
			this.Recopie(this.SaG);
			return result;
		}
		else{
			return this.SaD.ExtraireMax();
		}
	}
	
	//extraire la valeur minimum du fils gauche d'un ABR
	public Integer getMinABR() {
		if(this.SaD.is_vide){
			Integer result= this.valeur;
			return result;
		}
		else{
			return this.SaD.getMinABR();
		}
	}
	
	//extraire la valeur minimum du fils gauche d'un ABR
	public Integer getMaxABR() {
		if(this.SaG.is_vide){
			Integer result= this.valeur;
			return result;
		}
		else{
			return this.SaG.getMaxABR();
		}
	}

	public boolean is_Feuille(){
		if(this.SaG ==  null && this.SaD == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public String sousFonctionAffichageInfixe(){
		String s ="";
		if(!this.is_vide ){
			if(this.SaG!=null && !this.SaG.is_vide){
				s += this.SaG.sousFonctionAffichageInfixe();
			}
			s += this.valeur+":";
			if(this.SaD!=null && !this.SaD.is_vide){
				s += this.SaD.sousFonctionAffichageInfixe();
			}
		}
		return s;
	}
	
	public String AffichageInfixe(){
		String s =this.sousFonctionAffichageInfixe();
		if(s.length() > 1){
			return s.substring(0, s.length() - 1);
		}
		else{
			return s;
		}
	}
	
	public String sousFonctionAffichagePrefixe(){
		String s ="";
		s += this.valeur+":";
		if(!this.is_vide ){
			if(this.SaG!=null && !this.SaG.is_vide){
				s += this.SaG.sousFonctionAffichagePrefixe();
			}
			if(this.SaD!=null && !this.SaD.is_vide){
				s += this.SaD.sousFonctionAffichagePrefixe();
			}
		}
		return s;
	}
	
	public String AffichagePrefixe(){
		String s = this.sousFonctionAffichagePrefixe();
		if(s.length() > 1){
			return s.substring(0, s.length() - 1);
		}
		else{
			return s;
		}
	}
	
	
	
	//Getter and Setter
	public Integer getValeur() {
		return valeur;
	}
	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}
	public ABR getSaG() {
		return SaG;
	}
	public void setSaG(ABR saG) {
		SaG = saG;
	}
	public ABR getSaD() {
		return SaD;
	}
	public void setSaD(ABR saD) {
		SaD = saD;
	}

	public boolean Search(int val) {
		boolean result = false;
		if(!is_vide){
			if(val < this.valeur.intValue()){
				result = this.SaG.Search(val);
			}
			//si égalité
			else{
				if(val > this.valeur.intValue()){
					result = this.SaD.Search(val);
				}
				//on a trouver le noeud a supprimer
				else{
					result = true;
				}
			}
		}
		return result;
	}	

	public AABRR ABRtoAABRR(int k){
		AABRR result = new AABRR();
		Integer min = this.getMaxABR();
		Integer max = this.getMinABR();
		
		Float f =  (float) Math.ceil((max.intValue()-min.intValue())/k);
		int taille_Interval= f.intValue() + 1;
		
		 
		System.out.println(String.valueOf(taille_Interval));
		int tmp_min = min.intValue();
		String s_tmp = this.AffichageInfixe();
		String[] tab_valeurs = s_tmp.substring(0, s_tmp.length() - 1).split(":");
		
		
		for(int i=0;i<k;i++){		
			Noeud_AABRR tmp_n = new Noeud_AABRR(tmp_min,tmp_min+taille_Interval); 
			//Nombre de parcours ABR = nb d'Interval
			for(int j=0;j<tab_valeurs.length;j++){	
				if(Integer.valueOf(tab_valeurs[j]) != null  && Integer.parseInt(tab_valeurs[j]) >= tmp_min && Integer.parseInt(tab_valeurs[j]) <= tmp_min+taille_Interval){
					tmp_n.Ajouter(Integer.parseInt(tab_valeurs[j]));
				}
			}	
			//
			if(result.getNoeud().min == null && result.getNoeud().max == null){
				result.setNoeud(tmp_n);
			}
			result.AjouterNoeud(tmp_n);
			tmp_min += taille_Interval+1;
		}
		
		return result;
		
	}

	public ABR AjouterToutLesElements(ABR_Reverse abr) {

		if(!abr.isIs_vide()){
			this.Ajouter(abr.getValeur());
			if(abr.getSaG().valeur != null){
				this.AjouterToutLesElements(abr.getSaG());
			}
			if(abr.getSaD().valeur != null){
				this.AjouterToutLesElements(abr.getSaD());
			}
		}	
		return this;
	}
	
}
