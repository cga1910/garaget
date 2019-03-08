import java.util.ArrayList;

public class Garage {

  public static final ArrayList<ParkedVehicle> parkedVehicles = new ArrayList<>();
  /* Anledningen till att jag provade att sätta "final" på ovanstående array:
   - If a FINAL variable holds a reference to an array, then the components of
   the array may be changed by operations on the array, but the variable will
   always refer to the same array.*/

  public int getListSize() { return parkedVehicles.size(); }

}
