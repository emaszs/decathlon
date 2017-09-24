import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class DecathlonRankingCalculator {
    private HashMap<Integer, ArrayList<AthleteResults>> uniqueResultMap;


    DecathlonRankingCalculator(ArrayList<AthleteResults> athleteResultsList) {
        this.uniqueResultMap = new HashMap<>();

        for (AthleteResults athleteResults : athleteResultsList) {
            if (uniqueResultMap.get(athleteResults.totalScore) == null) {
                ArrayList<AthleteResults> newBucket = new ArrayList<>();
                newBucket.add(athleteResults);
                uniqueResultMap.put(athleteResults.totalScore, newBucket);
            } else {
                ArrayList<AthleteResults> bucket = uniqueResultMap.get(athleteResults.totalScore);
                bucket.add(athleteResults);
            }
        }
    }

    ArrayList<RankingBucket> toSortedRankingBucketList() {
        ArrayList<RankingBucket> res = new ArrayList<>();

        // Get the unique scores and sort them. This will be helpful when creating and assigning the final rankings.
        ArrayList<Integer> scoreList = new ArrayList<>(uniqueResultMap.keySet());
        scoreList.sort(Collections.reverseOrder());

        // Represents the position of the athlete in the final table regardless of score collisions.
        Integer finalTableIndex = 1;

        for (Integer score : scoreList) {
            int bucketSize = uniqueResultMap.get(score).size();
            StringBuilder ranking = new StringBuilder(finalTableIndex.toString());
            if (bucketSize == 1) {
                // In case the score is unique to a single athlete, simply assign the ranking and move on.
                RankingBucket newBucket = new RankingBucket(ranking.toString(), uniqueResultMap.get(score));
                res.add(newBucket);
            } else {
                /* If more than one athlete shares the same score, build the string representing the shared ranking
                between athleteResultsList, e.g. '3-4' if the third highest score is shared by two athleteResultsList. */
                for (int i = 1; i < bucketSize; i++) {
                    ranking.append("-").append(Integer.toString(finalTableIndex + i));
                }
                RankingBucket newBucket = new RankingBucket(ranking.toString(), uniqueResultMap.get(score));
                res.add(newBucket);
            }
            finalTableIndex += bucketSize;
        }

        return res;
    }


}
