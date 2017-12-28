
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int n) {        
        myRandom = new Random();
        order = n;
        map = new HashMap<String, ArrayList<String>>();
    }
    
    @Override public String toString() {
        return "Efficient MarkovModel of order " + order;
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int idx = myRandom.nextInt(myText.length() - order);
        String key = myText.substring(idx, idx + order);
        sb.append(key);
        for(int k = 0; k < numChars - order; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("key " + key + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            idx = myRandom.nextInt(follows.size());
            String follower = follows.get(idx);
            sb.append(follower);
            key = key.substring(key.length() - (order - 1)) + follower;
        }       
        return sb.toString();
    }
    
    private void buildMap() {
        for (int i = 0; i < myText.length() - order; i++) {
            int end = i + order;
            String section = myText.substring(i, end);
            if (!map.containsKey(section)) {
                map.put(section, new ArrayList<String>());
            }
            if (end < myText.length()) {
                String follower = myText.substring(end, end + 1);
                ArrayList<String> followers = map.get(section);
                followers.add(follower);
                map.put(section, followers);
            }
        }
    }
    
    public void printHashMapInfo() {
        System.out.println("Number of keys: " + map.size());
        int largest = 0;
        for (String key : map.keySet()) {
            int size = map.get(key).size();
            if (size > largest) {
                largest = size;
            }
        }
        System.out.println("The size of the largest ArrayList of characters is "
                            + largest);
        System.out.println("The keys that have the maximum size value:");
        for (String key : map.keySet()) {
            if (map.get(key).size() == largest) {
                System.out.println(key);
            }
        }
        System.out.println("\n");
    }
    
    @Override public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }
}