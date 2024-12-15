public class WayFinder{
  private CountryMap map;
  public WayFinder(CountryMap map) {
		this.map=map;
	}
	  
public String findFastestRoute(String startCityLabel,String endCityLabel) {
	City startCity=map.findCity(startCityLabel);
	City endCity=map.findCity(endCityLabel);
	
if(startCity==null || endCity==null) {
	return "Eror: Start or end city not found.";
  
}
  int numCities=map.getCities().length;

int[] distance=new int[numCities];
boolean[] visited=new boolean[numCities];
String[] previous=new String[numCities];


for(int i=0;i<numCities;i++) {
	distance[i]=Integer.MAX_VALUE;
	visited[i]=false;
	previous[i]=null;
}

distances[getCityIndex(startCityLabel)] = 0;
for (int i = 0; i < numCities; i++) {
   
    int currentIndex = findMinDistanceIndex(distances, visited);
    if (currentIndex == -1) break; 

    visited[currentIndex] = true; 
    City currentCity = map.getCities()[currentIndex]; 

    for (int j = 0; j < currentCity.getRouteCount(); j++) {
        String connectedCityLabel = currentCity.getConnectedCities()[j];
        int travelTime = currentCity.gettravelTime()[j];
        int connectedIndex = getCityIndex(connectedCityLabel);

       
        if (!visited[connectedIndex] && distances[currentIndex] + travelTime < distances[connectedIndex]) {
            distances[connectedIndex] = distances[currentIndex] + travelTime;
            previous[connectedIndex] = currentCity.getLabel(); 
        }
    }
}


int endIndex = getCityIndex(endCityLabel);
if (distances[endIndex] == Integer.MAX_VALUE) {
    return "No path found to the destination city.";
}


StringBuilder path = new StringBuilder();
String current = endCityLabel;
while (current != null) {
    path.insert(0, current + " -> ");
    current = previous[getCityIndex(current)];
}
path.setLength(path.length() - 4); 

return "Fastest Path: " + path.toString() + "\nTotal Time: " + distances[endIndex] + " min";
}

private int getCityIndex(String label) {
City[] cities = map.getCities();
for (int i = 0; i < cities.length; i++) {
    if (cities[i].getLabel().equals(label)) {
        return i;
    }
}
return -1; 
}
private int findMinDistanceIndex(int[] distances, boolean[] visited) {
int minDistance = Integer.MAX_VALUE;
int minIndex = -1;
for (int i = 0; i < distances.length; i++) {
    if (!visited[i] && distances[i] < minDistance) {
        minDistance = distances[i];
        minIndex = i;
    }
}
return minIndex;
}
}
