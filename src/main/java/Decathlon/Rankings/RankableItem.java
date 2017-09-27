package Decathlon.Rankings;

public class RankableItem {

    /**
     * Name for the item
     */
    public final String name;

    /**
     * A number by which this item will be ranked against others.
     */
    final int totalScore;

    /**
     * Constructor.
     *
     * @param name Name for the item
     * @param totalScore A number by which this item will be ranked against others.
     */
    public RankableItem(final String name, final int totalScore) {
        this.name = name;
        this.totalScore = totalScore;
    }
}
