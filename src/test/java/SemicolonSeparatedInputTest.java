import org.junit.Test;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SemicolonSeparatedInputTest {
    @Test
    public void processLine_athleteGiven_returnsCorrectResults() {
        String line = "Siim Susi;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72 ";
        SemicolonSeparatedInputImpl inp = new SemicolonSeparatedInputImpl();
        AthleteResults res = inp.processLine(line);

        assertEquals("Siim Susi", res.name);
        assertEquals(res.results.get(SportEvent.ONE_HUNDRED), "12.61");
        assertEquals(res.results.get(SportEvent.LONG_JUMP), "5.00");
        assertEquals(res.results.get(SportEvent.FIFTEEN_HUNDRED), "5.25.72 ");
    }

    @Test
    public void readAthleteResults_singleAthleteGiven_returnsCorrectResults() {
        Reader reader = new StringReader("Siim Susi;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72 \n");
        BufferedReader bufReader = new BufferedReader(reader);
        Input inp = new SemicolonSeparatedInputImpl();

        AthleteResults res = inp.readAthleteResults(bufReader).get(0);
        assertEquals("Siim Susi", res.name);
        assertEquals(res.results.get(SportEvent.ONE_HUNDRED), "12.61");
        assertEquals(res.results.get(SportEvent.LONG_JUMP), "5.00");
        assertEquals(res.results.get(SportEvent.FIFTEEN_HUNDRED), "5.25.72 ");
    }

    @Test
    public void readAthleteResults_multipleAthletesGiven_returnsCorrectResults() {
        String testInput = "Siim Susi;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72 \n";
        testInput += "Beata Kana;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6.50.76 \n";
        Reader reader = new StringReader(testInput);
        BufferedReader bufReader = new BufferedReader(reader);
        Input inp = new SemicolonSeparatedInputImpl();

        ArrayList<AthleteResults> res = inp.readAthleteResults(bufReader);

        assertEquals("Siim Susi", res.get(0).name);
        assertEquals("5.00", res.get(0).results.get(SportEvent.LONG_JUMP));
        assertEquals("Beata Kana", res.get(1).name);
        assertEquals("4.53", res.get(1).results.get(SportEvent.LONG_JUMP));
    }
}
