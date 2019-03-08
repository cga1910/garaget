public class Main {
  
  public static void main(String[] args) {
    try {
      mainMenu();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void mainMenu() {
    while (true) {
      System.out.println('\n' + "1. Kund" + '\n' + "2. Admin");
      int userInput = Input.userInputInt();
      if (userInput == 1) {
        Customer.menu();
      } else if (userInput == 2) {
        Admin.menu();
      }
    }
  }
  
}