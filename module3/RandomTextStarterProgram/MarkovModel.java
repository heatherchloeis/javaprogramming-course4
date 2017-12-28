
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.Random;

public class MarkovModel {
    private int keyLength;
    private String myText;
	private Random myRandom;
	
	public MarkovModel(int n) {
	    keyLength = n;
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int idx = myRandom.nextInt(myText.length() - keyLength);
		String key = myText.substring(idx, idx + keyLength);
		sb.append(key);
		for(int k = 0; k < numChars - keyLength; k++){
		    ArrayList<String> follows = getFollows(key);
		    //System.out.println("key " + key + " " + follows);
		    if (follows.size() == 0) {
		        break;
		    }
		    idx = myRandom.nextInt(follows.size());
		    String follower = follows.get(idx);
		    sb.append(follower);
		    key = key.substring(key.length() - (keyLength - 1)) + follower;
		}		
		return sb.toString();
	}
	
	public ArrayList<String> getFollows(String key) {
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