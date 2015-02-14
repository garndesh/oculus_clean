package garndesh.oculus.world;

import garndesh.oculus.tiles.TileWorld;

import java.util.ArrayList;
import java.util.List;


public class WorldMap {
	//private HashMap<HexPosition, WorldChunk> world = new HashMap<>();
	private List<WorldChunk> loadedChunks = new ArrayList<WorldChunk>();
	
	
	public WorldMap(){
	}
	
	public void addToMap(WorldChunk chunk){
		loadedChunks.add(chunk);
	}
	
	public void removeFromMap(WorldChunk chunk){
		loadedChunks.remove(chunk);
	}
	
	public void renderWorld(){
		for(WorldChunk chunk : loadedChunks){
			//world.get(pos).renderTile(this, pos);
			chunk.render();
		}
	}
	
	/*public HashMap<HexPosition, TileWorld> getWorld(){
		return world;
	}*/
}
