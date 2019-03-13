import java.util.ArrayList;

public class Garage {

  public static int systemTime = 0;
  public static final int garageSize = 20;

  public static final ArrayList<ParkedVehicle> parkedVehicles = new ArrayList<>();
  /* Anledningen till att jag provade att sätta "final" på ovanstående array:
   - If a FINAL variable holds a reference to an array, then the components of
   the array may be changed by operations on the array, but the variable will
   always refer to the same array.*/

  // TODO: Följande två metoder kan kombineras till en enda, men det är låg prio...

  // Tar reda på om ett visst fordon finns i listan över aktiva ärenden
  public static boolean checkPresence(String regNr) {
    boolean presence = true;
    for (int i = 0; i < parkedVehicles.size(); i++) {
      if (regNr.equals(parkedVehicles.get(i).regNr)) {
        return presence;
      }
    }
    presence = false;
    return presence;
  }

  // Hitta och returnera index för ett visst registreringsnummer
  public static int getParkingIndex(String regNr) {
    for (int i = 0; i < parkedVehicles.size(); i++) {
      if (regNr.equals(parkedVehicles.get(i).regNr)) {
        return i;
      }
    }
    return -1; // När inget matchande registreringsnummer hittades i listan
  }

  public static String getStatus() {
    String status = "-1";
    if (parkedVehicles.size() < garageSize) {
      status= "LEDIGT";
    } else {
      status= "FULLT";
    }
    return status;
  }

}
