package Assignment3;
import java.util.Random;

public class LadderAndSnake {

	    private static final int BOARD_SIZE = 10;
	    private static final int WINNING_SQUARE = 100;

	    private int[][] board;
	    private int numPlayers;
	    private int[] playerPositions;
	    private Random random;

	    public LadderAndSnake(int numPlayers) {
	        if (numPlayers < 2 || numPlayers > 4) {
	            throw new IllegalArgumentException("Number of players must be between 2 and 4 inclusive.");
	        }

	        this.numPlayers = numPlayers;
	        this.board = initializeBoard();
	        this.playerPositions = new int[numPlayers];
	        this.random = new Random();

	        // Display welcome message
	        System.out.println("Welcome to the Ladder and Snake Game!");
	        System.out.println("Number of Players: " + numPlayers);

	        // Play the game
	        play();
	    }

	    private int[][] initializeBoard() {
	        // Initialize the board with ladder and snake positions as per Figure 1
	        int[][] initializedBoard = new int[BOARD_SIZE][BOARD_SIZE];

	        // Set ladder positions
	        initializedBoard[3][0] = 31;
	        initializedBoard[5][2] = 14;
	        initializedBoard[8][5] = 27;

	        // Set snake positions
	        initializedBoard[6][5] = 16;
	        initializedBoard[7][8] = 19;
	        initializedBoard[9][7] = 17;

	        return initializedBoard;
	    }

	    private int flipDice() {
	        // Return a random value between 1 and 6 inclusive
	        return random.nextInt(6) + 1;
	    }

	    private void play() {
	        // Display the starting message
	        System.out.println("Now deciding which player will start playing...");

	        // Determine the order of playing turns
	        determineOrder();

	        // Play the game
	        while (true) {
	            for (int i = 0; i < numPlayers; i++) {
	                int diceValue = flipDice();
	                int newPosition = playerPositions[i] + diceValue;

	                System.out.println("Player " + (i + 1) + " got a dice value of " + diceValue + "; now in square " + newPosition);

	                // Check for ladder or snake
	                if (newPosition < BOARD_SIZE * BOARD_SIZE) {
	                    int row = newPosition / BOARD_SIZE;
	                    int col = newPosition % BOARD_SIZE;

	                    if (board[row][col] != 0) {
	                        int ladderOrSnakeDestination = board[row][col];
	                        System.out.println("Player " + (i + 1) + " reached a ladder/snake; now in square " + ladderOrSnakeDestination);
	                        newPosition = ladderOrSnakeDestination;
	                    }
	                }

	                // Update player position
	                playerPositions[i] = newPosition;

	                // Check for game over
	                if (newPosition == WINNING_SQUARE) {
	                    System.out.println("Player " + (i + 1) + " wins!");
	                    return;
	                }
	            }
	        }
	    }

	    private void determineOrder() {
	        int[] diceValues = new int[numPlayers];

	        // Each player throws the dice to determine order
	        for (int i = 0; i < numPlayers; i++) {
	            diceValues[i] = flipDice();
	            System.out.println("Player " + (i + 1) + " got a dice value of " + diceValues[i]);
	        }

	        // Determine the order based on dice values
	        while (true) {
	            int maxDiceValue = 0;
	            int maxPlayerIndex = -1;
	            boolean tie = false;

	            for (int i = 0; i < numPlayers; i++) {
	                if (diceValues[i] > maxDiceValue) {
	                    maxDiceValue = diceValues[i];
	                    maxPlayerIndex = i;
	                    tie = false;
	                } else if (diceValues[i] == maxDiceValue) {
	                    tie = true;
	                }
	            }

	            if (!tie) {
	                System.out.println("Reached final decision on order of playing: Player " + (maxPlayerIndex + 1));
	                break;
	            } else {
	                System.out.println("A tie between players. Trying to break the tie.");
	                // Re-throw dice for tied players
	                for (int i = 0; i < numPlayers; i++) {
	                    if (diceValues[i] == maxDiceValue) {
	                        diceValues[i] = flipDice();
	                        System.out.println("Player " + (i + 1) + " got a dice value of " + diceValues[i]);
	                    }
	                }
	            }
	        }
	    }
	}
