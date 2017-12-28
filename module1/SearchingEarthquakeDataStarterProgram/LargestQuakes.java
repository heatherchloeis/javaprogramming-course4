
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> data  = parser.read(source);
        System.out.println("The file read includes data for " + data.size() + " earthquakes.");
        /* for (QuakeEntry q : data) {
            System.out.println(q);
        } */
        ArrayList<QuakeEntry> answer = getLargest(data, 50);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int idx = 0;
        double maxMag = 0;
        for (int i = 1; i < data.size(); i++) {
            QuakeEntry qe = data.get(i);
            double mag = qe.getMagnitude();
            if (mag > maxMag) {
                maxMag = mag;
                idx = data.indexOf(qe);
            }
        }
        return idx;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(data);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i = 0; i < howMany; i++) {
            int idx = 0;
            double maxMag = 0;
            for (int x = 1; x < copy.size(); x++) {
                QuakeEntry qe = copy.get(x);
                double mag = qe.getMagnitude();
                if (mag > maxMag) {
                    maxMag = mag;
                    idx = copy.indexOf(qe);
                }
            }
            answer.add(copy.get(idx));
            copy.remove(idx);
        }
        return answer;
    }
}
