package garndesh.oculus.util;

import org.saintandreas.math.Vector3f;

public class HexPosUtil {

	public static final double UNIT_HIGHT = 2*Math.cos(Math.PI/6.0D);
	public static final double HALF_HIGHT = UNIT_HIGHT/2;

	public static Vector3f getVectorFromAxial(int r, int q){
		return new Vector3f((float)q*1.5F, 0,(float)( ((float)-r*UNIT_HIGHT)-((float)q*HALF_HIGHT)));
	}
	
	public static double getAngleFromRelativeCoords(int r, int q){
		Vector3f v = getVectorFromAxial(r, q);
		return Math.atan2(v.x, v.z);
	}
}
