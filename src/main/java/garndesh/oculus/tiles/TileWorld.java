package garndesh.oculus.tiles;

import garndesh.oculus.WorldMap;
import garndesh.oculus.model.ModelBase;
import garndesh.oculus.util.HexPosition;
import garndesh.oculus.util.NamedList;

import java.util.ArrayList;
import java.util.List;

import org.saintandreas.gl.MatrixStack;

public abstract class TileWorld {
	
	protected boolean isSolid = true;
	
	public static NamedList tiles = new NamedList();
	//public static final TileFloorDirt tileFloorDirt = new TileFloorDirt();
	//public static final TileWallDirt tileWallDirt = new TileWallDirt();
	
	public abstract void renderTile(WorldMap map, HexPosition pos);
	
	public boolean isSolid(){
		return isSolid;
	}
	
	private static NamedList makeTileList(){
		NamedList tileList = new NamedList();
		tileList.addToList("tileFloorDirt", new TileFloorDirt());
		tileList.addToList("tileWallDirt", new TileWallDirt());
		
		
		return tileList;
	}

}
