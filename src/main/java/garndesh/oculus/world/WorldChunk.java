package garndesh.oculus.world;

import garndesh.oculus.Constants;
import garndesh.oculus.tiles.TileWorld;
import garndesh.oculus.util.HexPosUtil;
import garndesh.oculus.util.HexPosition;
import garndesh.oculus.util.Log;

import org.saintandreas.gl.MatrixStack;
import org.saintandreas.math.Vector3f;

public class WorldChunk {

	public static final byte CHUNK_SIZE_BITS = 3;
	public static final short CHUNK_SIZE = 1 << CHUNK_SIZE_BITS;
	public static final byte CHUNK_MASK = CHUNK_SIZE - 1;
	public static final int CHUNK_RADIUS = CHUNK_SIZE / 2 - 1;

	private int r, q;
	private int centerR;
	private int centerQ;
	private short[] list = new short[CHUNK_SIZE * CHUNK_SIZE];
	private MatrixStack mv;

	public WorldChunk(int r, int q) {
		this.r = r;
		this.q = q;
		centerR = r * (2 * CHUNK_RADIUS + 1) + q * CHUNK_RADIUS;
		centerQ = q * (CHUNK_RADIUS + 1) - r * CHUNK_RADIUS;
		Log.d("WorldChunk", "Chunk pos: "+ centerR + ":" + centerQ);
		mv = MatrixStack.MODELVIEW;
	}

	public boolean setTile(byte r, byte q, short tile) {
		if ((r * CHUNK_SIZE + q) < 0 || (r * CHUNK_SIZE + q) >= list.length)
			return false;
		list[r * CHUNK_SIZE + q] = tile;
		return true;
	}

	public boolean setTiles(short[] tiles) {
		if (tiles.length != CHUNK_SIZE * CHUNK_SIZE)
			return false;
		list = tiles;
		return true;
	}

	public short getTile(byte r, byte q) {
		return list[r * CHUNK_SIZE + q];
	}

	public void render() {
		mv.push();
		mv.translate(HexPosUtil.getVectorFromAxial(centerR, centerQ));
		for (int x = -CHUNK_RADIUS; x <= CHUNK_RADIUS; x++) {
			for (int y = -CHUNK_RADIUS; y <= CHUNK_RADIUS; y++) {
				if (Math.abs(y + x) > CHUNK_RADIUS)
					continue;
				// Log.d("WorldChunk", "rendering tile @ x: "+x+" y "+y);
				mv.push();
				mv.translate(HexPosUtil.getVectorFromAxial(x, y));
				/*if(Math.abs(x-y)<2)
					mv.translate(new Vector3f(0, 0.3F*x, 0.3F*y));*/
				TileWorld.tiles.getObject(
						getTile((byte) (x + CHUNK_RADIUS),
								(byte) (y + CHUNK_RADIUS))).renderTile(
						centerR + x, centerQ + y);
				mv.pop();
			}
		}
		mv.pop();

	}

	public long getIndex() {
		return getIndex(this.centerR, this.centerQ);
	}

	@Override
	public int hashCode() {
		long i = getIndex(this.centerR, this.centerQ);
		int j = (int) i;
		int k = (int) (i >> 32);
		return j ^ k;
	}

	@Override
	public boolean equals(Object par1Obj) {
		WorldChunk worldChunk = (WorldChunk) par1Obj;
		return worldChunk.r == this.r && worldChunk.q == this.q;
	}

	public static long getIndex(int r, int q) {
		return (long) (r & Constants.COORDINATE_MULTIPLYER | (q & Constants.COORDINATE_MULTIPLYER) << 32);
	}

	public static HexPosition getPosFromIndex(long index) {
		int r = (int) (index & Constants.COORDINATE_MULTIPLYER);
		int q = (int) ((index >> 32) & Constants.COORDINATE_MULTIPLYER);
		return new HexPosition(r, q);
	}
	
	public static long getChunkIndexFromBlockLocation(int r, int q){
		HexPosition center = getChunkCenter(r, q);
		return getIndex(center.getR(), center.getQ());
	}
	
	public static HexPosition getChunkCenter(int r, int q){
		float chunkQ = (float)((2*CHUNK_RADIUS+1)*q+CHUNK_RADIUS*r)/(float)(3*CHUNK_RADIUS*CHUNK_RADIUS+3*CHUNK_RADIUS+1);
		float chunkR = (float)(r-q*chunkQ)/(float)(2*CHUNK_RADIUS+1);
		return new HexPosition(Math.round(chunkR), Math.round(chunkQ));
	}
	
}
