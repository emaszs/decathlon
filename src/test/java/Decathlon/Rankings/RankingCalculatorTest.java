package Decathlon.Rankings;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class RankingCalculatorTest {
    @Test
    public void makeUniqueResultMap_withOneScoreCollision_returnedMapIsCorrect() {
        ArrayList<RankableItem> list = new ArrayList<>();
        list.add(new RankableItem("item1", 100));
        list.add(new RankableItem("item2", 200));
        list.add(new RankableItem("item3", 200));
        list.add(new RankableItem("item4", 400));

        RankingCalculator<RankableItem> rankCalc = new RankingCalculator<>();

        HashMap<Integer, ArrayList<RankableItem>> res = rankCalc.makeUniqueResultMap(list);

        assertEquals(3, res.size());
        assertEquals("item2", res.get(200).get(0).name);
        assertEquals("item3", res.get(200).get(1).name);
    }

    @Test
    public void makeUniqueResultMap_singleItem_returnedMapIsCorrect() {
        ArrayList<RankableItem> list = new ArrayList<>();
        list.add(new RankableItem("item1", 100));

        RankingCalculator<RankableItem> rankCalc = new RankingCalculator<>();

        HashMap<Integer, ArrayList<RankableItem>> res = rankCalc.makeUniqueResultMap(list);

        assertEquals(1, res.size());
        assertEquals("item1", res.get(100).get(0).name);
    }

    @Test
    public void makeIncrementingRankingString_singleNumber_returnedStringIsCorrect() {
        assertEquals("11", RankingCalculator.makeIncrementingRankingString(11, 1));
    }

    @Test
    public void makeIncrementingRankingString_multipleNumbers_returnedStringIsCorrect() {
        assertEquals("2-3-4-5", RankingCalculator.makeIncrementingRankingString(2, 4));
    }

    @Test
    public void makeSortedRankingBucketList_scoreCollision_returnedBucketsAreCorrect() {
        ArrayList<RankableItem> list = new ArrayList<>();
        list.add(new RankableItem("item1", 100));
        list.add(new RankableItem("item2", 200));
        list.add(new RankableItem("item3", 200));
        list.add(new RankableItem("item4", 400));

        RankingCalculator<RankableItem> rankCalc = new RankingCalculator<>();

        ArrayList<RankingBucket<RankableItem>> res = rankCalc.makeSortedRankingBucketList(list);

        assertEquals("1", res.get(0).ranking);
        assertEquals("2-3", res.get(1).ranking);
        assertEquals("4", res.get(2).ranking);

        assertEquals("item4", res.get(0).items.get(0).name);
    }
}
