import java.util.Random;

public class WheelObj {

	private String[] wheel;//should it be string or int?
	private Random  rand = new Random ( );
	
	
	public WheelObj()
	{
		String[] Tempwheel = {"100", "150", "200", "250", "300", "350", "400", "450", "500", "550", "600", "650", "700", "750", "800", "850", "900", "950", "1000", "Lose Turn", "Bankrupt"};
		wheel = Tempwheel;
	}
	
	public String spin() 		//chooses one answer at random
	{
		int randomSpin = (rand.nextInt(21)); //I think 30 - how many options?x
		String result = wheel[randomSpin];
		return result;

	}
}
