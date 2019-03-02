public class Main {
  
	public static void main(String[] args) {
      mainMenu();
	}

  private static void mainMenu() {
    boolean looping = true;
    while (looping) {
      System.out.println('\n' + "1. Kund" + '\n' + "2. Admin");
      int userInput = Input.userInputInt();
      if (userInput == 1) {
        Customer.menu();
      } else if (userInput == 2) {
        Admin.menu();
      } else if (userInput == 0) {
        System.out.println("Avslutar...");
        looping = false;
      }
    }
  }
  
}