package ngordnet.proj2b_testing;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.main.HyponymsHandler;
import ngordnet.main.WordNet;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Tests the case where the list of words is length greater than 1, but k is still zero. */
public class TestMultiWordK0Hyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    public static final String WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    /** This is an example from the spec.*/
    @Test
    public void testOccurrenceAndChangeK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("occurrence", "change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        assertEquals(expected, actual);
    }

    /**test common
    @Test
    public void testCommonK0() {
        WordNet wordnet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("flashback");
        Set<Integer> fhypoID = wordnet.findCommon(words);
        assertTrue(fhypoID.contains(15));

        List<String> anotherWords = List.of("transition", "event");

        Set<Integer> TDhypoID = wordnet.findCommon(anotherWords);


        assertTrue(TDhypoID.size() == 2);

        List<String> alterWords = List.of("occurrence", "alteration");
        Set<Integer> alterIDs = wordnet.findAllIDs(alterWords.get(1));
        assertEquals(alterIDs.size(), 2);
        Set<Integer> alterHypoID = wordnet.findCommon(alterWords);
        assertEquals(alterHypoID, new TreeSet<>(List.of(2,11)));

    }*/

    /** This is an example from the spec on the full hyponyms and synsets file.*/
    @Test
    public void testBowlGalleryK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("bowl", "gallery");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[amphitheater, amphitheatre]";
        assertEquals(expected, actual);
    }

    /** This is another example from the spec where the user enters female, animal. */
    @Test
    public void testFemaleAnimalK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("female", "animal");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[amazon, bird, cat, chick, dam, demoiselle, female, female_mammal, filly, hag, hen, nanny, nymph, siren]";
        assertEquals(expected, actual);
    }

    /** This is another example from the spec where the user enters female, leader. */
    @Test
    public void testFemaleLeaderK0() {
        //System.out.println("NOTE: For the female, leader test, you have to fill in the expected text AND List<String> words yourself, otherwise this test will likely crash due to a null pointer exception");

        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("female", "leader");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[crown_princess, marchioness, materfamilias, matriarch, mayoress, mistress, vicereine, viscountess]";
        assertEquals(expected, actual);
    }

    @Test
    public void testEnergyLightSparkleK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("energy", "light", "sparkle"); // <-- replace with the appropriate list of words!

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);

        String expected = "[light, scintillation, spark, sparkle, twinkle]";


        assertEquals(expected, actual);
    }
}
