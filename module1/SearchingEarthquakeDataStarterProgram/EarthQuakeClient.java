import java.util.*;
import edu.duke.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // iterate arraylist to find earthquakes
        for (QuakeEntry qe : quakeData) {
            // get magnitude
            double mag = qe.getMagnitude();
            // if current earthquake's magnitude is greater than the minimum
            if (mag > magMin) {
                // add to new arraylist
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> copyQuakeData = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int k = 0; k < 25; k++) {
            int minDist = 0;
            for (int i = 1; i < copyQuakeData.size(); i++) {
                QuakeEntry qe = copyQuakeData.get(i);
                Location loc = qe.getLocation();
                if (loc.distanceTo(from) < distMax) {
                    minDist = i;
                }            
            }
            answer.add(copyQuakeData.get(minDist));
            copyQuakeData.remove(minDist);
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> data, double min, double max) {
        ArrayList<QuakeEntry> copyData = new ArrayList<QuakeEntry>(data);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : copyData) {
            double comp = qe.getDepth();
            if (comp > min && comp < max) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> data, 
                                                String where, 
                                                String phrase) {
        String regex = "";
        ArrayList<QuakeEntry> copyData = new ArrayList<QuakeEntry>(data);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : copyData) {
            String info = qe.getInfo();
            if (where.equals("start")) {
                regex = "\\A" + phrase;
                if (Pattern.matches(regex, info)) {
                    answer.add(qe);
                }
            }
            if (where.equals("end")) {
                regex = phrase + "\\z";
                if (Pattern.matches(regex, info)) {
                    answer.add(qe);
                }
            }
            if (where.equals("any")) {
                regex = ".*" + phrase + ".*";
                if (Pattern.matches(regex, info)) {
                    answer.add(qe);
                }
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("This file includes data for "+list.size()+" earthquakes.");
        ArrayList<QuakeEntry> answer = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);        
        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list, 1000, city);
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public void testFilterByDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> data = parser.read(source);
        System.out.println("The file read contains data for " + data.size() + " earthquakes.");
        double min = -8000.0;
        double max = -5000.0;
        ArrayList<QuakeEntry> answer = filterByDepth(data, min, max);
        System.out.println(answer.size() + " earthquakes matched the criteria specified.");
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    }
    
    public void testFilterByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> data = parser.read(source);
        System.out.println("The file read contains data for " + data.size() + " earthquakes.");
        String where = "any";
        String phrase = "Creek";
        ArrayList<QuakeEntry> answer = filterByPhrase(data, where, phrase);
        System.out.println(answer.size() + " earthquakes matched the criteria specified.");
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    }
}
