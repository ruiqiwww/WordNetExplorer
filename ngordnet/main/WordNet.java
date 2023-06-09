package ngordnet.main;

import edu.princeton.cs.algs4.In;

import java.util.*;


public class WordNet {
    private DirectedGraph IDGraph;

    /**idToSynset.get(id) returns a String[] as a synset*/
    private List<String[]> idToSynset;

    private Map<String, Set<Integer>> wordToAllIDs;


    public WordNet(String synsetFile, String hyponymFile) {
        idToSynset = new ArrayList<>();
        wordToAllIDs = new HashMap<>();

        In synsetReader = new In(synsetFile);
        int numSets = 0;
        while (synsetReader.hasNextLine()) {
            numSets +=1;
            String[] line = synsetReader.readLine().split(",");
            String[] synset = line[1].split(" ");
            idToSynset.add(synset);
            for (String word:synset) {
                if (!wordToAllIDs.containsKey(word)) {
                    wordToAllIDs.put(word, new TreeSet<>());
                }
                wordToAllIDs.get(word).add(numSets - 1);
            }

        }

        IDGraph = new DirectedGraph(numSets);

        In hypoReader = new In(hyponymFile);
        while (hypoReader.hasNextLine()) {
            String[] line = hypoReader.readLine().split(",");

            int hyperID = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                int hypoID = Integer.parseInt(line[i]);
                IDGraph.addEdge(hyperID, hypoID);
            }
        }
    }


    /**Given a set of IDs, return a list of all words in those synsets
     * without duplicates in alphabetical order*/
    public Set<String> findWords(Set<Integer> IDs) {
        Set<String> words = new TreeSet<>();
        for (int id : IDs) {
            String[] synset = idToSynset.get(id);

            for (String w:synset) {
                words.add(w);
            }
        }
        return words;
    }

    /**Given a list of parent IDs, return a list of their hyponyms*/
    public Set<Integer> findHypoIDs(String word) {
        Set<Integer> parentIDs = wordToAllIDs.get(word);
        Set<Integer> allHypoIDs = new TreeSet<>();
        if (parentIDs == null) {
            return allHypoIDs;
        }
        for (int id : parentIDs) {
            HyponymPath paths = new HyponymPath(IDGraph, id);
            allHypoIDs.addAll(paths.findHypos());
        }
        return allHypoIDs;
    }

    /*Given a list of word, find a list of ids of biggest common children.
      Problems: word and synsets are not one-to-one.
    public Set<Integer> findCommon(List<String> words) {
        if (words == null) {
            return null;
        }
        Set<Integer> common = findAllIDs(words.get(0));
        if (words.size() < 2) {
            return common;
        }
        for (int i = 1; i < words.size(); i++) {
            if (common == null || common.isEmpty()) {
                break;
            }
            Set<Integer> newWordIDs = findAllIDs(words.get(i));
            if (newWordIDs == null) {
                break;
            }
            Set<Integer> child1 = findCommonHelper(common, newWordIDs);
            if (child1.size() == 0) {
                Set<Integer> child2 = findCommonHelper(newWordIDs, common);
                child1.addAll(child2);
            }

            common = child1;
        }

        return common;
    }

    private Set<Integer> findCommonHelper(Set<Integer> child, Set<Integer> parent) {

        Set<Integer> common = new TreeSet<>();
        for (int newID:parent) {
            if (child.isEmpty()) {
                break;
            }
            HyponymPath pPaths = new HyponymPath(IDGraph, newID);
            for (int c:child) {
                if (pPaths.hasPathTo(c)) {
                    common.add(c);
                }
            }

            child.removeAll(common);
        }
        return common;

    }*/




}
