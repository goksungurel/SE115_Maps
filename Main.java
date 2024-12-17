import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner; 
import java.io.PrintWriter; 
public class Main{
  public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);

        System.out.println("Lütfen dosya adını giriniz: ");
        String fileName = scanner.nextLine();

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
}
  }
}
