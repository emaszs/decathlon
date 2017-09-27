package Decathlon.IO;

import Decathlon.Rankings.AthleteResults;
import Decathlon.Rankings.RankingBucket;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Abstract class which outputs the data about athlete's performance in a decathlon competition, as well as their ranks.
 */
public abstract class Output {

    /**
     * Write out the list of {@link RankingBucket} to a character stream.
     *
     * @param athleteResultsBucketList Data about athletes to write.
     * @param writer Stream to write to.
     * @throws IOException If output exception occurred.
     */
    public abstract void writeOutput(ArrayList<RankingBucket<AthleteResults>> athleteResultsBucketList, Writer writer)
            throws IOException;
}
