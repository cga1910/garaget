import java.util.ArrayList;
import java.util.Collections;

public class Admin {

    public static void menu() {
    System.out.print("Lösenord: ");
    String pwd = Input.userInputString();
    boolean looping = true;
    while (looping) {
      System.out.println();
      System.out.println("  ------------ ADMIN ------------");
      System.out.println("  Aktuell tid: " + Garage.systemTime);
      System.out.println("  Prisinfo: ");
      System.out.println("  Fyllnadsgrad: " + Garage.parkedVehicles.size());
      System.out.println("  -------------------------------");
      System.out.println("  1. Ställ in tid");
      System.out.println("  2. Ta bort fordon");
      System.out.println("  ---> 8. Checka in X antal fordon.");
      System.out.println("  3. Status");
      System.out.println("  4. Prissättning");
      System.out.println("  5. Logg och statistik");
      System.out.println("  6. Tillbaka");
      System.out.println("  -------------------------------");
        System.out.print("  >> ");
      int userInput = Input.userInputInt();
      if      (userInput == 1) { setTime(); }
      else if (userInput == 2) { removeVehicle(); }
      else if (userInput == 3) { statusMenu(); }
      else if (userInput == 4) { setPricing(); }
      else if (userInput == 5) { logsAndStatsMenu(); }
      else if (userInput == 6) { looping = false; }
      else if (userInput == 8) { addParkedVehicles(); }
    }
  }

  private static void setTime() {
    System.out.print('\n' + "  Ange tid: ");
    int time = Input.userInputInt();
    Garage.systemTime = time;
  }

  private static void removeVehicle() {
    String regNr = Input.userInputRegNr();
    boolean befintligt = false;
    // TODO: Kontrollera här om registreringsnumret finns - använd metoden checkPresence i Garage-klassen
    if (befintligt) {
      System.out.println("  Fordonet med registreringsnummer " + regNr + " togs bort ur listan.");
    } else {
      System.out.println("  Ogiltigt registreringsnummer.");
    }
  }

  // Metod för att checka in ett antal fördefinierade fordon
  private static void addParkedVehicles() {
    System.out.print('\n' + "  Ange antal fördefinierade fordon (1-20) att checka in: ");
    int num = Input.userInputInt();
    // Kontrollera att angivet antal fordon är tillåtet
    if (num > 0 && num < 21) {
      System.out.print('\n' + "  Lägger till fordon: ");
      for (int i = 0; i < num; i++) {
        // Skapa ett nytt fordonsobjekt med data från de färdiga listorna
        ParkedVehicle vehicle =
                new ParkedVehicle(ParkedVehicle.regNrList.get(i),
                                  ParkedVehicle.startTimeList.get(i));
        // Lägg till det skapade objektet i listan över aktiva parkeringsärenden
        Garage.parkedVehicles.add(vehicle);
        System.out.print(i + " ");
      }
      System.out.println(); // Färdigt!
    } else {
      System.out.println('\n' + "  Felaktigt antal, försök igen.");
    }
  }

  private static void statusMenu() {
    boolean looping = true;
    while (looping) {
      System.out.println();
      System.out.println("  ------------ Status -----------");
      System.out.println("  1. Lista incheckade fordon");
      System.out.println("  2. Lista övertrasserade fordon");
      System.out.println("  3. Tillbaka");
      System.out.println("  -------------------------------");
        System.out.print("  >> ");
      int userInput = Input.userInputInt();
      if      (userInput == 1) { printParked(); }
      else if (userInput == 2) { printOverdue(); }
      else if (userInput == 3) { looping = false; }
    }
  }

  private static void printParked() {
    System.out.println('\n' + "  Incheckade fordon: " + '\n');
    for (int i = 0; i < Garage.parkedVehicles.size(); i++) {
      // Hämta registreringsnumret
      String regNr = Garage.parkedVehicles.get(i).regNr;
      // Hämta starttiden
      int startTime = Garage.parkedVehicles.get(i).startTime;
      // Skriv ut...
      System.out.print((i+1) + "   ");
      System.out.print  ("Reg-nr: "   + regNr + "  ");
      System.out.println("Starttid: " + startTime);
    }
    System.out.println();
    Input.promptEnterKey();
  }

  private static void printOverdue() {
    System.out.println('\n' + "  Övertrasserade fordon: " + '\n');
    Input.promptEnterKey();
  }

  private static void setPricing() {
    boolean looping = true;
    while (looping) {
      System.out.println();
      System.out.println("  --------- Prissättning --------");
      System.out.println("  1. Sätt tidsenhet");
      System.out.println("  2. Sätt taxa");
      System.out.println("  3. Tillbaka");
      System.out.println("  -------------------------------");
        System.out.print("  >> ");
      int userInput = Input.userInputInt();
      if      (userInput == 1) { setTimeUnit(); }
      else if (userInput == 2) { setRate(); }
      else if (userInput == 3) { looping = false; }
    }
  }

  private static void setTimeUnit() {
    System.out.print('\n' + "  Ange tidsenhet: ");
    int time = Input.userInputInt();
  }

  private static void setRate() {
    System.out.print('\n' + "  Ange taxa: ");
    double time = Input.userInputDouble();
  }

  private static void logsAndStatsMenu() {
    boolean looping = true;
    while (looping) {
      System.out.println();
      System.out.println("  ------ Logg och statistik -----");
      System.out.println("  1. Logg");
      System.out.println("  2. Totalt antal parkeringar");
      System.out.println("  3. Totala intäkter");
      System.out.println("  4. Genomsnittlig parkeringstid");
      System.out.println("  5. Parkerad mediantid");
      System.out.println("  6. Tillbaka");
      System.out.println("  -------------------------------");
        System.out.print("  >> ");
      int userInput = Input.userInputInt();
      if      (userInput == 1) { printLog(); }
      else if (userInput == 2) { printTotalParkings(); }
      else if (userInput == 3) { printTotalIncome(); }
      else if (userInput == 4) { printParkingTimeAverage(); }
      else if (userInput == 5) { printParkingTimeMedian(); }
      else if (userInput == 6) { looping = false; }
    }
  }

  private static void printLog() {
    System.out.println('\n' + "  Logg:" + '\n');
    for (int i = 0; i < Log.list.size(); i++) {
      // Hämta information från loggen
      String date = Log.list.get(i).date;
      String regNr = Log.list.get(i).regNr;
      double taxa = Log.list.get(i).taxa;
      double timeUnit = Log.list.get(i).timeUnit;
      int startTime = Log.list.get(i).startTime;
      int endTime = Log.list.get(i).endTime;
      int parkedTime = Log.list.get(i).parkedTime;
      double debit = Log.list.get(i).debit;
      // Skriv ut...
      System.out.print("  " + (i+1) + ".  ");
      System.out.print  ("Utcheckningsdatum: " + date       + "  ");
      System.out.print  ("Reg-nr: "            + regNr      + "  ");
      System.out.print  ("Taxa: "              + taxa       + "  ");
      System.out.print  ("Tidsenhet: "         + timeUnit   + "  ");
      System.out.print  ("Starttid: "          + startTime  + "  ");
      System.out.print  ("Sluttid: "           + endTime    + "  ");
      System.out.print  ("Parkerad tid: "      + parkedTime + "  ");
      System.out.println("Debitering: "        + debit      + "  ");
    }
    System.out.println();
    Input.promptEnterKey();
  }

  private static void printTotalParkings() {
    System.out.print('\n' + "  Totalt antal parkeringar: " + '\n'); // Eller "Antal parkeringar sedan systemstart"
    System.out.print("  " + Log.list.size());
    System.out.println();
    Input.promptEnterKey();
  }

  private static void printTotalIncome() {
    System.out.print('\n' + "  Totala intäkter: " + '\n');
    double debitTotal = 0;
    for (int i = 0; i < Log.list.size(); i++) {
      // Hämta information från loggen
      double debit = Log.list.get(i).debit;
      debitTotal = debitTotal + debit;
    }
    System.out.print("  " + debitTotal + "kr");
    System.out.println();
    Input.promptEnterKey();
  }

  private static void printParkingTimeAverage() {
    System.out.print('\n' + "  Genomsnittlig parkeringstid: " + '\n');
    int parkedTimeTotal = 0;
    int numberOfParkings = 0;
    for (int i = 0; i < Log.list.size(); i++) {
      // Hämta information från loggen
      int parkedTime = Log.list.get(i).parkedTime;
      parkedTimeTotal = parkedTimeTotal + parkedTime;
      numberOfParkings++;
    }
    double parkedTimeAverage = parkedTimeTotal / numberOfParkings; // Beräkna genomsnittlig parkeringstid, tappar dock decimalerna...
    System.out.print("  " + parkedTimeAverage);
    System.out.println();
    Input.promptEnterKey();
  }

  private static void printParkingTimeMedian() {
    System.out.print('\n' + "  Parkerad mediantid: " + '\n');
    ArrayList<Integer> parkedTimeList = new ArrayList<Integer>();
    for (int i = 0; i < Log.list.size(); i++) {
      // Hämta information från loggen
      int parkedTime = Log.list.get(i).parkedTime;
      parkedTimeList.add(parkedTime);
    }
    Collections.sort(parkedTimeList);
    // Index bestäms av liststorlekens paritet, dvs huruvida den är jämn eller udda
    int parkedTimeMedian = 0;
    if (parkedTimeList.size() % 2 == 0) {
      // Medianen i en jämn sorterad lista är medlet av de två indexen i mitten
      int index1 = parkedTimeList.size()/2;
      int index2 = index1 - 1;
      parkedTimeMedian = (parkedTimeList.get(index1) + parkedTimeList.get(index2))/2;
    }
    else {
      int index = (parkedTimeList.size() - 1)/2; // Index för medianen i en sorterad lista när storleken på listan är udda är indexet i mitten
      parkedTimeMedian = parkedTimeList.get(index);
    }
    System.out.print("  " + parkedTimeMedian);
    System.out.println();
    Input.promptEnterKey();
  }

}
