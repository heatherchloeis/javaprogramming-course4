
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int order;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    @Override public String toString() {
        return "MarkovModel of order " + order;
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);
    	
	protected ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    while (true) {
	        int idx = myText.indexOf(key, pos);
	        int idxPlus = idx + key.length();
	        if (idx == -1 || idxPlus >= myText.length()) {
	            break;
	        }
	        String followers = myText.substring(idxPlus, idxPlus + 1);
	        follows.add(followers);
	        pos = idx + 1;
	    }
	    return follows;
	}
}
