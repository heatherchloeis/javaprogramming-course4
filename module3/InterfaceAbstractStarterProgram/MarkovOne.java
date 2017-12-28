
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {
    public MarkovOne() {
        myRandom = new Random();
        order = 1;
    }
    
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int idx = myRandom.nextInt(myText.length() - 1);
		String key = myText.substring(idx, idx + 1);
		sb.append(key);
		for(int k = 0; k < numChars - 1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
		    idx = myRandom.nextInt(follows.size());
		    String follower = follows.get(idx);
		    sb.append(follower);
		    key = follower;
		}		
		return sb.toString();
	}
}
