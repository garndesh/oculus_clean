package garndesh.oculus.util;

import org.saintandreas.math.Matrix4f;
import org.saintandreas.math.Quaternion;
import org.saintandreas.math.Vector2f;
import org.saintandreas.math.Vector3f;

import com.oculusvr.capi.OvrMatrix4f;
import com.oculusvr.capi.OvrQuaternionf;
import com.oculusvr.capi.OvrVector3f;
import com.oculusvr.capi.Posef;

public class RiftUtils {

	  public static Vector3f toVector3f(OvrVector3f v) {
	    return new Vector3f(v.x, v.y, v.z);
	  }

	  public static Quaternion toQuaternion(OvrQuaternionf q) {
	    return new Quaternion(q.x, q.y, q.z, q.w);
	  }

	  public static Matrix4f toMatrix4f(Posef p) {
	    return new Matrix4f().rotate(toQuaternion(p.Orientation)).mult(new Matrix4f().translate(toVector3f(p.Position)));
	  }

	  public static Matrix4f toMatrix4f(OvrMatrix4f m) {
	    return new org.saintandreas.math.Matrix4f(m.M).transpose();
	  }
	  
	  public static Matrix4f toMatrix4f(org.lwjgl.util.vector.Matrix4f m){
		  return new Matrix4f(	m.m00, m.m01, m.m02, m.m03, 
				  				m.m10, m.m11, m.m12, m.m13, 
				  				m.m20, m.m21, m.m22, m.m23, 
				  				m.m30, m.m31, m.m32, m.m33);
	  }

	public static Vector3f toVector3f(org.lwjgl.util.vector.Vector3f v) {
		return new Vector3f(v.x, v.y, v.z);
	}

	public static Quaternion toQuaternion(
			org.lwjgl.util.vector.Quaternion q) {
		return new Quaternion(q.x, q.y, q.z, q.w);
	}
}
