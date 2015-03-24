package garndesh.oculus.world;

import garndesh.oculus.tiles.TileWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WorldMap {
	private HashMap<Long, WorldChunk> world = new HashMap<Long, WorldChunk>();
	
	public WorldMap(){
	}
	
	public void addToMap(WorldChunk chunk){
		world.put(chunk.getIndex(), chunk);
	}

	public void removeFromMap(long index){
		world.remove(index);
	}
	
	public void renderWorld(){
		for(long key : world.keySet()){
			//world.get(pos).renderTile(this, pos);
			world.get(key).render();
		}
	}

	public TileWorld getTile(int r, int q) {
		long key = WorldChunk.getChunkIndexFromBlockLocation(r, q);
		if(world.containsKey(key))
			return TileWorld.getTile(world.get(key).getTile((byte)(r&WorldChunk.CHUNK_MASK), (byte)(q&WorldChunk.CHUNK_MASK) ) );
		return TileWorld.getTile("tileNull");
	}
	
	/*public HashMap<HexPosition, TileWorld> getWorld(){
		return world;
	}*/
}
