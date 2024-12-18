import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner; 
import java.io.PrintWriter; 
public class Main{
  public static void main(String[] args){
     // Check if a command-line argument (file name) is provided
if (args.length < 1) {
    System.out.println("Error: Please provide the file name as a command-line argument.");
    return;
}

String fileName = args[0];
        try {
           // Create a scanner to read from the input file
            Scanner fileScanner = new Scanner(Paths.get(fileName));

          // 1. Read the number of cities
            String numCitiesLine = fileScanner.nextLine().trim();
            int numCities;
            try {
                numCities = Integer.parseInt(numCitiesLine);

                if (numCities <= 0) {
                    System.out.println("Error: Invalid number of cities.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format for cities count.");
                return;
  }
          // Read the city labels
          String cityLabelLine=fileScanner.nextLine().trim();
          String[] cityLabel=cityLabelLine.split("\\s+");
          if (cityLabel.length != numCities) {
              System.out.println("Error: Number of city labels does not match the number of cities.");
              return;
          }
         // Create a CountryMap object to hold the cities
    	  CountryMap map=new CountryMap(numCities);
    	  for(String label:cityLabel) {
    		  map.addCity(label, numCities);
        }
    	   // Read the number of routes
    	  String numRoutesLine = fileScanner.nextLine().trim();
          int numRoutes;
          try {
              numRoutes = Integer.parseInt(numRoutesLine);
              if (numRoutes < 0) {
                  System.out.println("Error: Invalid number of routes.");
                  return;
              }
          } catch (NumberFormatException e) {
              System.out.println("Error: Invalid number format for routes.");
              return;
          }
    	  
    	  // Add the routes to the map
    	  for(int i=0;i<numRoutes;i++) {
    		  try {
    		  String city1=fileScanner.next();
    		  String city2=fileScanner.next();
    		  
           // Check if the next token is a valid integer for time
    		  if(!fileScanner.hasNextInt()) {
    			  System.out.println("Error: Invalid time format for route between " + city1 + " and " + city2);
    			  return;
    		  }
    		  
    		  int time=fileScanner.nextInt();
    		  fileScanner.nextLine(); 
    		  
    		  // Add the routes between the cities
    		  map.findCity(city1).addRoute(city2, time);
    		  map.findCity(city2).addRoute(city1, time);
    	  }catch (Exception e) {
              System.out.println("Error: Invalid route format on line " + (i + 4));
              return;
          }
    	  }
    	  
         // Read the start and end cities
    	  String startCity=fileScanner.next();
    	  String endCity=fileScanner.next();

          // Check if the start city exists in the list of city labels
    	  if (!cityExists(startCity, cityLabel)) {
              System.out.println("Error: Starting city '" + startCity + "' not found in the city list.");
              return;
          }
        	// Check if the end city exists in the list of city labels
          if (!cityExists(endCity, cityLabel)) {
              System.out.println("Error: Ending city '" + endCity + "' not found in the city list.");
              return;
          }
    	  

    	 // Find the fastest route between the start and end cities
    	  WayFinder wayFinder=new WayFinder(map);

           // Get the result of the fastest route
    	  String result=wayFinder.findFastestRoute(startCity, endCity);
    	  
    	  System.out.println(result);
    	  
    	  writeToFile("output.txt", result);
    	  
    	  fileScanner.close();
    	  
    	  } catch (IOException e) {
          System.out.println("Hata: Dosya bulunamadı veya okunamadı - " + fileName);
      } 
    }
  //Method to write content to a file
    private static void writeToFile(String fileName,String content) {
    	try(PrintWriter writer =new PrintWriter(fileName)){
    		writer.println(content);
    		System.out.println("Sonuç " + fileName + " dosyasına yazıldı.");
        } catch (IOException e) {
            System.out.println("Hata: Dosyaya yazma işlemi başarısız.");
    }
    
}
  // Method to check if a city exists in the city labels
    private static boolean cityExists(String city ,String[] cityLabel) {
    	for(String label : cityLabel) {
    		if(label.equals(city)) {
    			return true;
    		}
    	}
    	return false;
    }
    
}
