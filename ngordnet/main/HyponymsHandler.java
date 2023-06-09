package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;


import java.util.*;


public class HyponymsHandler extends NgordnetQueryHandler {
    WordNet wordnet;
    NGramMap ngm;

    public HyponymsHandler(WordNet wn, NGramMap map) {
        super();
        wordnet = wn;
        ngm = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        if (words == null || words.isEmpty()) {
            return new ArrayList<String>().toString();
        }
        Set<Integer> hypoIDs = wordnet.findHypoIDs(words.get(0));
        Set<String> allHypos = wordnet.findWords(hypoIDs);


        for (int i = 1; i < words.size(); i++) {
            if (allHypos.isEmpty()) {
                return allHypos.toString();
            }
            Set<Integer> newHypoIDs = wordnet.findHypoIDs(words.get(i));
            Set<String> newAllHypos = wordnet.findWords(newHypoIDs);
            allHypos.retainAll(newAllHypos);
        }

        if (k == 0) {
            return allHypos.toString();
        }

        Map<String, Double> hypoCount = new HashMap<>();
        for (String h : allHypos) {
            Collection<Double> yearCounts = ngm.countHistory(h, startYear, endYear).values();
            double count = 0;
            for (double c : yearCounts) {
                count += c;
            }
            if (count != 0) {
                hypoCount.put(h, count);
            }
        }

        MaxCountComparator countCompare = new MaxCountComparator(hypoCount);
        /*PriorityQueue<String> popHypo = new PriorityQueue<>(k, countCompare);
        for (String hypo : hypoCount.keySet()) {
            popHypo.add(hypo);
        }*/


        List<String> popHypo = new ArrayList<>(hypoCount.keySet());
        if (k < popHypo.size()) {
            popHypo.sort(countCompare);
            popHypo = popHypo.subList(0, k);
        }
        popHypo.sort(Comparator.naturalOrder());
        return popHypo.toString();

    }


}
