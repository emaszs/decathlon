import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * A class used for producing the rankings for a number of {@link RankableItem}s.
 *
 * These rankings are expressed as a sorted list of {@link RankingBucket} objects. Normally, each bucket would contain
 * a single item, and that item would be assigned a simple rank, such as {@code "1"} or {@code "2"}. However,
 * if two or more items share the same total score, their rankings should be represented as {@code "3-4"} or
 * {@code "3-4-5"}, for example. In this case, a bucket representing a certain score will contain the items that
 * share such a score and an appropriate ranking string.
 */
class RankingCalculator<T extends RankableItem> {
    /**
     * Produces a map where an {@code Integer} score identifies one or more items that share the score in a competition.
     *
     * @param resultsList A list of items in the competition.
     * @return Mapping of unique {@code Integer} scores to one or more athletes.
     */
    HashMap<Integer, ArrayList<T>> makeUniqueResultMap(final ArrayList<T> resultsList) {
        HashMap<Integer, ArrayList<T>> res = new HashMap<>();

        for (T results : resultsList) {
            if (res.get(results.totalScore) == null) {
                ArrayList<T> newSharedScoreList = new ArrayList<>();
                newSharedScoreList.add(results);
                res.put(results.totalScore, newSharedScoreList);
            } else {
                ArrayList<T> sharedScoreList = res.get(results.totalScore);
                sharedScoreList.add(results);
            }
        }
        return res;
    }

    /**
     * Produces a string which is used to represent the item's rank in the competition. Mainly useful when two or more
     * items share the same score.
     *
     * For example, if both {@code start} and {@code length} params are equal to {@code "1"}, the resulting string will
     * be {@code "1'}.
     * If {@code start == 2} and {@code length == 3}, the resulting string will look like {@code "2-3-4"}.
     *
     * @param start An int from which to start incrementing.
     * @param length How many numbers the resulting string will contain.
     * @return A string of incrementing ints, separated by dashes ("-"), such as {@code "2-3-4"}.
     */
    static String makeIncrementingRankingString(final int start, final int length) {
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
     * {@link #makeUniqueResultMap(ArrayList)} method. One or more items in this map are represented by a
     * certain score that they share. Once these preliminary item lists are known, the final {@link RankingBucket}
     * objects are created, assigned an appropriate rank in the competition and returned as a sorted list.
     *
     * @return A list of {@link RankingBucket} objects, sorted in descending order by their score.
     */
    ArrayList<RankingBucket<T>> makeSortedRankingBucketList(final ArrayList<T> athleteResultsList) {
        ArrayList<RankingBucket<T>> res = new ArrayList<>();

        // The same score may be shared by multiple athletes, therefore we create a map to represent these lists
        HashMap<Integer, ArrayList<T>> uniqueResultMap = makeUniqueResultMap(athleteResultsList);

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
