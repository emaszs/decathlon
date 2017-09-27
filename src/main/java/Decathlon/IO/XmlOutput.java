package Decathlon.IO;

import Decathlon.Rankings.AthleteResults;
import Decathlon.Rankings.RankingBucket;
import Decathlon.Rankings.SportEvent;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Writes competition results in an XML format.
 */
public class XmlOutput extends Output {

    /**
     * Writes the beginning of the XML file.
     *
     * @param writer Stream to write to.
     * @throws IOException If output exception occurred.
     */
    static void writeXmlBeginning(Writer writer) throws IOException {
        writer.write("<?xml version=\"1.0\"?>\n");
        writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"decathlon.xsl\"?>\n");
        writer.write("<decathlon>\n");
        writer.flush();
    }

    /**
     * Writes the ending of the XML file.
     *
     * @param writer Stream to write to.
     * @throws IOException If output exception occurred.
     */
    static void writeXmlEnd(Writer writer) throws IOException {
        writer.write("</decathlon>\n");
        writer.flush();
    }

    /**
     * Writes out all of the athlete nodes in a single {@link RankingBucket}. These athletes share the same ranking in
     * the competition.
     *
     * @param bucket The bucket which will be written in XML format.
     * @param writer Stream to write to.
     * @throws IOException If output exception occurred.
     */
    static void writeRankingBucket(RankingBucket<AthleteResults> bucket, Writer writer) throws IOException {
        for (AthleteResults athRes : bucket.items) {
            writer.write("  <athlete>\n");
            writer.write("    <name>" + athRes.name + "</name>\n");
            writer.write("    <ranking>" + bucket.ranking + "</ranking>\n");

            for (SportEvent event : athRes.results.keySet()) {
                String eventResultsLine = String.format("    <%s>%s</%s>\n", event.toString(),
                        athRes.results.get(event), event.toString());
                writer.write(eventResultsLine);
            }

            writer.write("    <total_score>" + athRes.calculateTotalScore() + "</total_score>\n");
            writer.write("  </athlete>\n");
        }
        writer.flush();
    }

    /**
     * Writes out multiple {@link RankingBucket} items in an XML format by invoking the
     * {@link #writeRankingBucket(RankingBucket, Writer)} method on each bucket. Each bucket may contain several
     * athletes.
     *
     * @param athleteResultsBucketList The list of buckets to write out.
     * @param writer Stream to write to.
     * @throws IOException If output exception occurred.
     */
    public void writeOutput(ArrayList<RankingBucket<AthleteResults>> athleteResultsBucketList, Writer writer)
            throws IOException {
        writeXmlBeginning(writer);
        for (RankingBucket<AthleteResults> bucket : athleteResultsBucketList) {
            writeRankingBucket(bucket, writer);
        }
        writeXmlEnd(writer);
    }
}
