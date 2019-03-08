public class Admin {
  
    public static void menu() {
    System.out.print("Lösenord: ");
    String pwd = Input.userInputString();
    boolean looping = true;
    while (looping) {
      System.out.println();
      System.out.println("  ------------ ADMIN ------------");
      System.out.println("  Aktuell tid: ");
      System.out.println("  Prisinfo: ");
      System.out.println("  Fyllnadsgrad: ");
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
      if (userInput == 1) {
        setTime();
      } else if (userInput == 2) {
        removeVehicle();
      } else if (userInput == 3) {
        statusMenu();
      } else if (userInput == 4) {
        setPricing();
      } else if (userInput == 5) {
        logsAndStatsMenu();
      } else if (userInput == 6) {
        looping = false;
      }
    }
  }
  
  private static void setTime() {
    System.out.print('\n' + "  Ange tid: ");
    int time = Input.userInputInt();
  }
  
  private static void removeVehicle() {
    String regNr = Input.userInputRegNr();
    boolean befintligt = false;
    // TODO: Kontrollera här om registreringsnumret finns
    if (befintligt) {
      System.out.println("  Fordonet med registreringsnummer " + regNr + " togs bort ur listan.");
    } else {
      System.out.println("  Ogiltigt registreringsnummer.");
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
      if (userInput == 1) {
        printParked();
      } else if (userInput == 2) {
        printOverdue();
      } else if (userInput == 3) {
        looping = false;
      }
    }    
  }

  private static void printParked() {
    System.out.print('\n' + "  Incheckade fordon: " + '\n');
    Input.promptEnterKey();
  }
  private static void printOverdue() {
    System.out.print('\n' + "  Övertrasserade fordon: " + '\n');
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
      if (userInput == 1) {
        setTimeUnit();
      } else if (userInput == 2) {
        setRate();
      } else if (userInput == 3) {
        looping = false;
      }
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
      if (userInput == 1) {
        printLog();
      } else if (userInput == 2) {
        printTotalParkings();
      } else if (userInput == 3) {
        printTotalIncome();
      } else if (userInput == 4) {
        printParkingTimeAverage();
      } else if (userInput == 5) {
        printParkingTimeMedian();
      } else if (userInput == 6) {
        looping = false;
      }
    }
  }

  private static void printLog() {
    System.out.print('\n' + "  Loggen visas..." + '\n');
    Input.promptEnterKey();
  }
  
  private static void printTotalParkings() {
    System.out.print('\n' + "  Totalt antal parkeringar: " + '\n'); // Eller "Antal parkeringar sedan systemstart"
    Input.promptEnterKey();
  }
  
  private static void printTotalIncome() {
    System.out.print('\n' + "  Totala intäkter: " + '\n');
    Input.promptEnterKey();
  }
  
  private static void printParkingTimeAverage() {
    System.out.print('\n' + "  Genomsnittlig parkeringstid: " + '\n');
    Input.promptEnterKey();
  }

  private static void printParkingTimeMedian() {
    System.out.print('\n' + "  Parkerad mediantid: " + '\n');
    Input.promptEnterKey();
  }
  
}