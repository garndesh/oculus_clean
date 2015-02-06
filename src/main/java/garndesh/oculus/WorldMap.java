package garndesh.oculus;

import garndesh.oculus.tiles.TileWorld;

import java.util.HashMap;

import org.saintandreas.gl.MatrixStack;

public class WorldMap {
	private HashMap<HexPosition, TileWorld> world = new HashMap<>();
	private MatrixStack mv;
	
	
	public WorldMap(){
		mv = MatrixStack.MODELVIEW;
	}
	
	public void addToMap(HexPosition pos, TileWorld tile){
		world.put(pos, tile);
	}
	
	public void renderWorld(){
		for(HexPosition pos : world.keySet()){
			mv.push();
			mv.translate(pos.toCartesian());
			world.get(pos).renderTile(this, pos);
			mv.pop();
		}
	}
	
	public HashMap<HexPosition, TileWorld> getWorld(){
		return world;
	}
}
