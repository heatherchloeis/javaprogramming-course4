
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, 
                                            Location current, 
                                            int howMany) {
        ArrayList<QuakeEntry> copyQuakeData = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int x = 0; x < howMany; x++) {
            // create new minimum variable
            int minDist = 0;
            // iterate over entries
            for (int i = 1; i < copyQuakeData.size(); i++) {
                QuakeEntry qe = copyQuakeData.get(i);
                // get distance
                Location loc = qe.getLocation();
                // if new distance is less than minimum
                if (loc.distanceTo(current) < 
                    copyQuakeData.get(minDist).getLocation().distanceTo(current)) {
                    // update minimum
                    minDist = i;
                }
            }
            // add entry to array
            answer.add(copyQuakeData.get(minDist));
            copyQuakeData.remove(minDist);
        }
        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("The file read includes data for " + list.size() + " earthquakes.");

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list, jakarta, 10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("The closest earthquake(s) found are " + close.size());
    }
    
}
