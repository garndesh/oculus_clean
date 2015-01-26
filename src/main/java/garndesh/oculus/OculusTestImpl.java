package garndesh.oculus;

import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.saintandreas.gl.MatrixStack;
import org.saintandreas.math.Vector3f;

public class OculusTestImpl extends OculusTest {

	private CubeRenderer cube;
	
	
	@Override
	protected void renderScene() {
		glClear(GL_DEPTH_BUFFER_BIT);
		
		if(cube == null)
			cube = new CubeRenderer();
		
		MatrixStack mv = MatrixStack.MODELVIEW;
	    mv.push();
	    {
	      mv.translate(new Vector3f(0, 2, 0 )).scale(0.1F);
	      cube.RenderCube();
	    }
	    mv.pop();
		//
	}

}
