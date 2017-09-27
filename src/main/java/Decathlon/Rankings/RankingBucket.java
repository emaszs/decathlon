package Decathlon.Rankings;

import java.util.ArrayList;

public class RankingBucket<T extends RankableItem> {
    /**
     * The rank shared by the items in this bucket.
     */
    public final String ranking;

    /**
     * Items contained in this bucket.
     */
    public final ArrayList<T> items;

    /**
     * Constructor.
     *
     * @param ranking Ranking assigned to this bucket.
     * @param items Items contained in the bucket.
     */
    public RankingBucket(final String ranking, final ArrayList<T> items) {
        this.ranking = ranking;
        this.items = items;
    }

}
