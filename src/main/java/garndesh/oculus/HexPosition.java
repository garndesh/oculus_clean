package garndesh.oculus;

public class HexPosition {
	
	public int i;
	public int j;
	public int k;
	public float scale = 1;
	
	public HexPosition(){
		i = 0; j = 0; k = 0;
	}
	
	public HexPosition(int i, int j, int k){
		this.i = i;
		this.j = j;
		this.k = k;
	}
	
	public HexPosition(int i, int j, int k, float s){
		this(i, j, k);
		this.scale = s;
	}
	
	public void setScale(float scale){
		this.scale = scale;
	}
	
}
