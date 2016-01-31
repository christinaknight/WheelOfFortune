import java.util.ArrayList;

public class BoardObj {

	private char[] solution;
	private char[] shownSol;
	private int numLetters;
	private int guessedLetters;
	private ArrayList<Character> guessed = new ArrayList<Character>();
	private char[] letters;
	
	public BoardObj()
	{
		numLetters = 26;
		guessedLetters = 0;
		char[] tempLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		letters = tempLetters;
	}
	
	public void addPhrase(String phrase)
	{
		//puts the string into an array with easily accessible letters
		int length = phrase.length();
		solution = new char[length];
		shownSol = new char[length];
		for(int i = 0; i < phrase.length(); i++)
		{
			char letter = phrase.charAt(i);
			solution [i] = letter;
			if (solution[i] == ' ')
				shownSol[i] = ' ';
			else
				shownSol [i] = '_';

		}
		//System.out.print("Test Solution: ");
		//printSolution(solution);
		System.out.println("");
	}
	
	public void printSolution()
	{
		for (int i = 0; i < solution.length; i++)
		{
			System.out.print(solution[i]);
		}
	}
	
	public boolean guessLetter(char letter) 
	{
		guessedLetters++;
		numLetters--;
		boolean found = false;
		for (int i = 0; i < solution.length; i++)
		{
			if(solution[i] == letter)
			{
				shownSol[i] = letter;
				found = true;
			}
		}
	    for (int u=0; u<solution.length; u++)
	    {
	    	if (shownSol[u] == letter)
	    	{
	    		System.out.print(letter);
	    		shownSol[u] = letter;
	    	}
	    	else
	    		System.out.print(shownSol[u]);
	    }
	    System.out.println("");
	    return found;
	}
	
	public void printLetters(char letter)
	{
		guessed.add(letter);
		char[] tempLetters = new char[26];
	    for (int u=0; u<letters.length; u++)
	    {
	    	if (letters[u] != letter)
	    	{
	    		tempLetters[u] = letters[u];	
	    	}
	    }
	    letters = tempLetters.clone();
	    System.out.println("");
	    System.out.print("Letters Guessed: ");
	    for (int i = 0; i < guessed.size(); i++)
	    {
	    	System.out.print(guessed.get(i));
	    	System.out.print("");
	    }
	    System.out.println("");

	    }

	public boolean checkValidLetter(char letter)
	{
		boolean works = false;
		for (int i = 0; i < letters.length; i++)
		{
			if (letters[i] == letter)
				works = true;
		}
		return works;
	}
	public boolean guessPuzzle(String word)
	{
		boolean found = false;
		int counter = 0;
		if (word.length() == solution.length)
		{
			for (int i = 0; i < solution.length; i++)
			{
				if(solution[i] != word.charAt(i))
				{
					found = false;
					counter++;
				}
				if(counter == 0)
					found = true;
			}
		}
		return found;

	}

	public void printShown()
	{
		for (int i = 0; i < shownSol.length; i++)
		{
			System.out.print(shownSol[i]);
		}
		System.out.println("");
	}
}
