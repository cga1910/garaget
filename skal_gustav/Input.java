import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;

public class Input {
  
  final static Scanner scan = new Scanner(System.in);
  
  // Tar emot en int från användaren och returnerar den
  public static int userInputInt() {
    try {
      int userInput = scan.nextInt();
      return userInput;
    } catch (InputMismatchException e) {
      System.out.println("Felaktigt inmatningsformat.");
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
      System.out.println("Felaktigt inmatningsformat.");
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

  // En separat metod för inmatning av registreringsnummer (är inte säker på att det behövs dock)
  public static String userInputRegNr() {
    System.out.print('\n' + "  Ange registreringsnummer: ");
    String userInput = userInputString();
    return userInput;
  }

  // Tryck på enter för att fortsätta
  public static void promptEnterKey() {
    System.out.print("  <Enter>");
    Scanner enter = new Scanner(System.in);
    enter.nextLine();
  }
  
}