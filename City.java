public class City{
   private String label;
   private String[] connectedCities;
   private int[] travelTime;    
   private int routeCount; 
  
public City(String label,int maxRoutes) {
	this.label=label;
	this.connectedCities=new String[maxRoutes];
	this.travelTime=new int[maxRoutes];
	this.routeCount=0;
}

public void addRoute(String city,int time) {
	if (routeCount < connectedCities.length) {
	connectedCities[routeCount]=city;
	travelTime[routeCount]=time;
	routeCount++;
	}else {
		System.out.println("Maximum number of routes reached! No more routes can be added.");
	}
}
	
public String getLabel() {
	return label;
}

public String[] getConnectedCities() {
	return connectedCities;
}
public int[] gettravelTime() {
	return travelTime;
}
public int getRouteCount() {
	return routeCount;
}
}

  
