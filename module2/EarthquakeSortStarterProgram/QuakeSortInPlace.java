
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i).getMagnitude() > data.get(i + 1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> data, int idx) {
        int max = idx;
        for (int i = idx + 1; i < data.size(); i++) {
            if (data.get(i).getDepth() > data.get(max).getDepth()) {
                max = i;
            }
        }
        return max;
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> data, int sort) {
        for (int i = 0; i < data.size() - 1 - sort; i++) {
            if (data.get(i).getMagnitude() > data.get(i + 1).getMagnitude()) {
                QuakeEntry q1 = data.get(i);
                QuakeEntry q2 = data.get(i + 1);
                data.set(i, q2);
                data.set(i + 1, q1);
            }
        }
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitudeBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
            System.out.println("Quakes sorted after " + i + " pass(es)");
            if (checkInSortedOrder(in)) {
                System.out.println("Successfully sorted in " + (i + 1) + " pass(es)");
                for (QuakeEntry qe : in) {
                    System.out.println(qe);
                }
                break;
            }
        }
    }
    
    public void sortByDepth(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < 50; i++) {
            int idx = getLargestDepth(in, i);
            QuakeEntry q1 = in.get(i);
            QuakeEntry q2 = in.get(idx);
            in.set(i, q2);
            in.set(idx, q1);
        }
        for (QuakeEntry qe : in) {
            System.out.println(qe);
        }
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if (checkInSortedOrder(in)) {
                System.out.println("Successfully sorted in " + (i + 1) + " pass(es)");
                break;
            }
       }
        
    }

    public void testSortMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for " + list.size() + " quakes");    
        sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
    }
    
    public void testSortDepth() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for " + list.size() + " quakes");    
        sortByDepth(list);
    }
    
    public void testSortMagnitudeBubbleSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for " + list.size() + " quakes");    
        sortByMagnitudeBubbleSort(list);
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
