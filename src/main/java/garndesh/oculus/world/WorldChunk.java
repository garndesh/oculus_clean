package garndesh.oculus.world;

import garndesh.oculus.tiles.TileWorld;
import garndesh.oculus.util.HexPosUtil;
import garndesh.oculus.util.HexPosition;

import org.saintandreas.gl.MatrixStack;

public class WorldChunk {

	public static final short CHUNK_SIZE = 32;
	
	private int r, q;
	private short[] list = new short[CHUNK_SIZE*CHUNK_SIZE];
	private MatrixStack mv;
	
	public WorldChunk(int r, int q){
		this.r = r;
		this.q = q;
		mv = MatrixStack.MODELVIEW;
	}
	
	public boolean setTile(byte r, byte q, short tile){
		if(r <= -CHUNK_SIZE/2 || r >= CHUNK_SIZE/2 || q <= -CHUNK_SIZE/2 || q >= CHUNK_SIZE/2 )
			return false;
		list[(r+CHUNK_SIZE/2)*CHUNK_SIZE+(q+CHUNK_SIZE/2)] = tile;
		return true;
	}
	
	public boolean setTiles(short[] tiles){
		if(tiles.length!=CHUNK_SIZE*CHUNK_SIZE)
			return false;
		list = tiles;
		return true;
	}
	
	public short getTile(byte r, byte q){
		return list[(r+CHUNK_SIZE/2)*CHUNK_SIZE+(q+CHUNK_SIZE/2)];
	}

	public void render() {
		mv.push();
		mv.translate(HexPosUtil.getVectorFromAxial(r*CHUNK_SIZE, q*CHUNK_SIZE));
			for(byte x = -CHUNK_SIZE/2; x < CHUNK_SIZE/2; x++){
				for(byte y = -CHUNK_SIZE/2; y < CHUNK_SIZE/2; y++){
					((TileWorld)TileWorld.tiles.getObject(getTile(x, y))).renderTile(new HexPosition(q*CHUNK_SIZE+x, r*CHUNK_SIZE+y));
				}
			}
		mv.pop();
		
	}
}
