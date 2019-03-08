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
      // Kontrollera här om regNr är befintligt eller obefintligt
      System.out.println("  Här ska kontrolleras huruvida registreringsnumret finns i systemet eller ej." + '\n');
      System.out.println("  Befintligt: " + '\n');
      System.out.println("    Parkerad tid: ");
      System.out.println("    Debitering: ");
        System.out.print("    Betalningsprompt:");
      double payment = Input.userInputDouble();
      System.out.println("    Tack för din betalning!" + '\n');
      Input.promptEnterKey();
      double paid = -1;
      printReceipt_checkOut(paid);
      // Om regNr är obefintligt
      // System.out.println("  Obefintligt: " + '\n');
      // System.out.println("    Felmeddelande!" + '\n');
      // Input.promptEnterKey();
    }
  }

  private static void printReceipt_checkIn() {
    System.out.println('\n' + "    KVITTO ");
    System.out.println("    Registreringsnummer: ");
    System.out.println("    Tid: ");
    System.out.println("    (Datum:) " + '\n');
    Input.promptEnterKey();
  }
  
  private static void printReceipt_checkOut(double paid) {
    System.out.println('\n' + "    KVITTO ");
    System.out.println("    (Datum:) ");
    System.out.println("    Parkerad tid: ");
    System.out.println("    Betalt: " + paid);
    System.out.println("    Utpasseringskod: " + '\n');
    Input.promptEnterKey();
  }
  
}