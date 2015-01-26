package garndesh.oculus;

public class OculusTestImpl extends OculusTest {

	private CubeRenderer cube;
	
	
	@Override
	protected void renderScene() {
		if(cube == null)
			cube = new CubeRenderer();
		cube.RenderCube();
	}

}
