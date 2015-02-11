package garndesh.oculus;

import garndesh.oculus.util.QuaternionUtil;
import garndesh.oculus.util.RiftUtils;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f();
	private Quaternion orientation = new Quaternion();
	private Matrix4f view = new Matrix4f(); 
	
	private static FloatBuffer transform = BufferUtils.createFloatBuffer(4*4);
	
	protected static final Vector3f AXIS_X = new Vector3f(0, 0, -1);
	protected static final Vector3f AXIS_Y = new Vector3f(0, 1, 0);
	protected static final Vector3f AXIS_Z = new Vector3f(1, 0, 0);
	private static final String TAG = "Camera";
	
	//axis relative to the camera
	protected Vector3f forward; 
	protected Vector3f right; 
	protected Vector3f up;
	
	public enum Direction {
		FORWARD,
		BACKWARD,
		LEFT,
		RIGHT,
		UP,
		DOWN
	}
	
	public Camera(){
		forward = AXIS_X;
		right = AXIS_Z;
		up = AXIS_Y;
	}
	
	public Vector3f getPosition(){
		return position;
	}
	
	public Quaternion getOrientation(){
		return orientation;
	}
		
	public void rotateY(float angle){
	    Quaternion yRot = QuaternionUtil.createFromAxisAngle(AXIS_Y, angle, null);
	    Quaternion.mul(yRot, orientation, orientation);
	    //orientation.setY(orientation.getY()+yRot.y);

	    QuaternionUtil.rotate(right, yRot, right);
	    QuaternionUtil.rotate(forward, yRot, forward);

	    right.normalise();
	    forward.normalise();
	}
	
	public void rotateZ(float angle){
	    Quaternion zRot = QuaternionUtil.createFromAxisAngle(right, angle, null);
	    Quaternion.mul(zRot, orientation, orientation);
	    //orientation.setZ(orientation.getZ()+zRot.z);

	    QuaternionUtil.rotate(up, zRot, up);
	    QuaternionUtil.rotate(forward, zRot, forward);

	    up.normalise();
	    forward.normalise();
	    //Log.d(TAG,  "up-rotation: "+up.x+ "/"+up.y+"/"+up.z);
	}
	
	public void rotateX(float angle){
	    
	}
	
	public void move(Vector3f dir, float amount){
	    // Create a copy of direction
	    Vector3f deltaMove = new Vector3f(dir);

	    // Normalise the direction and scale it by amount
	    deltaMove.normalise();
	    deltaMove.scale(amount);

	    // Add the delta to the camera's position
	    Vector3f.add(position, deltaMove, position);
	}
	
	// raw move function will change given vector
	private void moveRaw(Vector3f dir, float amount){
		dir.normalise();
		dir.scale(amount);
		Vector3f.add(position, dir, position);
	}
	
	public void move(Direction dir, float amount)
	{
	    switch (dir)
	    {
	        case FORWARD:  moveRaw(new Vector3f(forward.x, 0, forward.z), +amount); break;
	        case BACKWARD: moveRaw(new Vector3f(forward.x, 0, forward.z), -amount); break;
	        case LEFT:     moveRaw(new Vector3f(right.x, 0, right.z),   -amount); break;
	        case RIGHT:    moveRaw(new Vector3f(right.x, 0, right.z),   +amount); break;
	        case UP:       move(AXIS_Y,      +amount); break;
	        case DOWN:     move(AXIS_Y,      -amount); break;
	    }
	}
	
	public void update(){
		
		// Rotate the scene and translate the world back
	    QuaternionUtil.toRotationMatrix(orientation, view);
	    Matrix4f.translate(position.negate(null), view, view);

	    // Store the view matrix in the buffer
	    view.store(transform);
	    transform.rewind();
	}
	
	public Matrix4f getView(){
		return view;
	}
	
	public org.saintandreas.math.Matrix4f getViewSA(){
		//return new org.saintandreas.math.Matrix4f(transform);
		return RiftUtils.toMatrix4f(view);
	}
	
	public void setPosition(Vector3f position){
		this.position = position;
	}
	
	public void setOrientation(Quaternion orientation){
		this.orientation = orientation;
	}
	
	public static FloatBuffer getTransform(){
		return transform;
	}
	

}
