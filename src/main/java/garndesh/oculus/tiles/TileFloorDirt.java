package garndesh.oculus.tiles;

import garndesh.oculus.HexPosition;
import garndesh.oculus.ModelBase;
import garndesh.oculus.Resources;
import garndesh.oculus.WorldMap;


public class TileFloorDirt extends TileWorld{

	private ModelBase floor;

	public TileFloorDirt() {
		this.floor = ModelBase.generateModelFromFile(Resources.MODEL_FLOOR, Resources.TEXTURES_FLOOR, "Floor");
		isSolid = false;
	}

	@Override
	public void renderTile(WorldMap map, HexPosition pos) {
		this.floor.renderModel();
	}

}
