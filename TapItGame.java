package tapItGame;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * The TapItGame asks the user to tap a randomly chosen letter from the
 * alphabet.
 * 
 * The game starts by waiting 3 seconds for the player's 'letter tap' response.
 * 
 * If the player taps the correct letter on the keyboard, their score increases
 * by 1.
 * 
 * Additionally, after each successful letter tap by the player, the game wait
 * response time gets shorter. For example, if the user taps the correct letter
 * the game response time is reduced by 0.25 seconds.
 * 
 * A player can win by getting a score of 5.
 * 
 * A player can lose by tapping an incorrect key or running out of time.
 * 
 * Note: both upper and lower case letters are correct.
 * 
 * @author AMazzie
 *
 */
public class TapItGame {

	/**
	 * Holds the players score
	 */
	private static int playerScore;

	/**
	 * Starts at 3.0 seconds.
	 * 
	 * With each successful letter tap, decreases by 0.25 seconds.
	 * 
	 * Causes the game to increase in difficulty
	 */
	private static double gameResponseTime;

	/**
	 * Holds the button the user tapped for comparing.
	 */
	protected char userButtonTapped;

	/**
	 * The difficulty of the game represented by an integer.
	 * 
	 * This number will cause the timer to increase or decrease at higher (lower)
	 * levels, ultimately making the game harder (easer).
	 * 
	 * A difficulty of 0.25 is considered "easy" and is used by default.
	 */
	private static double difficulty;

	/**
	 * Holds the randomly chosen letter by game.
	 */
	private char randomGameLetter;

	/**
	 * Winning score set to 10 by default.
	 */
	private static int winningScore;

	/**
	 * Constructs one instance of a TapItGame.
	 * 
	 * The score starts at 0.
	 * 
	 * The game response timer starts at 3 seconds.
	 */
	public TapItGame() {
		gameResponseTime = 3.0;
		playerScore = 0;
		winningScore = 5;
		difficulty = 0.25;
	}

	/**
	 * Prints a simple game menu to start the game
	 */
	public static void printGameMenu() {
		System.out.println("*************");
		System.out.println("*************");
		System.out.println("-- TapIt! --");
		System.out.println("*************");
		System.out.println("*************");
		System.out.println("Anthony Mazzie, 2020");
		System.out.println("---------------------------");
		System.out.println("Instructions:");
		System.out.println("1. A random letter appears on the screen.");
		System.out.println("2. Tap the corresponding letter on your keyboard before the time runs out.");
		System.out.println("3. Timer starts at " + gameResponseTime + " seconds and reduces each round.");
		System.out.println("4. Each successful letter tap will incease your score by 1.");
		System.out.println("5. If you tap an incorrect letter or your response time exceeds the timer, you lose.");
		System.out.println("6. A score of " + winningScore + " wins the game, good luck!");
		System.out.println("---------------------------");
		System.out.println("Optional: select difficulty?: (Y/N)");
		Scanner userInputScanner = new Scanner(System.in);
		char userDifficultyInput = userInputScanner.next().charAt(0);
		
		if(userDifficultyInput == 'Y' || userDifficultyInput == 'y') {
			System.out.println("Easy - 1");
			System.out.println("Medium - 2");
			System.out.println("Hard - 3");
			
			System.out.print("Enter desired difficulty: ");
			int userDifficultyChoice = userInputScanner.nextInt();
			difficultySelector(userDifficultyChoice);
		}
		
		System.out.println("---------------------------");
		System.out.println("Press Any Button to Begin!");
		System.out.println("---------------------------");
		

		if (userInputScanner.hasNext()) {
			System.out.println("Game started!");
		}
	}

	/**
	 * Generates a random letter from the alphabet.
	 * 
	 * Note: upper/lower case is irrelevant to this game.
	 * 
	 */
	public void getRandomLetter() {
		Random randomObj = new Random();
		randomGameLetter = (char) ('a' + randomObj.nextInt(26));
		System.out.println("Random letter generated is ->: " + randomGameLetter);
	}
	
	/**
	 * Sets the game difficulty to easy, medium or hard.
	 * 
	 * Difficulties explained:
	 * Each round the user is asked to tap a letter in a time limit.
	 * This time limit is determined by the difficulty.
	 * 
	 * Easy mode reduces the time limit by 0.25 seconds each round.
	 * Easy mode reduces the time limit by 0.5 seconds each round.
	 * Easy mode reduces the time limit by 0.75 seconds each round.
	 * 
	 * @param userDifficultyChoice
	 */
	public static void difficultySelector(int userDifficultyChoice) {
		if(userDifficultyChoice == 1) {
			System.out.println("Difficulty set to easy.");
			difficulty = 0.25;
		}
		else if(userDifficultyChoice == 2) {
			System.out.println("Difficulty set to medium.");
			difficulty = 0.5;
		}
		else if(userDifficultyChoice == 3) {
			System.out.println("Difficulty set to hard.");
			difficulty = 0.75;
		}
	}

	/**
	 * Check to see if the randomly produced letter matches the user's input letter
	 * 
	 * @return
	 */
	private static boolean userInputChecker(char randomGameLetter, char userButtonTapped) {
		boolean wordsMatch = false;

		if (randomGameLetter == userButtonTapped) {
			wordsMatch = true;
		}

		return wordsMatch;
	}

	/**
	 * Gets the user's input each round.
	 * 
	 * Helper method, called in the run() method.
	 */
	private void getUserInput() {
		System.out.print("Tap the letter!: ");
		Scanner gameScan = new Scanner(System.in);
		userButtonTapped = gameScan.next().charAt(0);
	}

	/**
	 * Runs the TapItGame.
	 */
	protected void run() {
		printGameMenu();

		int timeDifference = 0;

		while (true) {

			getRandomLetter();
			Date roundStartTime = new Date(); // Starting round timer
			System.out.println("Timer starting, you have " + gameResponseTime + " seconds!");
			getUserInput();
			Date roundEndTime = new Date(); // Ending round timer
			timeDifference = (int) ((roundEndTime.getTime() - roundStartTime.getTime()) / 1000); //user response time

			if (timeDifference > gameResponseTime) {
				System.out.println("User response time: " + timeDifference);
				System.out.println("Game response time: " + gameResponseTime);
				System.out.println("You took too long, game over!");
				break;
			}

			if (!userInputChecker(randomGameLetter, userButtonTapped)) {
				System.out.println("Letters do not match, you lose!");
				break;
			} else if (userInputChecker(randomGameLetter, userButtonTapped)) {
				playerScore = playerScore + 1;
				if (playerScore >= 5) {
					System.out.println("You win!");
					break;
				}
				gameResponseTime = gameResponseTime - difficulty;
				System.out.println("Score: " + playerScore);
			}
		}
		System.out.println("Final score: " + playerScore);
	}
}