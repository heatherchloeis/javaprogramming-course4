
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2 = myText[index + 1];
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 = next;
		}		
		return sb.toString().trim();
	}
	
	private int indexOf(String [] words, String target1, String target2, int start) {
	    for (int i = start; i < words.length; i++) {
	        if (words[i].equals(target1) && words[i + 1].equals(target2)) {
	            return i + 1;
	        }
	    }
	    return -1;
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int idx = 0;
	    while (true) {
	        idx = indexOf(myText, key1, key2, idx);
	        if (idx == -1 || (idx + 1) >= myText.length) {
	            break;
	        }
	        String followers = myText[idx + 1];
	        follows.add(followers);
	        idx++;
	    }
	    return follows;
    }
            
    public void testIndexOf() {
        String test = "this is just a test yes this is a simple test";
        MarkovWordTwo markov = new MarkovWordTwo();
        markov.setTraining(test);
        ArrayList<String> follows = markov.getFollows("this", "is");
        System.out.println("The array is of size " + follows.size());
        for (int i = 0; i < follows.size(); i++) {
            System.out.println(follows.get(i));
        }
    }
}
