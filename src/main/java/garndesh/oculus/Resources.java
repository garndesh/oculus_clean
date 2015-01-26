package garndesh.oculus;

import org.saintandreas.resources.Resource;

public enum Resources implements Resource {

	TEXTURES_TEST_TEXTURE("garndesh/openglrenderer/textures/test.png"),
	SHADERS_TEST_SHADER_FRAGMENT("garndesh/openglrenderer/fragment01.frag"),
	SHADERS_TEST_SHADER_VERTEX("garndesh/openglrenderer/vertex01.vert"),
	
	
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
