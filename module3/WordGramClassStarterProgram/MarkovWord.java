
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
		
	private int indexOf(String[] words, WordGram target, int start) {
	    for (int i = start; i < words.length - myOrder; i++) {
	        WordGram word = new WordGram(words, i, myOrder);
	        if (word.equals(target)) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
		WordGram key = new WordGram(myText, index, myOrder);
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords - myOrder; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = key.shiftAdd(next);
		}		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(WordGram kGram) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int idx = 0;
	    while (true) {
	        idx = indexOf(myText, kGram, idx);
	        if (idx == -1 || (idx + myOrder) >= myText.length) {
	            break;
	        }
	        String followers = myText[idx + myOrder];
	        follows.add(followers);
	        idx = idx + myOrder;
	    }
	    return follows;
    }
}
