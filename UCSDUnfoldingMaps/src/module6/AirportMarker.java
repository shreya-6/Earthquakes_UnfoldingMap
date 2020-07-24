package module6;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker implements Comparable<AirportMarker> {
	public static List<SimpleLinesMarker> routes;
	
	/** Greater than or equal to this threshold is an intermediate depth */
	public static final float THRESHOLD_INTERMEDIATE = 500;
	/** Greater than or equal to this threshold is a deep depth */
	public static final float THRESHOLD_DEEP = 1500;
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	
	}
	
	 public int compareTo(AirportMarker marker) {
		 return (this.getCountry()).compareTo(marker.getCountry());
		 
	 }
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
	//	pg.fill(0,0,255);
		pg.ellipse(x, y, 5, 5);
		// determine color of marker from depth
		colorDetermine(pg);
		
		
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		 // show rectangle with title

		// TODO: Implement this method
	String name =getCity()+" "+getCountry()+" "+getCode()+" "+getName();
	pg.pushStyle();
	
	pg.rectMode(PConstants.CORNER);
	
	pg.stroke(110);
	pg.fill(255,0,0);
	pg.rect(x, y + 15, pg.textWidth(name) +6, 18, 5);
	
	pg.textAlign(PConstants.LEFT, PConstants.TOP);
	pg.fill(0);
	pg.text(name, x + 3 , y +18);
	
	
	pg.popStyle();
	
		
		// show routes
		
		
	}
	private void colorDetermine(PGraphics pg) {
		//TODO: Implement this method
		 if(getAltitude()>THRESHOLD_DEEP)
			{
				pg.fill(255,0,0);  //red
			}
		 else if(getAltitude()>=THRESHOLD_INTERMEDIATE && getAltitude()<=THRESHOLD_DEEP)
			{
				pg.fill(0,0,255);  //blue
			}
		 else if(getAltitude()<THRESHOLD_INTERMEDIATE){
			 pg.fill(255,255,0);   //yellow
		 }
	}
	 
	public float getAltitude() {
		return Float.parseFloat(getProperty("altitude").toString());
	}
	public String getName() {
		return (String)getProperty("name");	
		
	}
	public String getCity() {
		return (String)getProperty("city");	
		
	}
	public String getCountry() {
		return (String)getProperty("country");	
		
	}
	public String getCode() {
		return (String)getProperty("code");	
		
	}
}
