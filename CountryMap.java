public class CountryMap{
private City[] cities;
  private int cityCount;

public CountryMap(int numberOfCities) {
	this.cities=new City[numberOfCities];
	this.cityCount=0;
}
public void addCity(String label,int maxRoutes) {
	if(cityCount<cities.length) {
		cities[cityCount]=new City(label,maxRoutes);
		cityCount++;
	}
}

public City findCity(String label) {
	for(int i=0;i<cityCount;i++) {
		if(cities[i].getLabel().equals(label)) {
			return cities[i];
		}
	
  }
	 System.out.println("Hata: " + label + " etiketiyle şehir bulunamadı.");
	return null;
}
public City[] getCities() {
	return cities;
}
}
