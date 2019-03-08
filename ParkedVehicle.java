import java.util.ArrayList;
import java.util.Arrays;

public class ParkedVehicle {

  public String regNr = "-1";
  public int startTime = -1;

  // Fördefinierade registreringsnummer
  public static ArrayList<String> regNrList = new ArrayList<String>
          (Arrays.asList("AAA001", "AAA002", "AAA003", "AAA004", "AAA005",
                  "AAA006", "AAA007", "AAA008", "AAA009", "AAA010", "AAA011",
                  "AAA012", "AAA013", "AAA014", "AAA015", "AAA016", "AAA017",
                  "AAA018", "AAA019", "AAA020"));

  // Fördefinierade start-tider
  public static ArrayList<Integer> startTimeList = new ArrayList<Integer>
          (Arrays.asList(Garage.systemTime, Garage.systemTime,
                  Garage.systemTime, Garage.systemTime, Garage.systemTime,
                  Garage.systemTime, Garage.systemTime, Garage.systemTime,
                  Garage.systemTime, Garage.systemTime, Garage.systemTime,
                  Garage.systemTime, Garage.systemTime, Garage.systemTime,
                  Garage.systemTime, Garage.systemTime, Garage.systemTime,
                  Garage.systemTime, Garage.systemTime, Garage.systemTime));

  /*
  public static ArrayList<Integer> startTimeList = new ArrayList<Integer>
          (Arrays.asList(900, 1200, 1900, 2400, 3200, 4000, 6000, 7000, 8000,
                  9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000, 17000,
                  18000, 19000)); */

  // Konstruktor för parkerade fordon
  public ParkedVehicle(String regNr, int startTime) {
    this.regNr = regNr;
    this.startTime = startTime;
  }

  // Dessa behövs bara om vi gör instansvariablerna privata
  // public String getParkedRegNr() { return this.regNr; }
  // public int getParkedStartTime() { return this.startTime; }

}
