package garndesh.oculus;

public class OculusMain {
	//private static MainLoop mainLoop;
	private static OculusTest game;
	
	public static void main(String[] args){
		game = new OculusTestImpl();
		game.run();
		//mainLoop = new MainLoop();
		//mainLoop.start();
	}
}
