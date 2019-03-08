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
      System.out.println("  1. Påbörja parkering" + '\n' + 
                       "  2. Avsluta parkering");
      System.out.println("  -----------------------------------");
        System.out.print("  >> ");
      int userInput = Input.userInputInt();
      if      (userInput == 1) { checkIn(); }
      else if (userInput == 2) { checkOut(); }
      else if (userInput == 0) { looping = false; }
    }
  }

  private static void checkIn() {
    String regNr = Input.userInputRegNr(); // Funktionen returnerar strängen "-1" om användaren bara trycker Enter
    // Fortsätt bara om strängen innehåller något
    if (!regNr.equals("-1")) {
      // Kontrollera här registreringsnumrets format
      System.out.println("  Här ska registreringsnumrets format kontrolleras." + '\n');
      System.out.println("  Giltigt: ");
      printReceipt_checkIn();
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
        System.out.println("    Parkerad tid: " + parkedTime);
        System.out.println("    Taxa: " + Debit.taxa);
        System.out.println("    Antal debiterade tidsenheter: " + Debit.ADT);
        System.out.println("    Debitering: " + Debit.ADT + " * " + Debit.taxa + " = " + price);
        System.out.println("    Att betala: " + price + " kr");

        // Anropa betalnings-metoden
        cashPayment(price, parkedTime);

        // TODO: Här ska fordonet tas bort från listan över aktiva ärenden

      } else {
        System.out.println("  Kunde inte hitta registreringsnumret!" + '\n');
        Input.promptEnterKey();
      }
    }
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
        printReceipt_checkOut(price, parkedTime);
        break; // Bryt while-loopen
      } else if (balance < 0) {
        System.out.println("    Kvar att betala: " + (balance * -1));
      }
    }
  }

  private static void printReceipt_checkIn() {
    System.out.println('\n' + "    KVITTO ");
    System.out.println("    Registreringsnummer: ");
    System.out.println("    Tid: ");
    System.out.println("    (Datum:) " + '\n');
    Input.promptEnterKey();
  }
  
  private static void printReceipt_checkOut(double paid, int parkedTime) {
    System.out.println('\n' + "    KVITTO ");
    System.out.println("    (Datum:) ");
    System.out.println("    Parkerad tid: " + parkedTime);
    System.out.println("    Betalt: " + paid);
    System.out.println("    Utpasseringskod: " + '\n');
    Input.promptEnterKey();
  }
  
}