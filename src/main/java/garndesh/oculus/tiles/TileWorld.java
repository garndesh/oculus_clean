package garndesh.oculus.tiles;

import garndesh.oculus.HexPosition;
import garndesh.oculus.ModelBase;
import garndesh.oculus.WorldMap;

import java.util.ArrayList;
import java.util.List;

import org.saintandreas.gl.MatrixStack;

public abstract class TileWorld {
	
	protected boolean isSolid = true;
	
	
	public static final TileFloorDirt tileFloorDirt = new TileFloorDirt();
	public static final TileWallDirt tileWallDirt = new TileWallDirt();
	
	public abstract void renderTile(WorldMap map, HexPosition pos);
	
	public boolean isSolid(){
		return isSolid;
	}

}
