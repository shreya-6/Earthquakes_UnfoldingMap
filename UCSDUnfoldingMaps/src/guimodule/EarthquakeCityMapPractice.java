package guimodule;

import com.jogamp.opengl.math.geom.Frustum.Location;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PApplet;

public class EarthquakeCityMapPractice extends PApplet {
	private UnfoldingMap map;
	
	public void setup()
	{
		Location valLoc = new Location(-38.14f,-73.03f);
	   // Marker val = new SimplePointMarker(valLoc);
		//map.addMarker(val);
		PointFeature valEq = new PointFeature(valLoc);
	    valEq.addProperty("title", "Valdivia, Chile");
	    valEq.addProperty("magnitude", "9.5");
	    valEq.addProperty("date", "May 22, 1960");
	    
	    Marker valMk = new SimplePointMarker(valLoc, valEq.getProperties());
	    map.addMarker(valMk);
	}
	public void draw()
	{
	    background(10);
	    map.draw();
		addKey();
	}
	private void addKey() {
		// TODO Auto-generated method stub
		
	}

}
