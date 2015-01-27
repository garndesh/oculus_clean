package garndesh.oculus;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.saintandreas.gl.BufferUtils;
import org.saintandreas.math.Matrix4f;

/**
 * ShaderProgram Class. Used to load and use Vertex and Fragment shaders easily.
 *
 * @author Sri Harsha Chilakapati
 */
public class ShaderProgram {
	// ProgramID
	int programID;
	// Vertex Shader ID
	int vertexShaderID;
	// Fragment Shader ID
	int fragmentShaderID;

	/**
	 * Create a new ShaderProgram.
	 */
	public ShaderProgram() {
		programID = glCreateProgram();
	}
	
	public void setUniform(String name, FloatBuffer matrix){
	    // Get the location
	    int location = glGetUniformLocation(programID, name);
	    // Set the uniform
	    glUniformMatrix4(location, false, matrix);
	}
	
	public void setUniform(String name, float[] values){
	    if (values.length > 4)
	    {
	        System.err.println("Uniforms cannot have more than 4 values");
	        OculusTestImpl.end();
	    }

	    // Get the location of the uniform
	    int location = glGetUniformLocation(programID, name);

	    // Set the uniform values
	    switch (values.length)
	    {
	        case 1:
	            glUniform1f(location, values[0]);
	            break;
	        case 2:
	            glUniform2f(location, values[0], values[1]);
	            break;
	        case 3:
	            glUniform3f(location, values[0], values[1], values[2]);
	            break;
	        case 4:
	            glUniform4f(location, values[0], values[1], values[2], values[3]);
	            break;
	    }
	}

	/**
	 * Attach a Vertex Shader to this program.
	 *
	 * @param name
	 *            The file name of the vertex shader.
	 */
	public void attachVertexShader(String name) {
		// Load the source
		String vertexShaderSource = FileUtil.readFromFile(name);
		// Create the shader and set the source
		vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexShaderID, vertexShaderSource);
		// Compile the shader
		glCompileShader(vertexShaderID);
		// Check for errors
		if (glGetShaderi(vertexShaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Unable to create vertex shader:");
			// Print log
			System.err.println(glGetShaderInfoLog(vertexShaderID,
					glGetShaderi(vertexShaderID, GL_INFO_LOG_LENGTH)));
			dispose();
			OculusTestImpl.end();
		}
		// Attach the shader
		glAttachShader(programID, vertexShaderID);
	}

	/**
	 * Attach a Fragment Shader to this program.
	 *
	 * @param name
	 *            The file name of the Fragment Shader.
	 */
	public void attachFragmentShader(String name) {
		// Read the source
		String fragmentShaderSource = FileUtil.readFromFile(name);
		// Create the shader and set the source
		fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentShaderID, fragmentShaderSource);
		// Compile the shader
		glCompileShader(fragmentShaderID);
		// Check for errors
		if (glGetShaderi(fragmentShaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Unable to create fragment shader:");
			// Print log
			System.err.println(glGetShaderInfoLog(fragmentShaderID,
					glGetShaderi(fragmentShaderID, GL_INFO_LOG_LENGTH)));
			dispose();
			OculusTestImpl.end();
		}
		// Attach the shader
		glAttachShader(programID, fragmentShaderID);
	}

	/**
	 * Links this program in order to use.
	 */
	public void link() {
		// Link this program
		glLinkProgram(programID);
		// Check for linking errors
		if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
			System.err.println("Unable to link shader program:");
			dispose();
			OculusTestImpl.end();
		}
	}

	/**
	 * Bind this program to use.
	 */
	public void bind() {
		glUseProgram(programID);
	}

	/**
	 * Unbind the shader program.
	 */
	public static void unbind() {
		glUseProgram(0);
	}

	/**
	 * Dispose the program and shaders.
	 */
	public void dispose() {
		// Unbind the program
		unbind();
		// Detach the shaders
		glDetachShader(programID, vertexShaderID);
		glDetachShader(programID, fragmentShaderID);
		// Delete the shaders
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);
		// Delete the program
		glDeleteProgram(programID);
	}

	/**
	 * @return The ID of this program.
	 */
	public int getID() {
		return programID;
	}

	public void attachVertexShader(Resources ShaderVertex) {
		this.attachVertexShader(ShaderVertex.path);
		
	}

	public void attachFragmentShader(Resources ShaderFragment) {
		this.attachFragmentShader(ShaderFragment.path);
		
	}

	public void setUniform(String name, Matrix4f top) {
		FloatBuffer fb = BufferUtils.getFloatBuffer(16);
	    top.fillFloatBuffer(fb, true);
	    fb.rewind();
		setUniform(name, fb);
	}
}