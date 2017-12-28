
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel {
    public MarkovFour() {
        myRandom = new Random();
        order = 4;
    }
    
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int idx = myRandom.nextInt(myText.length() - 4);
		String key = myText.substring(idx, idx + 4);
		sb.append(key);
		for(int k = 0; k < numChars - 4; k++){
		    ArrayList<String> follows = getFollows(key);
		    //System.out.println("key " + key + " " + follows);
		    if (follows.size() == 0) {
		        break;
		    }
		    idx = myRandom.nextInt(follows.size());
		    String follower = follows.get(idx);
		    sb.append(follower);
		    key = key.substring(1) + follower;
		}		
		return sb.toString();
	}
}
