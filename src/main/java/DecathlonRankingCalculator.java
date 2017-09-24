import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * A class used for producing the rankings of a decathlon competition.
 *
 * These rankings are expressed as a sorted list of {@link RankingBucket} objects. Normally, each bucket would contain
 * a single athlete, and that athlete would be assigned a simple rank, such as {@code "1"} or {@code "2"}. However,
 * if two or more athletes share the same total score, their rankings should be represented as {@code "3-4"} or
 * {@code "3-4-5"}, for example. In this case, a bucket representing a certain score will contain the athletes that
 * share such a score and an appropriate ranking string.
 */
class DecathlonRankingCalculator {
    /**
     * Produces a map where an {@code Integer} score identifies one or more athletes that share the score in the
     * competition.
     *
     * @param athleteResultsList A list of {@link AthleteResults} in the competition.
     * @return Mapping of unique {@code Integer} scores to one or more athletes.
     */
    HashMap<Integer, ArrayList<AthleteResults>> makeUniqueResultMap
        (final ArrayList<AthleteResults> athleteResultsList) {
        HashMap<Integer, ArrayList<AthleteResults>> res = new HashMap<>();

        for (AthleteResults athleteResults : athleteResultsList) {
            if (res.get(athleteResults.totalScore) == null) {
                ArrayList<AthleteResults> newSharedScoreList = new ArrayList<>();
                newSharedScoreList.add(athleteResults);
                res.put(athleteResults.totalScore, newSharedScoreList);
            } else {
                ArrayList<AthleteResults> sharedScoreList = res.get(athleteResults.totalScore);
                sharedScoreList.add(athleteResults);
            }
        }
        return res;
    }

    /**
     * Produces a string which is used to represent the athlete's rank in the competition. Especially useful when
     * two or more athletes share the same score.
     *
     * For example, if both {@code start} and {@code length} params are equal to {@code "1"}, the resulting string will
     * be {@code "1'}.
     * If {@code start == 2} and {@code length == 3}, the resulting string will look like {@code "2-3-4"}.
     *
     * @param start An int from which to start incrementing.
     * @param length How many numbers the resulting string will contain.
     * @return A string of incrementing ints, separated by dashes ("-"), such as {@code "2-3-4"}.
     */
    String makeIncrementingRankingString(final int start, final int length) {
        StringBuilder res = new StringBuilder(Integer.toString(start));
        if (length == 1) {
            // Nothing else needs to be done with the string.
            return res.toString();
        } else {
            // TODO make comment formatting consistent!
            /* Build the string, starting from the a certain int, incrementing the int by 1 for the specified length
            * and separating each int with a dash ("-")*/
            for (int i = 1; i < length; i++) {
                res.append("-").append(Integer.toString(start + i));
            }
            return res.toString();
        }
    }

    /**
     * Creates a sorted list of {@link RankingBucket} objects.
     *
     * To achieve this, the method first creates a map of unique results by invoking the
     * {@link #makeUniqueResultMap(ArrayList)} method. One or more athletes in this map can be represented by a
     * certain score that they share. Once these preliminary athlete lists are known, the final {@link RankingBucket}
     * objects are created, assigned an appropriate rank in the competition and returned.
     *
     * @return A list of {@link RankingBucket} objects, sorted in descending order by their score.
     */
    ArrayList<RankingBucket> toSortedRankingBucketList(final ArrayList<AthleteResults> athleteResultsList) {
        ArrayList<RankingBucket> res = new ArrayList<>();

        // The same score may be shared by multiple athletes, therefore we create a map to represent these lists
        HashMap<Integer, ArrayList<AthleteResults>> uniqueResultMap = makeUniqueResultMap(athleteResultsList);

        // Get the unique score ints and sort them. This will be helpful when creating and assigning the final rankings.
        ArrayList<Integer> scoreList = new ArrayList<>(uniqueResultMap.keySet());
        scoreList.sort(Collections.reverseOrder());

        /* (Indirectly) represents the position of the athlete in the final table regardless of score collisions.
        * Helps when building the final ranking string. */
        Integer rankingTableIndex = 1;

        for (Integer score : scoreList) {
            int bucketSize = uniqueResultMap.get(score).size();

            // Get the ranking string that the athletes in this bucket will share.
            String ranking = makeIncrementingRankingString(rankingTableIndex, bucketSize);
            RankingBucket newBucket = new RankingBucket(ranking, uniqueResultMap.get(score));
            res.add(newBucket);

            rankingTableIndex += bucketSize;
        }
        return res;
    }
}
