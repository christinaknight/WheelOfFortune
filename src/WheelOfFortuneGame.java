import java.awt.Frame;
import java.util.Scanner;

public class WheelOfFortuneGame {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) 
	{
		intro();
		WheelObj wheel = new WheelObj();
		BoardObj board = new BoardObj();
		ScoreCard p1 = new ScoreCard();
		ScoreCard p2 = new ScoreCard();
		SolutionObj solution = new SolutionObj();
		String userchoice = "";
		do {
			printMenu();
			String userChoice = getChoice(); //return a valid option
			if (userChoice.equalsIgnoreCase("O"))
			{
				int type = 1;
				playOneRound(p1, p2, wheel, board, solution, type);
			}
			if (userChoice.equalsIgnoreCase("S"))
				playSeries(p1, p2, wheel, board, solution);
			if (userChoice.equalsIgnoreCase("I"))
				printInstructions();
		}
		while (!userchoice.equalsIgnoreCase("Q"));
		endStatements();

	}

	public static void intro()
	{
		System.out.println("Welcome goes here");
	}

	public static void printMenu()
	{
		System.out.println("Enter your choice:");
		System.out.println("O : play one round");
		System.out.println("S : play a series");
		System.out.println("I : print instructions");
		System.out.println("Q : quit game");
	}

	public static String getChoice()
	//post: returns valid choice
	{
		String choice;
		int counter = 0;
		do{
			choice = sc.next();
			if (choice.equalsIgnoreCase("O") || choice.equalsIgnoreCase("S") || choice.equalsIgnoreCase("I"));
			{
				counter++;
			}
			//else - WHY DOES THIS NOT WORK?????????
			//{
			//System.out.println("Oops! That isn't a valid choice.");
			//System.out.println("Please re enter a valid option");
			//}
		}while(counter == 0);
		System.out.println("");
		return choice;
	}

	public static void printInstructions()
	{
		System.out.println("How to play Wheel of Fortune:");
		System.out.println("Each player begins with $250. Player 1 goes first. He/she");
		System.out.println(" chooses whether to Spin the Wheel, Buy a Vowel, or Guess the Puzzle.");
		System.out.println("Spinning the wheel, he/she either lands on Bankrupt (lose all money)");
		System.out.println(", Lose Turn, or a money value. Then, that player can choose a constenant.");
		System.out.println("If the letter chosen is correct, they recieve that money value and get to go again.");
		System.out.println("If not, they do not recieve any money and it is the other player's turn.");
		System.out.println("To buy a vowel, the player chooses a vowel to purchase. Regardless of whether it is ");
		System.out.println("located in the puzzle, the player must pay $250."); //is this right?
		System.out.println("The game continues until one player guesses the board correctly. That player is the winner!");
	}

	public static void endStatements()
	{
		System.out.println("Thank you for Playing Wheel of Fortune!");
	}

	public static void playSeries(ScoreCard p1, ScoreCard p2, WheelObj wheel, BoardObj board, SolutionObj solution)
	{
		int type = 0;
		System.out.println("Series Round!");
		System.out.println("You are playing the first player to win three games.");
		System.out.println("The player to correctly guess the puzzle in more games wins!");
		setNames(p1, p2);
		int score1 = 0;
		int score2 = 0;
		do{
			int game = playOneRound(p1, p2, wheel, board, solution, type);
			if (game == 1)
				score1++;
			if (game == 2)
				score2++;
		}while(score1 < 3 && score2 < 3);
		System.out.println("");
		System.out.println("Final score P1 : " + score1);
		System.out.println("Final score P2 : " + score2);
		System.out.println("");
		if (score1 > score2)
			System.out.println("Congratulations, " + p1.getName() + " you won the series!");
		if (score2 > score1)
			System.out.println("Congratulations, " + p2.getName() + " you won the series!");
		System.out.println("");

	}
	public static int playOneRound(ScoreCard p1, ScoreCard p2, WheelObj wheel, BoardObj board, SolutionObj solution, int type)
	//post: plays one round of WOF
	{
		if (type == 1)
			setNames(p1, p2);
		int counter = 0; //keeps track of whose turn it is
		boolean guess1 = false; //sets the guess to false
		boolean guess2 = false;
		int winner = 0;
		String secretPhrase = solution.getSecretPhrase(); //picks phrase at random
		board.addPhrase(secretPhrase); //adds secret phrase to board
		do { //whole round
			if(counter==1)
			{
				guess2 = turn(p2, wheel, board); //person 2 turn
				counter =0;
			}

			if(counter == 0) //person 1 turn
			{
				guess1 = turn(p1, wheel, board);
				System.out.println(guess1);
				counter++;
			}
		} while(guess1 == false && guess2 == false);
		if (guess1 == true)
		{
			winner = 1;
			System.out.println("Congratulations! " + p1.getName() + " you won this game!");
		}
		if (guess2 == true)
		{
			winner = 2;
			System.out.println("Congratulations! " + p2.getName() + " you won this game!");
		}

		return winner;

	}


	public static boolean turn(ScoreCard p, WheelObj wheel, BoardObj board)
	{
		//System.out.println(p.getMoney());
		boolean turnChange = false;
		boolean correctGuess = false;
		int spinValue = 0;
		System.out.println("It is now " + p.getName() +"'s turn!");
		do{//each turn
			board.printSolution();
			System.out.println("");
			board.printShown();
			String choice = chooseOption();
			if (choice.equalsIgnoreCase("W"))
			{
				String spun = wheel.spin(); //spins wheel if choice is spin - does actions within wheel
				if (spun.equalsIgnoreCase("Bankrupt"))
				{
					System.out.println("Oh no, you're bankrupt!");
					p.bankrupt();
					turnChange = true; //switches person
				}
				else if(spun.equalsIgnoreCase("Lose Turn"))
				{
					System.out.println("Aww Sorry, you lost your turn!");
					System.out.println("Better luck next time!");
					turnChange = true; //switches person
				}
				else
				{
					boolean didRight = false;
					do{
						System.out.println("You spun: $" + spun);
						System.out.println("If you guess a correct letter, you get the money value!");
						spinValue = Integer.parseInt(spun);
						char cLetter = chooseConst();
						boolean use = board.checkValidLetter(cLetter);
						boolean conGuessed;
						if (use == true)
						{
							conGuessed = board.guessLetter(cLetter);
							if (conGuessed == false)
								turnChange = true;
							if(conGuessed == true)
								p.addMoney(spinValue);
							didRight = true;
							board.printLetters(cLetter);
						}
						else
						{
							System.out.println("You already guessed that letter!");
							System.out.println("Please guess again!");
						}

					}while(didRight == false);
				}

			}
			if (choice.equalsIgnoreCase("B"))
			{
				char vLetter = buyVowel(p, board);
				boolean vowelGuessed = board.guessLetter(vLetter);
				if (vowelGuessed == false)
					turnChange = true;
				board.printLetters(vLetter);
			}
			if (choice.equalsIgnoreCase("G"))
			{
				boolean guess = guessThePuzzle(board); //if true will end loop
				if (guess == false) //if guessed wrong - ends turn but round still continues
					turnChange = true;
				if (guess == true)
				{
					turnChange = true;
					correctGuess = true;
				}


			}
			System.out.println(p.getName() + " has $" + p.getMoney() + " left.");
		} while(turnChange == false);

		return correctGuess;
	}

	public static String chooseOption()
	{
		String choice;
		do{
			System.out.println("Options:");
			System.out.println("W : Spin the wheel");
			System.out.println("B : Buy a vowel");
			System.out.println("G : Guess the puzzle");
			choice = sc.next();
		}while (!choice.equalsIgnoreCase("W") && !choice.equalsIgnoreCase("B") && !choice.equalsIgnoreCase("G"));
		return choice;

	}

	public static void setNames(ScoreCard p1, ScoreCard p2)
	{
		System.out.println("Player 1 enter your name.");
		String name1 = sc.next();
		p1.setName(name1);
		System.out.println("Player 2 enter your name.");
		String name2 = sc.next();
		p2.setName(name2);
	}

	public static char chooseConst()
	{
		//choose constenant to use
		System.out.println("Choose a constenant to guess:");
		String letter = sc.next();  //need to add to check if real const
		char l = letter.charAt(0);
		return l;

	}

	public static char buyVowel(ScoreCard p, BoardObj board)
	{
		int counter = 0;
		char v;
		do {
			System.out.println("Choose a vowel to buy");
			String vowel = sc.next();//converting from char to int 
			v = vowel.charAt(0);
			if (v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u')
			{

				p.buyVowel(v, board);
				counter = 1;
			}
			else
			{
				System.out.println("You typed in a non - vowel. You can only buy vowels.");
				System.out.println("Please try again.");
			}
		} while(counter != 1);
		return v;

	}


	public static boolean guessThePuzzle(BoardObj board)
	{
		System.out.println("Enter your guess for the puzzle");
		sc.nextLine();
		String guess = sc.nextLine();
		System.out.println(guess);
		boolean right = board.guessPuzzle(guess);
		return right;
	}
}


