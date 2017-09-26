class RankableItem {

    /**
     * Name for the item
     */
    final String name;

    /**
     * A number by which this item will be ranked against others.
     */
    final int totalScore;

    RankableItem(final String name, final int totalScore) {
        this.name = name;
        this.totalScore = totalScore;
    }
}
