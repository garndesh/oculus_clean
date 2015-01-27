package garndesh.oculus;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.saintandreas.gl.MatrixStack;
import org.saintandreas.math.Vector3f;

public class OculusTestImpl extends OculusTest {

	private static OculusTestImpl instance;
	private CubeRenderer cube;

	public OculusTestImpl(){
		super();
		instance = this;
	}
	@Override
	protected void renderScene() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		if (cube == null)
			cube = new CubeRenderer();

		MatrixStack mv = MatrixStack.MODELVIEW;
		mv.push();
		{
			mv.translate(new Vector3f(0, 2, 0)).scale(0.1F);
			for (int x = -1; x < 3; x++) {
				mv.push();
				mv.translate(new Vector3f( x%2*5, 0, (x-1)%2*5));
				cube.RenderCube();
				mv.pop();
			}
		}
		mv.pop();
		//
	}
	
	
	public static void end() {
		Mouse.setGrabbed(false);
		//instance.dispose();
		Display.destroy();
		System.exit(0);
	}

}
