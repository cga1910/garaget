public class Debit {

  // Eftersom vi aldrig behöver flera debit-objekt samtidigt (vi ska alltid
  // ha enbart ett pris, etc), låter vi variablerna vara klassvariabler (static)
  // gissar jag.
  static double timeUnit = 900;
  static double taxa = 10;
  static double ADT = -1;
  static double debit = -1;

  public static double getDebit(double parkedTime, int parkingIndex) {
    double timeUnit = Garage.parkedVehicles.get(parkingIndex).timeUnit;
    double taxa = Garage.parkedVehicles.get(parkingIndex).taxa;
    ADT = parkedTime / timeUnit;
    ADT = Math.ceil(ADT); // Avrunda uppåt
    debit = ADT * taxa;
    return debit;
  }

}
