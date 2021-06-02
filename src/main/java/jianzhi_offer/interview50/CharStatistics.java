package jianzhi_offer.interview50;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * CharStatistics
 *
 * @title CharStatistics
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/2
 **/
public class CharStatistics {

    private Set<Character> appearingOnceChars = new LinkedHashSet<>();

    private Set<Character> duplicateChars = new HashSet<>();

    public void insert(char ch) {
        if (duplicateChars.contains(ch)) {
            return;
        }
        if (appearingOnceChars.contains(ch)) {
            appearingOnceChars.remove(ch);
            duplicateChars.add(ch);
            return;
        }
        appearingOnceChars.add(ch);
    }

    public char firstAppearingOnce() {
        if (appearingOnceChars.isEmpty()) {
            return ' ';
        }
        return appearingOnceChars.iterator().next();
    }

}
