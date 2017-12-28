
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;
import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setTraining(st);
		markov.setRandom(1024);
		for (int k = 0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	
	public void runMarkovOne() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		markov.setRandom(365);
		for (int k = 0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	
	public void runMarkovFour() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		//st = "this is a test of the emergency alert system. This is a test.";
		MarkovFour markov = new MarkovFour();
		markov.setTraining(st);
		markov.setRandom(715);
		for (int k = 0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	
	public void runMarkovModel() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		//st = "this is a test of the emergency alert system. This is a test.";
		MarkovModel markov = new MarkovModel(7);
		markov.setTraining(st);
		markov.setRandom(953);
		for (int k = 0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	
	public void runGetFollows() {	    
		FileResource fr = new FileResource();
		String test = fr.asString();
		test = test.replace('\n', ' ');
	    //test = "this is a test yes this is a test";
	    MarkovOne markov = new MarkovOne();
	    markov.setTraining(test);
	    ArrayList<String> follows = markov.getFollows("he");
	    System.out.println("The array is of size " + follows.size());
	    for (int i = 0; i < follows.size(); i++) {
	        System.out.println(follows.get(i));
	    }
	}
	
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
}
