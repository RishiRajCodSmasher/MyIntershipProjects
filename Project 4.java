import java.util.Scanner;

/**
 * Text Adventure Game
 * Objective: Develop a text-based adventure game in Java.
 * Description: An interactive game where users make choices leading to different outcomes.
 */
public class TextAdventureGame {

    /**
     * Main method where the game begins.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Text Adventure Game!");
        System.out.println("You find yourself in a mysterious situation.");

        while (true) {
            System.out.println("\nWhat will you do?");
            System.out.println("1. Open the door");
            System.out.println("2. Look through the window");
            System.out.println("3. Search the room");

            // Get user choice
            int choice = getUserChoice(scanner, 3);

            // Process user choice
            switch (choice) {
                case 1:
                    System.out.println("The door is locked. You need a key.");
                    break;

                case 2:
                    System.out.println("You see a beautiful garden outside.");
                    System.out.println("Do you want to go out?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");

                    // Get user choice for the garden scenario
                    int gardenChoice = getUserChoice(scanner, 2);

                    if (gardenChoice == 1) {
                        System.out.println("You enjoy the fresh air in the garden. You win!");
                        System.exit(0);
                    } else {
                        System.out.println("You stay inside the room.");
                    }
                    break;

                case 3:
                    System.out.println("You find a hidden key under the bed.");
                    System.out.println("What will you do?");
                    System.out.println("1. Take the key");
                    System.out.println("2. Leave it");

                    // Get user choice for the key scenario
                    int keyChoice = getUserChoice(scanner, 2);

                    if (keyChoice == 1) {
                        System.out.println("You now have the key. You can try opening the door.");
                    } else {
                        System.out.println("You decide to leave the key behind.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    /**
     * Gets the user's choice with input validation.
     *
     * @param scanner   Scanner object for user input
     * @param maxChoice Maximum valid choice
     * @return User's choice
     */
    private static int getUserChoice(Scanner scanner, int maxChoice) {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Enter your choice (1-" + maxChoice + "): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= maxChoice) {
                    isValidInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + maxChoice + ".");
                }
            } else {
                scanner.next(); // Consume invalid input
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return choice;
    }
}
