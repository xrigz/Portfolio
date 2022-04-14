import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangmann {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("words.txt"));
		Scanner keyboard = new Scanner(System.in);

		List<String> words = new ArrayList<>();
		List<Character> playerGuesses = new ArrayList<>();

		while (scanner.hasNext()) {
			words.add(scanner.nextLine());
		}

		Random rand = new Random();
		String word = words.get(rand.nextInt(words.size()));

		//System.out.println(word);

		int wrongCount = 0;

		while (true) {
			printHangedMan(wrongCount);
			
			if (wrongCount >= 6) {
				System.out.println("You lose!");
				System.out.println("Word was: " + word);
				break;
			}
			System.out.println("\n\n");
			printWordState(word, playerGuesses);
			if(!getPlayerGuesses(keyboard, word, playerGuesses)) {
				wrongCount++;
			}

			if (printWordState(word, playerGuesses)) {
				System.out.println("You Win!");
				break;
			}
			System.out.println("Please enter your guess for the word: ");
			if (keyboard.nextLine().equals(word)) {
				System.out.println("You Win!");
				break;
			} else {
				System.out.println("Nope, try again");
			}

		}

	}

	private static void printHangedMan(int wrongCount) {
		System.out.println(" -------");
		System.out.println(" |     |");
		if (wrongCount >= 1) {
			System.out.println(" o");
		}

		if (wrongCount >= 2) {
			System.out.print("/ ");
			if (wrongCount >= 3) {
				System.out.println(" \\");
			} else {
				System.out.println();
			}
		}
		if (wrongCount >= 4) {
			System.out.println(" |");
		}
		if (wrongCount >= 5) {
			System.out.print("/ ");
			if (wrongCount >= 6) {
				System.out.println("\\");
			} else {
				System.out.println();
			}
		}
	}

	private static boolean getPlayerGuesses(Scanner keyboard, String word, List<Character> playerGuesses) {
		System.out.println("Please enter a letter: ");
		String letterGuess = keyboard.nextLine();
		playerGuesses.add(letterGuess.charAt(0));
		
		return word.contains(letterGuess);

	}

	private static boolean printWordState(String word, List<Character> playerGuesses) {
		int correctCount = 0;
		for (int i = 0; i < word.length(); i++) {
			if (playerGuesses.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
				correctCount++;
			} else {
				System.out.print("-");
			}
		}
		System.out.println();

		return (word.length() == correctCount);
	}
}
