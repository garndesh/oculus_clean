package garndesh.oculus.tiles;

import org.saintandreas.gl.MatrixStack;
import org.saintandreas.math.Vector3f;

import garndesh.oculus.OculusTestImpl;
import garndesh.oculus.Resources;
import garndesh.oculus.model.ModelBase;
import garndesh.oculus.util.HexPosUtil;
import garndesh.oculus.util.HexPosition;
import garndesh.oculus.util.Log;
import garndesh.oculus.util.StringRenderer;
import garndesh.oculus.world.WorldMap;

public class TileWallDirt extends TileWorld {

	protected ModelBase wall;
	protected ModelBase ceiling;
	

	public TileWallDirt() {
		this.ceiling = ModelBase.generateModelFromFile(Resources.MODEL_FLOOR,
				Resources.TEXTURES_FLOOR, "Floor");
		this.wall = ModelBase.generateModelFromFile(Resources.MODEL_WALL,
				Resources.TEXTURES_WALL, "Wall");
	}

	@Override
	public void renderTile(int r, int q) {
		mv.push();
		mv.translate(new Vector3f(0, 2.8F, 0));
		ceiling.renderModel();
		mv.pop();
		
		mv.push();
		mv.translate(new Vector3f(0.2F, 0, 0));
		//for (byte i = 0; i < 6; i++) {
		//int index = 0;
		for (byte[] i : HexPosition.NEIGHBORS) {
			 TileWorld neighbor = OculusTestImpl.instance.getMap().getTile(r+i[0], q+i[1]);
			 //OculusTestImpl.instance.getMap().getTile(r+HexPosition.NEIGHBORS[i][0],
			 //q+HexPosition.NEIGHBORS[i][1]);
			// Log.d("tmp", "isSolid: "+neighbor.isSolid());
			if(neighbor==null || !neighbor.isSolid()){
				mv.push();
				//mv.rotate((float) ((float) i * Math.PI / 3F	), Vector3f.UNIT_Y);
				mv.rotate((float) HexPosUtil.getAngleFromRelativeCoords(i[0], i[1]), Vector3f.UNIT_Y);
				mv.translate(new Vector3f(0, 0, (float) HexPosUtil.HALF_HIGHT-0.2F));
				wall.renderModel();
				mv.pop();
			}
		}
		mv.pop();
		/*
		 * for(byte q = -1; q <= 1; q++){ for(byte r = -1; r <=1; r++){ if(q==r)
		 * continue; if(map.getWorld().get(new HexPosition(pos.getQ()+q,
		 * pos.getR()+r)).isSolid()); } }
		 */
	}

}
