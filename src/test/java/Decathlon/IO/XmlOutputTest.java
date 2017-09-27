package Decathlon.IO;

import Decathlon.Rankings.AthleteResults;
import Decathlon.Rankings.AthleteResultsTest;

import Decathlon.Rankings.RankingBucket;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class XmlOutputTest {

    final private ByteArrayOutputStream stream = new ByteArrayOutputStream();
    final private OutputStreamWriter writer = new OutputStreamWriter(stream);
    final private XmlOutput output = new XmlOutput();

    @Test
    public void writeXmlBeginning() throws Exception {
        output.writeXmlBeginning(writer);

        String expected = "<?xml version=\"1.0\"?>\n";
        expected += "<?xml-stylesheet type=\"text/xsl\" href=\"decathlon.xsl\"?>\n";
        expected += "<decathlon>\n";
        assertEquals(expected, stream.toString());
    }

    @Test
    public void writeXmlEnd() throws Exception {
        output.writeXmlEnd(writer);

        String expected = "</decathlon>\n";
        assertEquals(expected, stream.toString());
    }

    @Test
    public void writeOutput() throws Exception {
        ArrayList<AthleteResults> resultsList = new ArrayList<>();
        resultsList.add(AthleteResultsTest.createTestAthleteResults());

        ArrayList<RankingBucket<AthleteResults>> bucketList = new ArrayList<>();
        bucketList.add(new RankingBucket<>("1", resultsList));

        output.writeOutput(bucketList, writer);

        assertTrue(stream.toString().contains("<name>Siim Susi</name>\n"));
        assertTrue(stream.toString().contains("<shot_put>9.22</shot_put>\n"));
        assertTrue(stream.toString().contains("<fifteen_hundred>5.25.72</fifteen_hundred>\n"));
        assertTrue(stream.toString().contains("<total_score>3165</total_score>\n"));
    }

}