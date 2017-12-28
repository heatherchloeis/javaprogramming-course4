
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> map; 
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
		buildMap();
	}
		
	private int indexOf(String[] words, WordGram target, int start) {
	    for (int i = start; i < words.length - myOrder; i++) {
	        WordGram word = new WordGram(words, i, myOrder);
	        if (words[i].equals(target)) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	public void buildMap() {	    
        map = new HashMap<Integer, ArrayList<String>>();
	    for (int i = 0; i < myText.length - myOrder; i++) {
	        WordGram word = new WordGram(myText, i, myOrder);
	        int code = word.hashCode();
	        if (!map.containsKey(code)) {
	            map.put(code, new ArrayList<String>());
	        }
	        if (i + myOrder < myText.length) {
	            String follower = myText[i + myOrder];
	            ArrayList<String> follows = map.get(code);
	            follows.add(follower);
	            map.put(code, follows);
	        }
	    }
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
		WordGram key = new WordGram(myText, index, myOrder);
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords - myOrder; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows == null || follows.size() == 0) {
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
	    return map.get(kGram.toString().hashCode());
	}
	
	public void printHashMapInfo() {
	    //System.out.println(map);
	    System.out.println("Number of keys: " + (map.size()));
	    int largest = 0;
	    for (int code : map.keySet()) {
	        int curr = map.get(code).size();
	        if (curr > largest) {
	            largest = curr;
	        }
	    }
	    System.out.println("Size of largest value in the HashMap: " + largest);
	    for (int code : map.keySet()) {
	        if (map.get(code).size() == largest) {
	            System.out.println("The keys that have the maximum size value: ");
	            System.out.println(code + " .... " + map.get(code));
	        }
	    }
	}
}
