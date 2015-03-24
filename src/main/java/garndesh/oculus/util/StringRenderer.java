package garndesh.oculus.util;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class StringRenderer {

	private Chars[] chars = new Chars[256];
	
	
	public StringRenderer(){
		chars[(int) '1'] = (new Chars()).addLine(0F, 1F, -0.1F, 0.9F).addLine(0, 0, 0, 1).addLine(-0.5F, 0, 0.5F, 0);
		chars[(int) '-'] = new Chars().addLine(-0.5F, 0.5F, 0.5F, 0.5F);
		chars[(int) ','] = new Chars().addLine(0, 0, -0.1F, -0.1F);
		chars[(int) '0'] = new Chars().addLine(-0.5F, 0, -0.5F, 1).addLine(0.5F, 0, 0.5F, 1).addLine(-0.5F, 0, 0.5F, 0).addLine(-0.5F, 1, 0.5F, 1).addLine(-0.5F, 0, 0.5F, 1);
	}
	
	public void renderText(String text, float size){
		GL11.glLineWidth(3);

		GL11.glColor3f(1.0F, 0.0F, 0.0F);
		GL11.glBegin(GL11.GL_LINES);
		for(char c : text.toCharArray()){
			if(chars[c]!=null){
				for(float[] line : chars[c].lines){
					GL11.glVertex3f(size * line[0], size * line[1], 0);
					GL11.glVertex3f(size * line[2], size * line[3], 0);
				}
			}
		}
		
		
		GL11.glEnd();
		
	}
	
	private class Chars {
		List<float[]> lines = new ArrayList<float[]>();
		
		public Chars addLine(float x0, float y0, float x1, float y1){
			lines.add(new float[]{x0, y0, x1, y1});
			return this;
		}
		
		public List getLines(){
			return lines;
		}
		
	}
}

