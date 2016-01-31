import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class WOFGraphics {

	 Scanner sc = new Scanner(System.in);
	
	private  JFrame f;
	private  JPanel p1;
	private  JPanel Round;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JLabel lab;
	private ImageIcon image;
	
	public WOFGraphics()
	{
		gui();
		mainpart();
	}
	
	public void gui()
	{
		   Font arialBolditalic12 = new Font("Arial", Font.BOLD, 25);
		      
		f = new JFrame("Test Frame");
		f.setVisible(true);
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p1 = new JPanel(new GridBagLayout());
		p1.setBackground(Color.pink);
		
		GridBagConstraints c = new GridBagConstraints();
		
		b1 = new JButton("One Round");
		b2 = new JButton("Series");
		b3 = new JButton("Instructions");
		lab = new JLabel("Wheel of Fortune");
		image = new ImageIcon("wheel.png");

		
		//image("wheel.png");
		
			
		
	      lab.setFont(arialBolditalic12);
		
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 1;
		p1.add(b1, c);
		c.gridx = 1;
		c.gridy = 1;
		p1.add(b2, c);
		c.gridx = 2;
		c.gridy = 1;
		p1.add(b3, c);
		c.gridx = 0;
		c.gridy = -2;
		p1.add(lab);


		
		f.add(p1);
		
		
	
	}
	public void image(String image){
		JLabel label = new JLabel(new ImageIcon(image));
		JFrame img_f = new JFrame("Welcome");
		img_f.getContentPane().add(label);
		img_f.pack();
		img_f.setVisible(true);
	}
	public void dialog(String contents){
		//TODO Write pre-post
		JOptionPane.showMessageDialog(f, contents);
	}
	public String input_dialog(String prompt){
		//TODO Write pre-post
		return JOptionPane.showInputDialog(prompt);
	}
	public void mainpart()
	{
		//dialog("<html><!-- HTML Codes by Quackit.com --> <span style=\"font-family:Cursive;font-size:22px;font-style:italic;font-weight:normal;text-decoration:none;text-transform:none;color:9900FF;\">Text 123</span>");
		//System.out.println(input_dialog("<html><h1>Input</h1></html"));
		//intro();
		WheelObj wheel = new WheelObj();
		BoardObj board = new BoardObj();
		ScoreCard p1 = new ScoreCard();
		ScoreCard p2 = new ScoreCard();
		SolutionObj solution = new SolutionObj();

		//do {
		b1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				//System.out.println("one round");
				dialog("Play One Round");
				System.out.println("Git ere");
				JFrame oneRound = new JFrame("One Round");
				oneRound.setVisible(true);
				oneRound.setSize(600, 400);
				oneRound.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JPanel panel2 = new JPanel(new GridBagLayout());
				panel2.setBackground(Color.blue);
				oneRound.add(panel2);
				playOneRound(p1, p2, wheel, board, solution, oneRound);
			}
			
		});
		
		b2.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Play series");
				dialog("Play Series");
				//playSeries(p1, p2, wheel, board, solution);
			}
			
		});
		
		b3.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("print instructions");
				dialog("printinstructions");
			}
			
		});
		//}
		//while (!userchoice.equals("Q"));
		//endStatements();
	}
	public  void playSeries(ScoreCard p1, ScoreCard p2, WheelObj wheel, BoardObj board, SolutionObj solution)
	{
		dialog("/n Series Round!"
				+ "/n You are playing the first player to win three games."
				+ "/n The player to correctly guess the puzzle in more games wins!");
		System.out.println("You are playing the first player to win three games.");
		System.out.println("The player to correctly guess the puzzle in more games wins!");
		int score1 = 0;
		int score2 = 0;
		do{
			int game = playOneRound(p1, p2, wheel, board, solution);
			if (game == 1)
				score1++;
			if (game == 2)
				score2++;
		}while(score1 < 3 || score2 < 3);
	}
	public  int playOneRound(ScoreCard p1, ScoreCard p2, WheelObj wheel, BoardObj board, SolutionObj solution, JFrame frame)
	//post: plays one round of WOF
	{
		setNames(p1, p2);
		JLabel lab1 = new JLabel("Player 1: " + p1.getName() + "Money: " + p1.getMoney());
		JLabel lab2 = new JLabel("Player 1: " + p2.getName() + "Money: " + p2.getMoney());
		frame.add(lab1);
		frame.add(lab2);
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
		} while(guess1 == false || guess2 == false); //DOES NOT WORK WHY??????
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
	public  boolean turn(ScoreCard p, WheelObj wheel, BoardObj board)
	{
		boolean turnChange = false;
		boolean correctGuess = false;
		int spinValue = 0;
		JLabel turn = new JLabel("It is now " + p.getName() +"'s turn!");
		p1.add(turn);
		f.add(p1);
		System.out.println("It is now " + p.getName() +"'s turn!");
		do{//each turn
			String choice = chooseOption();
			if (choice.equals("W"))
			{
				String spun = wheel.spin(); //spins wheel if choice is spin - does actions within wheel
				System.out.println("You spun: " + spun);
				if (spun.equals("Bankrupt"))
				{
					p.bankrupt();
					turnChange = true; //switches person
				}
				if (spun.equals("Lose Turn"))
					turnChange = true; //switches person
				else
				{
					spinValue = Integer.parseInt(spun);
					char cLetter = chooseConst();
					boolean conGuessed = board.guessLetter(cLetter);
					if(conGuessed == false)
						turnChange = true;
					if(conGuessed == true)
						p.addMoney(spinValue); 
				}
			}
			if (choice.equals("B"))
			{
				char vLetter = buyVowel(p, board);
				boolean vowelGuessed = board.guessLetter(vLetter);
				if (vowelGuessed == false)
					turnChange = true;
			}
			if (choice.equals("G"))
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
		} while(turnChange == false);
		System.out.println(p.getName() + " has $" + p.getMoney() + "left.");
		return correctGuess;
	}

	public  String chooseOption()
	{
		String choice = input_dialog("\n");
				/*"Options:" );
		System.out.println("W : Spin the wheel");
		System.out.println("B : Buy a vowel");
		System.out.println("G : Guess the puzzle");
		String choice = sc.next();*/
		return choice;

	}
	
	public  void setNames(ScoreCard p1, ScoreCard p2)
	{
		System.out.println("Player 1 enter your name.");
		String name1 = sc.next();
		p1.setName(name1);
		System.out.println("Player 2 enter your name.");
		String name2 = sc.next();
		p2.setName(name2);
	}

	public  char chooseConst()
	{
		//choose constenant to use
		System.out.println("Choose a constenant to guess:");
		String letter = sc.next();  //need to add to check if real const
		char l = letter.charAt(0);
		return l;
		
	}
	
	public  char buyVowel(ScoreCard p, BoardObj board)
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
	
	
	public  boolean guessThePuzzle(BoardObj board)
	{
		System.out.println("Enter your guess for the puzzle");
		sc.nextLine();
		String guess = sc.nextLine();
		System.out.println(guess);
		boolean right = board.guessPuzzle(guess);
		return right;
	}

	
	public static void main(String[] args) 
	{
		new WOFGraphics();
	}
}