
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PhraseFilter implements Filter {
    private String regex;
    public PhraseFilter(String phrase) {
        regex = ".*" + phrase + ".*";
    }
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        return Pattern.matches(regex, title);
    }
}
