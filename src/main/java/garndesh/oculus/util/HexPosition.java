package garndesh.oculus.util;

import org.saintandreas.math.Vector3f;

public class HexPosition {
	
	private static final double UNIT_HIGHT = 2*Math.cos(Math.PI/6.0D);
	private static final double HALF_HIGHT = UNIT_HIGHT/2;
	private int q;
	private int r;
	private float scale = 1;
	
	public static final byte[][] NEIGHBORS = new byte[][]{ {1, 0}, {1, -1}, {0, 1}, {1, 0}, {-1, 1}, {0, -1}};
	
	public HexPosition(){
		this(0, 0, 0);
	}
	
	public HexPosition(int r, int q){
		this(q, r, 1F);
	}
	
	public HexPosition(int r, int q, float s){
		this.q = q;
		this.r = r;
		this.scale = s;
		
	}
	/*
	public HexPosition(int i, int j, int k){
		this.q = i;
		this.r = k;
	}
	
	public HexPosition(int i, int j, int k, float s){
		this(i, j, k);
		this.scale = s;
	}*/
	
	public void setScale(float scale){
		this.scale = scale;
		updateCartasian();
	}
	
	private void updateCartasian(){
		//this.cartesian = new Vector3f((float)((double)j*1.5F*scale), 0, (float)(((double)i+(double)k)*cos30*scale) );
	}
	
	
	public Vector3f toCartesian(){
		return new Vector3f((float)q*1.5F, 0,(float)( ((float)-q*HALF_HIGHT)-((float)r)*UNIT_HIGHT)).scale(scale);
		//return new Vector3f((float)( ((float)q*0.5)-((float)r)), 0,(float)q*1.5F).scale(scale);
	}

	public int getQ() {
		return q;
	}

	public void setQ(int q) {
		this.q = q;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
	
	@Override
	public String toString(){
		return "HexPosition: "+r+","+q+" "+scale;
	}
	
	@Override
	public boolean equals(Object obj){
		if((obj instanceof HexPosition)){
			//Log.d("HexPos", "equals?"+((HexPosition) obj).q+" == "+this.q+" && "+((HexPosition) obj).r+" == "+this.r );
			if(((HexPosition) obj).q == this.q && ((HexPosition) obj).r == this.r)
				return true;
		}	
		return false;
	}
	
	@Override
	 public int hashCode() { 
	    int hash = 1;
	    hash = hash * 31 + r;
	    hash = hash * 31  + q;
	    return hash;
	  }
	
}
