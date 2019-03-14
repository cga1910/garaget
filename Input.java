import java.util.Scanner;
import java.util.InputMismatchException;

public class Input {
  
  final static Scanner scan = new Scanner(System.in);
  
  // Tar emot en int från användaren och returnerar den
  public static int userInputInt() {
    try {
      int userInput = scan.nextInt();
      return userInput;
    } catch (InputMismatchException e) {
      System.out.println('\n' + "  Felaktigt inmatningsformat.");
      scan.next();
    }
    return -1;
  }

  // Tar emot en double från användaren och returnerar den
  public static double userInputDouble() {
    try {
      double userInput = scan.nextDouble();
      return userInput;
    } catch (InputMismatchException e) {
      System.out.println('\n' + "  Felaktigt inmatningsformat.");
      scan.next();
    }
    return -1;
  }
  
  // Tar emot en String från användaren och returnerar den
  public static String userInputString() {
    Scanner scan2 = new Scanner(System.in);
    String userInput;
    while ((userInput = scan2.nextLine()).length() > 0) { // Tar först emot en sträng, kollar sedan längden
      return userInput; // Returnera strängen om den är större än 0 tecken
    }
    return "-1"; // Annars returnera "-1" (t.ex. om användaren bara trycker Enter)
  }

  // Metod för att ta emot ett registreringsnummer
  public static String userInputRegNr() {
    System.out.print('\n' + "  Ange registreringsnummer: ");
    String userInput = userInputString();
    userInput = userInput.toUpperCase(); // Konvertera till stora bokstäver
    return userInput;
  }

  // Kontrollerar registreringsnumret och returnerar TRUE om formatet är korrekt
  public static boolean checkRegNrFormat(String regNr) {
    boolean pass = false;
    char c;
    if (regNr.length() == 6) {
      try {
        c = regNr.charAt(0); boolean testChar1 = checkChar(c, 1);
        c = regNr.charAt(1); boolean testChar2 = checkChar(c, 1);
        c = regNr.charAt(2); boolean testChar3 = checkChar(c, 1);
        c = regNr.charAt(3); boolean testChar4 = checkChar(c, 2);
        c = regNr.charAt(4); boolean testChar5 = checkChar(c, 2);
        c = regNr.charAt(5); boolean testChar6 = checkChar(c, 2);
        if (testChar1 && testChar2 && testChar3 && testChar4 && testChar5 && testChar6) {
          pass = true;
          return pass;
        }
      } catch (Exception e) {
        System.out.println('\n' + "  Felaktigt format. Använd formatet ABC123." + '\n');
        promptEnterKey();
      }
    }
    System.out.println('\n' + "  Felaktigt format. Använd formatet ABC123." + '\n');
    promptEnterKey();
    return pass;
  }

  // Testar ett tecken
  public static boolean checkChar(char c, int testPart) {
    boolean pass = false;
    String s = String.valueOf(c);
    if (testPart == 1) {
      pass = s.matches(".*[A-Z].*");
    }
    else if (testPart == 2) {
      pass = s.matches(".*[0-9].*");
    }
    return pass;
  }

  // Tryck på enter för att fortsätta
  public static void promptEnterKey() {
    System.out.print("  <Enter>");
    Scanner enter = new Scanner(System.in);
    enter.nextLine();
  }
  
}