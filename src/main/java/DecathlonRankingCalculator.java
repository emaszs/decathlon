import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class DecathlonRankingCalculator {
    private final HashMap<Integer, ArrayList<AthleteResults>> uniqueResultMap;


    DecathlonRankingCalculator(ArrayList<AthleteResults> athleteResultsList) {
        this.uniqueResultMap = makeUniqueResultMap(athleteResultsList);
    }

    HashMap<Integer, ArrayList<AthleteResults>> makeUniqueResultMap (ArrayList<AthleteResults> athleteResultsList) {
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

    String makeIncrementingRankingString(int start, int length) {
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

    ArrayList<RankingBucket> toSortedRankingBucketList() {
        ArrayList<RankingBucket> res = new ArrayList<>();

        // Get the unique scores and sort them. This will be helpful when creating and assigning the final rankings.
        ArrayList<Integer> scoreList = new ArrayList<>(uniqueResultMap.keySet());
        scoreList.sort(Collections.reverseOrder());

        /* (Indirectly) represents the position of the athlete in the final table regardless of score collisions.
        * Helps when building the final ranking string. */
        Integer rankingTableIndex = 1;

        for (Integer score : scoreList) {
            int bucketSize = uniqueResultMap.get(score).size();

            String ranking = makeIncrementingRankingString(rankingTableIndex, bucketSize);
            RankingBucket newBucket = new RankingBucket(ranking, uniqueResultMap.get(score));
            res.add(newBucket);

            rankingTableIndex += bucketSize;
        }
        return res;
    }
}
