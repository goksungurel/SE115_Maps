import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner; 
import java.io.PrintWriter; 
public class Main{
  public static void main(String[] args){
if (args.length < 1) {
    System.out.println("Error: Please provide the file name as a command-line argument.");
    return;
}

String fileName = args[0];


        try {
            Scanner fileScanner = new Scanner(Paths.get(fileName));

            // 1. Şehir sayısını okur
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
          //şehir etikelerini okur
          String cityLabelLine=fileScanner.nextLine().trim.();
          String[] cityLabel=cityLabelLine.split("\\s+");
          if (cityLabel.length != numCities) {
              System.out.println("Error: Number of city labels does not match the number of cities.");
              return;
          }
          // CountryMap oluştur
    	  CountryMap map=new CountryMap(numCities);
    	  for(String label:cityLabel) {
    		  map.addCity(label, numCities);
    	   //Rota sayısını oku
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
    	  
    	  //rotaları ekle
    	  for(int i=0;i<numRoutes;i++) {
    		  try {
    		  String city1=fileScanner.next();
    		  String city2=fileScanner.next();
    		  
    		

    		  if(!fileScanner.hasNextInt()) {
    			  System.out.println("Error: Invalid time format for route between " + city1 + " and " + city2);
    			  return;
    		  }
    		  
    		  int time=fileScanner.nextInt();
    		  fileScanner.nextLine(); 
    		  
    		  
    		  map.findCity(city1).addRoute(city2, time);
    		  map.findCity(city2).addRoute(city1, time);
    	  }catch (Exception e) {
              System.out.println("Error: Invalid route format on line " + (i + 4));
              return;
          }
    	  }
    	  
    	//  Başlangıç ve bitiş şehirlerini oku
    	  String startCity=fileScanner.next();
    	  String endCity=fileScanner.next();
    	  
    	  if (!cityExists(startCity, cityLabel)) {
              System.out.println("Error: Starting city '" + startCity + "' not found in the city list.");
              return;
          }

          if (!cityExists(endCity, cityLabel)) {
              System.out.println("Error: Ending city '" + endCity + "' not found in the city list.");
              return;
          }
    	  

    	  // En hızlı rotayı bul
    	  WayFinder wayFinder=new WayFinder(map);
    	  
    	  String result=wayFinder.findFastestRoute(startCity, endCity);
    	  
    	  System.out.println(result);
    	  
    	  writeToFile("output.txt", result);
    	  
    	  fileScanner.close();
    	  
    	  } catch (IOException e) {
          System.out.println("Hata: Dosya bulunamadı veya okunamadı - " + fileName);
      } 
    }
    private static void writeToFile(String fileName,String content) {
    	try(PrintWriter writer =new PrintWriter(fileName)){
    		writer.println(content);
    		System.out.println("Sonuç " + fileName + " dosyasına yazıldı.");
        } catch (IOException e) {
            System.out.println("Hata: Dosyaya yazma işlemi başarısız.");
    	
    }
    
}
    private static boolean cityExists(String city ,String[] cityLabel) {
    	for(String label : cityLabel) {
    		if(label.equals(city)) {
    			return true;
    		}
    	}
    	return false;
    }
    
}
