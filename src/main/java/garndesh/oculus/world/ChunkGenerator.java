package garndesh.oculus.world;

import java.util.Random;

import garndesh.oculus.tiles.TileWorld;
import garndesh.oculus.util.HexPosition;
import garndesh.oculus.util.Log;

public class ChunkGenerator {

	private static Random rand = new Random();
	
	public static WorldChunk generateChunk(int r, int q){
		WorldChunk chunk = new WorldChunk(r, q);
		int radius = WorldChunk.CHUNK_RADIUS;
		Log.d("generator", "generating chunk: "+r+":"+q+" of radius: "+radius);
		for(int x = -radius; x <=radius; x++){
			for(int y = -radius; y <= radius; y++){
				if(Math.abs(y+x)>radius) {
					Log.d("Generator", "skipping x= "+x+" y: " +y);
					continue;
				}
				Log.d("Generator", "creating tile x= "+x+" y: " +y);
				//chunk.setTile((byte)(x+radius), (byte)(y+radius), (short) TileWorld.tiles.getIndexOf("tileFloorDirt"));
				if(Math.abs(Math.abs(y)-Math.abs(x))==2 || rand.nextInt(20) == 0){
					chunk.setTile((byte)(x+radius), (byte)(y+radius), (short) TileWorld.tiles.getIndexOf("tileWallDirt"));
				} else {
					chunk.setTile((byte)(x+radius), (byte)(y+radius), (short) TileWorld.tiles.getIndexOf("tileFloorDirt"));
				}
			}
		}
		return chunk;
	}
}
