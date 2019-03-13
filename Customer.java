import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class Customer {

  public static void menu() {
    boolean looping = true;
    while (looping) {
      System.out.println();
      System.out.println("  ------------ P-automat ------------");
      System.out.println("  Tid: ");
      System.out.println("  Prisinfo: ");
      System.out.println("  Garaget är Fullt/Ledigt");
      System.out.println("  -----------------------------------");
      if (Garage.parkedVehicles.size() < 20) {
        System.out.println("  1. Påbörja parkering");
      }
      System.out.println("  2. Avsluta parkering");
      System.out.println("  -----------------------------------");
        System.out.print("  >> ");
      int userInput = Input.userInputInt();
      if      (userInput == 1 && (Garage.parkedVehicles.size() < 20) ) { checkIn(); }
      else if (userInput == 2) { checkOut(); }
      else if (userInput == 0) { looping = false; }
    }
  }

  private static void checkIn() {
    // TODO: Kontrollera här om garaget är fullt
    String regNr = Input.userInputRegNr(); // Funktionen returnerar strängen "-1" om användaren bara trycker Enter
    // Fortsätt bara om strängen innehåller något
    if (!regNr.equals("-1")) {
      // Kontrollera här registreringsnumrets format
      System.out.println("  Här ska registreringsnumrets format kontrolleras." + '\n');
      System.out.println("  Giltigt: ");
      ParkedVehicle vehicle = new ParkedVehicle(regNr, Garage.systemTime);
      Garage.parkedVehicles.add(vehicle);
      printReceipt_checkIn(regNr, Garage.systemTime);
      System.out.println('\n' + "  Ogiltigt: " + '\n');
      System.out.println("    Felmeddelande!" + '\n');
      Input.promptEnterKey();
    }
  }

  private static void checkOut() {
    String regNr = Input.userInputRegNr();
    // Fortsätt bara om strängen innehåller något
    if (!regNr.equals("-1")) {
      // Kontrollera om angivet regNr finns i garaget
      if (Garage.checkPresence(regNr)) {
        // 1. Med hjälp av regNr, ta reda på vilket index som fordonet finns på
        int parkingIndex = Garage.getParkingIndex(regNr);
        // 2. Hämta start-tiden för fordonet på ovanstående index
        int parkingStartTime = Garage.parkedVehicles.get(parkingIndex).startTime;
        // 3. Räkna ut parkerad tid genom att subtrahera från nutid (systemtid)
        int parkedTime = Garage.systemTime - parkingStartTime;

        // Hämta priset, baserat på parkerad tid
        double price = Debit.getDebit(parkedTime);

        System.out.println();
        System.out.println("    Parkerad tid: " + (parkedTime/60) + " minuter");
        System.out.println("    Taxa: " + Debit.taxa + " kr för varje påbörjad period om " + (Debit.timeUnit/60) + " minuter");
        System.out.println("    Antal debiterade perioder: " + Debit.ADT + '\n');
        System.out.println("    Debitering: " + Debit.ADT + " * " + Debit.taxa + " kr = " + price + " kr" + '\n');
        System.out.println("    Att betala: " + price + " kr");

        // Anropa betalnings-metoden
        cashPayment(price, parkedTime);

        // Logga ärendet
        // TODO: timeUnit och taxa ska plockas från fordons-objektet egentligen - ej från systemets nuvarande värden (i Debit),
        // TODO: men så länge incheckningen inte lagrar dessa variabler i fordons-objektet måste vi ta dem från Debit.
        // TODO: Men det är inte kritiskt.
        Log.addEntry(getDate(), regNr, Debit.taxa, Debit.timeUnit, parkingStartTime, Garage.systemTime, parkedTime, price);

        // Ta bort ärendet
        Garage.parkedVehicles.remove(parkingIndex);

        // Skapa en utpasseringskod
        int passCode = generatePasscode();

        // Skriv ut kvittot
        printReceipt_checkOut(price, parkedTime, passCode);

      } else {
        System.out.println("  Kunde inte hitta registreringsnumret!" + '\n');
        Input.promptEnterKey();
      }
    }
  }

  private static int generatePasscode() {
    Random random = new Random();
    return random.nextInt(9999);
  }

  private static void cashPayment(double price, int parkedTime) {
    double balance = 0 - price;
    double payment = 0;

    while (balance < 0) { // Upprepa så länge kunden har kvar att betala
      System.out.print("    >> ");
      payment = Input.userInputDouble();
      if (payment > 0) { // Godtag bara positiv betalning
        balance = balance + payment;
      }
      if (balance >= 0) { // Kunden har betalat tillräckligt
        if (balance > 0) {
          System.out.println("    Växel: " + balance + " kr");
        }
        System.out.println("    Tack för din betalning!" + '\n');
        Input.promptEnterKey();
        break; // Bryt while-loopen
      } else if (balance < 0) {
        System.out.println("    Kvar att betala: " + (balance * -1) + " kr");
      }
    }
  }

private static void printReceipt_checkIn(String regNr, int startTime) {
 System.out.println('\n' + "    KVITTO ");
 System.out.println("    Registreringsnummer: " + regNr);
 System.out.println("    Tid: " + startTime + '\n');
 // System.out.println("    (Datum:) " + '\n');
 Input.promptEnterKey();
}

  private static void printReceipt_checkOut(double paid, int parkedTime, int passCode) {
    System.out.println('\n' + "    KVITTO ");
    System.out.println("    Datum: " + getDate());
    System.out.println("    Parkerad tid: " + (parkedTime/60) + " minuter");
    System.out.println("    Betalt: " + paid + " kr" + '\n');
    System.out.println("    Utpasseringskod: " + passCode + '\n');
    Input.promptEnterKey();
  }

  private static String getDate() {
    LocalDateTime ldt = LocalDateTime.now();
    String date = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
    return date;
  }
}
