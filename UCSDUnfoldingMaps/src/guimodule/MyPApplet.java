package guimodule;

import processing.core.PApplet;
import processing.core.PImage;

public class MyPApplet extends PApplet {
	  String url="https://storage.needpix.com/rsynced_images/sunset-2629542_1280.jpg";
	  PImage img;
	  
	 public void setup()
	 {
		 size(700,400);
		// stroke(0);
		 img=loadImage(url,"jpg");
		// img.resize(0, height);
		 //image(img,0,0);
	 }
	 public void draw()
	 {
		 img.resize(0, height);
		 image(img,0,0);
		 // add drawing color for MyPApplet
		 int[] color=sunColorSec(second());
		 fill(color[0],color[1],color[2]); //set sun color
		 ellipse(355,110,150,150); //draw sun
	 }
	 
	 public int[] sunColorSec(float seconds)
	 {
		 int[] rgb=new int[3];
		 //scale the brightness of the yellow based on the scnd
		 // 30 scnds is black. 0 secnds is bright yellow
		 float diffFrom30= Math.abs(30-seconds);
		 
		 float ratio=diffFrom30/30;
		 rgb[0]=(int)(255*ratio);
		 rgb[1]=(int)(255*ratio);
		 rgb[2]=0;
		 
		 return rgb;
	 }
	/* public static void main(String arg[])
	 {
		//String[] processingArgs = {"MyPApplet"};
		//MyPApplet mpa = new MyPApplet();
		//PApplet.runSketch(processingArgs, mpa);
		String[] appletArgs = new String[] { "MyPApplet" };
		PApplet.main(appletArgs);
	 }*/
}
