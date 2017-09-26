import java.util.ArrayList;

class RankingBucket<T> {
    final String ranking;
    final ArrayList<T> resultsList;

    RankingBucket(String ranking, ArrayList<T> resultsList) {
        this.ranking = ranking;
        this.resultsList = resultsList;
    }

}
