package module6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import module6.CommonMarker;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	
	private CommonMarker lastSelected;
	private CommonMarker lastClicked;
	
	public void setup() {
		// setting up PAppler
		size(900,700, OPENGL);
		
		// setting up map and default events
		map = new UnfoldingMap(this, 190, 50, 680, 610);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
	
			m.setRadius(5);
			airportList.add(m);
			
		//	airportList.add(new AirportMarker(feature)) ;
			
			// put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
		
		}
		
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		
			//System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			//routeList.add(sl);
		}
		
		
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		map.addMarkers(routeList);
		sortAndPrint(10);
		
		map.addMarkers(airportList);
		
	}
	
	public void draw() {
		background(192,192,192);
		map.draw();
		addKey();
	}
	
	private void addKey() {	
		fill(255, 255, 204);
		rect(25, 50, 150, 250);
		
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);
		text("Airport Altitude", 50, 65);
		
		fill(color(255,255,0));
		ellipse(140, 100, 14, 14); // yellow
	
		fill(color(0, 0, 255));
		ellipse(140, 130, 14, 14);     //blue
		
		fill(color(255, 0, 0));
		ellipse(140,160,14,14);          // red
		
		fill(color(0, 0, 0));
		ellipse(140,190,14,14);          // black
		
		fill(0, 0, 0);
		text("< 500 : ", 40, 100);
		text("500-1500 : ", 40, 130);
		text("1500 > : ", 40, 160);
		text("No Info : ",40, 190);
		text("Press 'A' : All Routes",35, 220);

	}
	
	 private void sortAndPrint(int numToPrint) {
		  List<AirportMarker> arr = new ArrayList<AirportMarker>();
		   for(Marker qk: airportList){
		   arr.add((AirportMarker)qk);
		   }
		   Collections.sort(arr);
		   for (int i = 0; i < numToPrint; i++){
				AirportMarker marker = arr.get(i);
				System.out.println(marker.getCountry()+"  "+marker.getCity()+"  "+marker.getCode());
			}
	   }
	
	public void mouseMoved()
	{
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		selectMarkerIfHover(airportList);
	}
	
	// If there is a marker under the cursor, and lastSelected is null 
	// set the lastSelected to be the first marker found under the cursor

	private void selectMarkerIfHover(List<Marker> markers)
	{
		for(Marker m: markers) {
          if(lastSelected == null && m.isInside(map, mouseX, mouseY)) {
        	  m.setSelected(true);
              lastSelected = (CommonMarker) m;
            }
         }
	}
	
	public void mouseClicked()
	{
		if (lastClicked != null) {
			unhideMarkers();
			lastClicked = null;
		}
		else if (lastClicked == null) 
		{
			checkAirportsForClick();
		}
	}
	
	private void checkAirportsForClick()
	{
		if (lastClicked != null) return;
		// Loop over the earthquake markers to see if one of them is selected
		for (Marker m : airportList) {
			AirportMarker marker = (AirportMarker)m;
			if (!marker.isHidden() && marker.isInside(map, mouseX, mouseY)) {
				lastClicked = marker;
				// Hide all the other earthquakes and hide
				for (Marker mhide : airportList ) {
					if (mhide != lastClicked) {
						mhide.setHidden(true);
					}
				}
			/*	for (Marker mhide : airportList) {
					if (mhide. );
					}
				}*/
				return;
			}
		}
	}
	
	private void unhideMarkers() {
		for(Marker marker : airportList) {
			marker.setHidden(false);
		}
			
	}
}
