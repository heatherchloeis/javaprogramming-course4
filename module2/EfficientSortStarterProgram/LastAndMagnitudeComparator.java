
/**
 * Write a description of LastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String infoQ1 = q1.getInfo();
        String lastQ1 = infoQ1.substring(infoQ1.lastIndexOf(" ") + 1);
        String infoQ2 = q2.getInfo();        
        String lastQ2 = infoQ2.substring(infoQ2.lastIndexOf(" ") + 1);
        int val = lastQ1.compareTo(lastQ2);
        if (val == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return val;
    }
}
