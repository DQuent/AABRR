

public class Noeud_AABRR {

	Integer min; //inclu
	Integer max; //exclu
	ABR_Reverse abrr; 
	
	//Constructeur 0
	public Noeud_AABRR(){
		this.min = null;
		this.max = null;
		this.abrr = new ABR_Reverse();
	}
	
	//Constructeur 1
	public Noeud_AABRR(int m, int M){
		if(m < M){
			this.min = new Integer(m);
			this.max = new Integer(M);
			this.abrr = new ABR_Reverse();
		}
		else{
			System.out.println("La valeur minimum de l'interval doit être plus petite que la valeur maximum !");
		}
	}
	
	//Permet d'ajouter val à l'ABRR du Noeud 
	public void Ajouter(int val){
		this.abrr.Ajouter(val);
	}
	
	//Permet Supprimer val à l'ABRR du Noeud , renvoie true si la valeur à été supprimée
	public boolean Supprimer(int val){
		return this.abrr.Supprimer(val);
	}
	
	//Retourne l'affichage préfixe d'un Noeud
	public String AfficherNoeud(){
		String s = String.valueOf(this.getMin())+':'+String.valueOf(this.getMax())+';'+this.abrr.AffichagePrefixe();
		if(!this.abrr.AffichagePrefixe().isEmpty()){
			s=s.substring(0, s.length() - 1);
		}
		return s;
	}
	
	//Retourne vrai si la valeur est présente dans l'ABRR du Noeud
	public boolean Search(int val) {
		return this.abrr.Search(val);
	}
	
	//Retourne vrai si l'ABRR de l'arbre est vide
	public boolean is_ABRR_vide() {
		return this.abrr.isIs_vide();
	}
	
	//Retourne vrai si toute les valeurs de l'ABRR sont comprises entre min et Max
	public boolean is_ABRR_val_in_Interval(){
		String s = this.abrr.AffichageInfixe();
		String[] s_tab = s.split(":");

		for(int i=0;i<s_tab.length;i++){
			if(!(Integer.parseInt(s_tab[i]) <= this.getMax() && Integer.parseInt(s_tab[i]) >= this.getMin())){
				return false;
			}
		}
		
		return true;
	}
	
	//Retourne vrai si l'ABRR du Noeud est correct, c'est à dire que celui-ci comporte un ABRR bien formé et que ses valeurs sont comprises entre min et Max
	public boolean is_ABRR_correct() {
		return this.abrr.is_ABRR_correct() && this.is_ABRR_val_in_Interval();
	}

	//Getter and Setter 
		public int getMin() {
			return min.intValue();
		}


		public void setMin(int min) {
			this.min = new Integer(min);
		}


		public int getMax() {
			return max.intValue();
		}


		public void setMax(int max) {
			this.max = new Integer(max);
		}
	
}
