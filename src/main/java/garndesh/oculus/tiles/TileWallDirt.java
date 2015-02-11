package garndesh.oculus.tiles;

import org.saintandreas.gl.MatrixStack;
import org.saintandreas.math.Vector3f;

import garndesh.oculus.OculusTestImpl;
import garndesh.oculus.Resources;
import garndesh.oculus.WorldMap;
import garndesh.oculus.model.ModelBase;
import garndesh.oculus.util.HexPosition;
import garndesh.oculus.util.Log;

public class TileWallDirt extends TileWorld{

	protected ModelBase wall;
	protected ModelBase ceiling;
	
	public TileWallDirt() {
		this.ceiling = ModelBase.generateModelFromFile(Resources.MODEL_FLOOR, Resources.TEXTURES_FLOOR, "Floor");
		this.wall = ModelBase.generateModelFromFile(Resources.MODEL_WALL, Resources.TEXTURES_WALL, "Wall");
	}
	
	
	@Override
	public void renderTile(WorldMap map, HexPosition pos) {
		MatrixStack.MODELVIEW.push();
		MatrixStack.MODELVIEW.translate(new Vector3f(0, 2.8F, 0));
		ceiling.renderModel();
		MatrixStack.MODELVIEW.pop();
		for(byte r = 0; r < 6; r++){
			HexPosition testPos = new HexPosition(pos.getQ()+HexPosition.NEIGHBORS[r][0], pos.getR()+HexPosition.NEIGHBORS[r][1]);
			TileWorld neighbor = map.getWorld().get(testPos);
			
			//Log.d("tmp", "isSolid: "+neighbor.isSolid());
			if(neighbor!=null && !neighbor.isSolid()){
				MatrixStack.MODELVIEW.push();
				MatrixStack.MODELVIEW.rotate((float) ((float)r*Math.PI/3F), Vector3f.UNIT_Y);
				wall.renderModel();
				MatrixStack.MODELVIEW.pop();
			}
		}
		/*for(byte q = -1; q <= 1; q++){
			for(byte r = -1; r <=1; r++){
				if(q==r)
					continue;
				if(map.getWorld().get(new HexPosition(pos.getQ()+q, pos.getR()+r)).isSolid());
			}
		}*/
	}


}
