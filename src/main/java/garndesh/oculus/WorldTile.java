package garndesh.oculus;

import java.util.ArrayList;
import java.util.List;

public abstract class WorldTile {
	
	private HexPosition position;
	private ModelBase floor;
	private List<ModelBase> walls = new ArrayList<>(6);
	private ModelBase ceiling;
	
	public WorldTile(HexPosition pos){
		this.position = pos;
	}

}
