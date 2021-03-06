package garndesh.oculus;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import garndesh.oculus.model.ModelBase;
import garndesh.oculus.tiles.TileWorld;
import garndesh.oculus.util.HexPosition;
import garndesh.oculus.world.ChunkGenerator;
import garndesh.oculus.world.WorldMap;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.saintandreas.gl.MatrixStack;
import org.saintandreas.math.Matrix4f;
import org.saintandreas.math.Vector3f;

public class OculusTestImpl extends OculusTest {

	private static final String TAG = "testImpl";
	public static OculusTestImpl instance;
	private CubeRenderer cube;
	private ModelBase skybox;
	//private ModelBase jasper;
	private short skyboxRotation = 0;
	
	private WorldMap map;

	public OculusTestImpl() {
		super();
		instance = this;
	}
	
	@Override
	public void initGl(){
		super.initGl();
		map = new WorldMap();
		fillWorldMap();
		//jasper = ModelBase.generateModelFromFile(Resources.MODEL_JASPER, Resources.TEXTURES_JASPER, "JasperFinal");
	}
	
	private void fillWorldMap() {
		//map.addToMap(ChunkGenerator.generateChunk(0, 0));
		//map.addToMap(ChunkGenerator.generateChunk(1, 0));
		int raduis = 4;
		for(int q = -raduis; q <=raduis; q++){
			for(int r = -raduis; r <= raduis; r++){
				if(Math.abs(r+q)>raduis)
					continue;
				map.addToMap(ChunkGenerator.generateChunk(r, q));
			}
		}
		
	}

	@Override
	protected void renderScene() {
		glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
		skyboxRotation++;
		if (cube == null)
			cube = new CubeRenderer();
		if(skybox==null)
			skybox = ModelBase.generateModelFromFile(Resources.MODEL_SKYBOX, Resources.TEXTURES_SKYBOX, "Cube");

		//float rotation = (float)( (float)skyboxRotation/(float)Short.MAX_VALUE * 2 * Math.PI);
		//Log.d("ROT test", "rotation: "+rotation);
		MatrixStack mv = MatrixStack.MODELVIEW;
		mv.push();
		{
			mv.push();
			{
				//mv.set(new Matrix4f());
				mv.translate(new Vector3f(0, 20, 0));
				mv.scale(10F);
				//mv.rotate(rotation, Vector3f.UNIT_Y);
				skybox.renderModel();
			}
			mv.pop();
			map.renderWorld();
			
			mv.push();
			{
				mv.translate(new Vector3f(0, 1.65F, 0));
				mv.translate(new Vector3f(0, 0, -2));
				mv.scale(0.1F);
				for(int i = -5; i<=5 ; i++){
					mv.push();
					mv.translate(new Vector3f(2*i, 0, 0));
					//mv.rotate(rotation, Vector3f.UNIT_Y);
					cube.renderCube();
					//TileWorld.getTile(1).renderTile(0, i);
					mv.pop();
				}
			/*
			 * for (int x = -1; x < 3; x++) { mv.push(); mv.translate(new
			 * Vector3f( x%2*5, 0, (x-1)%2*5)); cube.RenderCube(); mv.pop(); }
			 */
			}
			mv.pop();
		}
		mv.pop();
		//
	}

	public static void end() {
		Mouse.setGrabbed(false);
		// instance.dispose();
		Display.destroy();
		System.exit(0);
	}
	
	public WorldMap getMap(){
		return map;
	}
}
