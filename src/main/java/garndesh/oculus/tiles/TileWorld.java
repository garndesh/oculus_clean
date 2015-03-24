package garndesh.oculus.tiles;

import org.saintandreas.gl.MatrixStack;

import garndesh.oculus.util.HexPosition;
import garndesh.oculus.util.NamedList;

public abstract class TileWorld {
	
	protected boolean isSolid = true;
	protected static MatrixStack mv = MatrixStack.MODELVIEW;
	
	public static NamedList<TileWorld> tiles = makeTileList();
	//public static final TileFloorDirt tileFloorDirt = new TileFloorDirt();
	//public static final TileWallDirt tileWallDirt = new TileWallDirt();
	
	public abstract void renderTile(int r, int q);
	
	public boolean isSolid(){
		return isSolid;
	}
	
	private static NamedList<TileWorld> makeTileList(){
		NamedList<TileWorld> tileList = new NamedList<TileWorld>();
		tileList.addToList("tileNull", new TileNull());
		tileList.addToList("tileFloorDirt", new TileFloorDirt());
		tileList.addToList("tileWallDirt", new TileWallDirt());
		
		return tileList;
	}
	
	public static TileWorld getTile(int id){
		return tiles.getObject(id);
	}
	
	public static TileWorld getTile(String tileName){
		return tiles.getObject(tiles.getIndexOf(tileName));
	}
	

}
