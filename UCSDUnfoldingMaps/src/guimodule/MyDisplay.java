package guimodule;

import processing.core.PApplet;

public class MyDisplay extends PApplet {
	
	public void setup() {
		size(400,400);
		background(200,200,200);
		
		
	}
	
	public void draw() {
		
		// fill the background clr of ellipse -->yellow
		fill(255,255,0);
		ellipse(200,200,390,390);
		
		fill(0,0,0); //black backgrnd of ellipse
		ellipse(120,130,50,70);
		ellipse(280,130,50,70);
		
//		noFill(); // for arc not to fill
		arc(200,270,110,110,0,PI); // arc for mouth
		
		
	}

}
