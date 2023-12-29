package Assignment3;
import java.util.Random;
import java.util.Scanner;

import java.util.Scanner;
import java.util.Scanner;
import java.util.Scanner;
import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        System.out.println("Ladder and Snake Game!");

        int attempts = 0;
        int numPlayers;

        // enter number of players (between 2 and 4)
        do {
            try {
                System.out.print("Enter the number of players (2-4): ");
                numPlayers = kb.nextInt();
                attempts++;

                if (numPlayers < 2 || numPlayers > 4) {
                    System.out.println("Invalid number of players. Please enter a value between 2 and 4.");
                }

                if (attempts == 10) {
                    System.out.println("Exceeded maximum attempts. Game terminated.");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                kb.nextLine(); // invalid input
                numPlayers = 0; // Set numPlayers to 0 to repeat the loop
            }
        } while (numPlayers < 2 || numPlayers > 4);

        // Create a LadderAndSnake object 
        new LadderAndSnake(numPlayers);

        //  closing message
        System.out.println("Game is over... Thank you for playing, see ya later!");

        kb.close();
    }
}
