

public class Noeud_AABRR {

	int min; //inclu
	int max; //exclu
	ABR_Reverse abr; 
	
	public Noeud_AABRR(){
		this.min = -1;
		this.max = -1;
		this.abr = new ABR_Reverse();
	}
	
	public Noeud_AABRR(int m, int M){
		if(m < M){
			this.min = m;
			this.max = M;
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
		return min;
	}


	public void setMin(int min) {
		this.min = min;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
	}
	
	public String AfficherNoeud(){
		String s = String.valueOf(this.min)+':'+String.valueOf(this.max)+';'+this.abr.AffichagePrefixe();
		return s.substring(0, s.length() - 1);
	}
	
	public boolean is_ABRR(){
		boolean result = true;
		
		return result;
		
	}

	public boolean Search(int val) {
		return this.abr.Search(val);
	}

}
