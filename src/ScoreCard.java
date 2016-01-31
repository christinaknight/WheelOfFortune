
public class ScoreCard {

private int money;
private String name;

	public ScoreCard()
	{
		money = 250; //or however much they get
	}
	
	public void setName(String Name)
	{
		name = Name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void buyVowel(char vowel, BoardObj board)
	{
		if (vowel == 'a' || vowel == 'e' || vowel == 'i' || vowel == 'o' || vowel == 'u')
		{
			
			money = money - 250;
		}
		else
		{
			System.out.println("You typed in a non - vowel. You can only buy vowels");
			System.out.println("Please try again.");
		}
	}
	
	public void bankrupt() //bankrupt
	{
		System.out.println("in bankrupt");
		money = 0;
	}
	
	public void addMoney(int reward)
	{
		money = money + reward;
	}
	
	public int getMoney()
	{
		return money;
	}
}
