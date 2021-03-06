import java.util.ArrayList;
import java.util.Collections;

public class Admin {
    static private final String PASSWORD = "admin123";

    public static void menu() {
    System.out.print("Lösenord: ");
    String pwd = Input.userInputString();
    if (pwd.equals(PASSWORD)) {
      boolean looping = true;
      while (looping) {
        System.out.println();
        System.out.println("  ------------ ADMIN ------------");
        System.out.println("  Aktuell tid: " + Garage.systemTime + " s");
        System.out.println("  Prisinfo: " + Debit.taxa + " kr / " + Debit.timeUnit + " s");
        System.out.println("  Fyllnadsgrad: " + Garage.parkedVehicles.size());
        System.out.println("  -------------------------------");
        System.out.println("  0. Checka in flera fordon");
        System.out.println("  -------------------------------");
        System.out.println("  1. Ställ in tid");
        System.out.println("  2. Ta bort fordon");
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
        else if (userInput == 0) { addParkedVehicles(); }
      }
    } else {
      System.out.println("Felaktigt password");
    }
  }

  private static void setTime() {
    System.out.print('\n' + "  Ange tid: ");
    int time = Input.userInputInt();
    if (time != -1) {
      Garage.systemTime = time;
    }
  }

  private static void removeVehicle() {
    String regNr = Input.userInputRegNr();
    if (!regNr.equals("-1") && Input.checkRegNrFormat(regNr)) {
      // Kontrollera om angivet regNr finns i garaget
      if (Garage.checkPresence(regNr)) {
        // Med hjälp av regNr, ta reda på vilket index som fordonet finns på
        int parkingIndex = Garage.getParkingIndex(regNr);
        // Hämta start-tiden för fordonet på ovanstående index
        int parkingStartTime = Garage.parkedVehicles.get(parkingIndex).startTime;
        // Räkna ut parkerad tid genom att subtrahera från nutid (systemtid)
        int parkedTime = Garage.systemTime - parkingStartTime;
        // Markera ärendet som obetalt genom att sätta till -1
        // OBS! Detta kanske vi vill ändra!
        double price = -1.0;
        // Logga ändå vad vi _skulle_ ha tagit i betalt
        double taxa = Garage.parkedVehicles.get(parkingIndex).taxa;
        double timeUnit = Garage.parkedVehicles.get(parkingIndex).timeUnit;
        // Logga ärendet
        Log.addEntry(Customer.getDate(), regNr, taxa, timeUnit, parkingStartTime, Garage.systemTime, parkedTime, price);
        Garage.parkedVehicles.remove(parkingIndex); // Ta bort fordonet
        System.out.println("  Fordonet med registreringsnummer " + regNr + " togs bort ur listan.");
      } else {
        System.out.println("  Fordonet med registreringsnummer " + regNr + "finns inte!");
      }
    } else {
      System.out.println("  Ogiltigt registreringsnummer.");
    }
    /*
    String regNr = Input.userInputRegNr();
    boolean befintligt = false;

    // TODO: Kontrollera här om registreringsnumret finns - använd metoden checkPresence i Garage-klassen
    if (befintligt) {
      System.out.println("  Fordonet med registreringsnummer " + regNr + " togs bort ur listan.");
    } else {
      System.out.println("  Ogiltigt registreringsnummer.");
    }*/
  }

  // Metod för att checka in ett antal fördefinierade fordon
  // Förhindrar överfyllnad av garaget samt dubbletter av regNr
  private static void addParkedVehicles() {
    // Räkna ut antal lediga platser
    int freeSlots = Garage.garageSize - Garage.parkedVehicles.size();
    System.out.println('\n' + "  Det finns " + freeSlots + " lediga platser." + '\n');

    if (freeSlots > 0) {
      System.out.print("  Ange antal fordon att checka in: ");
      int num = Input.userInputInt();

      // Önskat antal får inte vara större än lediga platser i garaget
      if (num > 0 && num <= freeSlots) {
        System.out.println();
        String predefinedRegNr = "-1";
        int predefinedStartTime = -1;
        int i = 0;
        int addedVehicles = 0;

        // Fortsätt försöka lägga till fordon tills önskat antal (num) är uppnått
        while (addedVehicles != num) {
          // Hämta fördefinierat regNr
          predefinedRegNr = ParkedVehicle.regNrList.get(i);
          // Hämta fördefinierad start-tid
          predefinedStartTime = ParkedVehicle.startTimeList.get(i);

          // Kontrollera att inte regNr redan finns i parkeringslistan
          if (!Garage.checkPresence(predefinedRegNr)) {
            // Om regNr är ledigt (inte finns),
            // skapa ett nytt fordonsobjekt med data från de färdiga listorna
            ParkedVehicle vehicle =
                    new ParkedVehicle(predefinedRegNr, predefinedStartTime, Debit.timeUnit, Debit.taxa);
            // Lägg till det skapade objektet i listan över aktiva ärenden
            Garage.parkedVehicles.add(vehicle);
            System.out.println("  " + predefinedRegNr + " lades till");
            addedVehicles++;
            i++;
          } else {
            // Ett fordon med det fördefinierade regNr finns redan
            System.out.println("  " + predefinedRegNr + " var upptaget");
            i++; // Kolla nästa regNr under nästa varv
          }
        }
        System.out.println(); // Färdigt
      } else if (num > freeSlots) {
        // Användaren försökte lägga till för många fordon
        System.out.println('\n' + "  Felaktigt antal, försök igen." + '\n');
      } else if (num == 0) {
        // Användaren vill inte lägga till några fordon
        System.out.println('\n' + "  Inga fordon har lagts till." + '\n');
      }
    }
    Input.promptEnterKey();
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
      System.out.print("  " + (i+1) + "   ");
      System.out.print  ("Reg-nr: "   + regNr + "  ");
      System.out.println("Starttid: " + startTime);
    }
    System.out.println();
    Input.promptEnterKey();
  }

  private static void printOverdue() {
    System.out.println('\n' + "  Övertrasserade fordon: " + '\n');
    int counter = 0;
    for (int i = 0; i < Garage.parkedVehicles.size(); i++) {
      // Hämta registreringsnumret
      String regNr = Garage.parkedVehicles.get(i).regNr;
      // Hämta starttiden
      int startTime = Garage.parkedVehicles.get(i).startTime;
      // Beräkna parkerad tid
      int parkedTime = Garage.systemTime - startTime;
      // Skriv ut ifall parkedTime är 3 dygn eller mer
      if (parkedTime >= 259200) {
        System.out.print("  " + (counter+1) + "   ");
        System.out.print  ("Reg-nr: "   + regNr + "  ");
        System.out.println("Starttid: " + startTime);
        counter++;
      }
    }
    System.out.println();
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
    if (time != -1) {
      Debit.timeUnit = time;
    }
  }

  private static void setRate() {
    System.out.print('\n' + "  Ange taxa: ");
    double rate = Input.userInputDouble();
    if (rate != -1) {
      Debit.taxa = rate;
    }
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
