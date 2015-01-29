package garndesh.oculus;

import org.saintandreas.resources.Resource;

public enum Resources implements Resource {

	TEXTURES_TEST_TEXTURE("garndesh/oculus/textures/test.png"),
	TEXTURES_SKYBOX("garndesh/oculus/textures/skybox.png"),
	SHADERS_TEST_SHADER_FRAGMENT("garndesh/oculus/fragment01.frag"),
	SHADERS_TEST_SHADER_VERTEX("garndesh/oculus/vertex01.vert"),
	
	
	MODEL_SKYBOX("src/main/resources/garndesh/oculus/models/skybox.obj"),
	
	NO_RESOURCE("");

	public final String path;

	Resources(String path) {
		this.path = path;
	}

	@Override
	public String getPath() {
		return path;
	}

}
