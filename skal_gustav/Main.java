public class Main {
  
  static boolean loopingMain = true;
  
	public static void main(String[] args) {
    while (loopingMain) {
      mainMenu();
    }
	}

  private static void mainMenu() {
    System.out.println('\n' + "1. Kund" + '\n' + "2. Admin");
    int userInput = Input.userInputInt();
    if (userInput == 1) {
      Customer.menu();
    } else if (userInput == 2) {
      Admin.menu();
    } else if (userInput == 0) {
      System.out.println("Avslutar...");
      loopingMain = false;
    }
  }
  
}