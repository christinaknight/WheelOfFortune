import java.util.Random;

public class SolutionObj {
	
	private String[] solutions;
	private Random  rand = new Random ( );
	
	
	public SolutionObj()
	{
		String[] tempSols = {"jazz", "buffalo", "later gator", "throwback thursday", "man crush monday", "lyrical", "contemporary", "in a while crocodile", "clairvoyant", "zenith", "hypochondriac", "liposuction", "sloven", "vainglorious", "paltry", "coi", "overweening", "juxtaposition", "cogitate", "crux", "dregs", "dogmatic", "axiom", "environs", "zany", "effrontery", "curry favor", "sycophant", "svelte", "cherubic", "obsequious", "cajolery", "attenuate"};
		solutions = tempSols;
	}

	public String getSecretPhrase()
	{
		//randomly chooses a phrase to use
		int randomSpin = (rand.nextInt(30)); 
		String result = solutions[randomSpin];
		return result;
		
	}
}
