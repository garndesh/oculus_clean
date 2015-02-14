package garndesh.oculus.world;

import java.util.Random;

import garndesh.oculus.tiles.TileWorld;
import garndesh.oculus.util.HexPosition;

public class ChunkGenerator {

	private static Random rand = new Random();
	
	public static WorldChunk generateChunk(int r, int q){
		WorldChunk chunk = new WorldChunk(r, q);
		byte radius = (byte) WorldChunk.CHUNK_SIZE/2-1;
		for(byte x = (byte)-radius; x <=radius; x++){
			for(byte y = (byte)-radius; y <= radius; y++){
				if(Math.abs(y+x)>radius)
					continue;
				HexPosition pos = new HexPosition(x, y);
				if(Math.abs(Math.abs(y)-Math.abs(x))==2 || rand.nextInt(20) == 0){
					chunk.setTile(x, y, (short) TileWorld.tiles.getIndexOf("tileWallDirt"));
				} else {
					//map.addToMap(pos, (TileWorld) TileWorld.tiles.getObject("tileFloorDirt"));
					chunk.setTile(x, y, (short) TileWorld.tiles.getIndexOf("tileFloorDirt"));
				}
			}
		}
		return chunk;
	}
}
