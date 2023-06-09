package ngordnet.main;

import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

public class MaxCountComparator implements Comparator<String> {

    Map<String, Double> hypoCount;

    public MaxCountComparator(Map<String, Double> hc) {
        super();
        hypoCount = hc;
    }

    public int compare(String lhs, String rhs) {
        Double leftcount = hypoCount.get(lhs);
        Double rightcount = hypoCount.get(rhs);

        if (leftcount < rightcount) {
            return 1;
        }
        if (leftcount > rightcount) {
            return -1;
        }
        return 0;
    }


}
