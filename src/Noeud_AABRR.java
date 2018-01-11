

public class Noeud_AABRR {

	Integer min; //inclu
	Integer max; //exclu
	ABR_Reverse abr; 
	
	public Noeud_AABRR(){
		this.min = null;
		this.max = null;
		this.abr = new ABR_Reverse();
	}
	
	public Noeud_AABRR(int m, int M){
		if(m < M){
			this.min = new Integer(m);
			this.max = new Integer(M);
			this.abr = new ABR_Reverse();
		}
		else{
			System.out.println("La valeur minimum de l'interval doit être plus petite que la valeur maximum !");
		}
	}
	

	public void Ajouter(int val){
		this.abr.Ajouter(val);
	}
	
	public boolean Supprimer(int val){
		return this.abr.Supprimer(val);
	}

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
	
	public String AfficherNoeud(){
		String s = String.valueOf(this.getMin())+':'+String.valueOf(this.getMax())+';'+this.abr.AffichagePrefixe();
		if(!this.abr.AffichagePrefixe().isEmpty()){
			s=s.substring(0, s.length() - 1);
		}
		return s;
	}
	
	public boolean is_ABRR(){
		boolean result = true;
		
		return result;
		
	}

	public boolean Search(int val) {
		return this.abr.Search(val);
	}

	public boolean is_ABRR_vide() {
		return this.abr.isIs_vide();
	}

	public boolean is_ABRR_val_in_Interval(){
		String s = this.abr.AffichageInfixe();
		String[] s_tab = s.split(":");

		for(int i=0;i<s_tab.length;i++){
			if(!(Integer.parseInt(s_tab[i]) <= this.getMax() && Integer.parseInt(s_tab[i]) >= this.getMin())){
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean is_ABRR_correct() {
		return this.abr.is_ABRR_correct() && this.is_ABRR_val_in_Interval();
	}

}
