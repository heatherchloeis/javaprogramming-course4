import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";        
        // String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> data  = parser.read(source);         
        System.out.println("The file read includes data for " + data.size() + " earthquakes");

        MatchAllFilter mat = new MatchAllFilter();
        
        Location loc = new Location(55.7308, 9.1153);
        //Filter f = new DistanceFilter(loc, 3000000);
        //Filter ff = new MagnitudeFilter(0.0, 5.0);
        Filter ff = new DepthFilter(-4000, -2000);
        //Filter fff = new PhraseFilter("e");
        //mat.addFilter(f);
        mat.addFilter(ff);
        //mat.addFilter(fff);
        ArrayList<QuakeEntry> answer = filter(data, mat); 
        System.out.println(answer.size() + " earthquakes were found matching the criteria.");
        for (QuakeEntry qe: answer) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("The number of earthquake data read is " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
