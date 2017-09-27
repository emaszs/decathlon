package Decathlon.IO;

import Decathlon.Rankings.AthleteResults;
import Decathlon.Rankings.SportEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 * Reads athlete data from a character stream, where each line represents a different athlete's results in the
 * competition and the items in the line are separated by semicolons.
 */
public class SemicolonSeparatedInput extends Input {

    /**
     * Process one line of the input data. One line is expected to represent a single athlete's performance in
     * a decathlon competition.
     *
     * @param line A string of 11 items, separated by semicolons, the first being the athletes name and the rest
     *             his results in each of the 10 events
     * @return A {@link AthleteResults} object representing the athlete's performance in the competition
     */
    public AthleteResults processLine(final String line) {
        EnumMap<SportEvent, String> athleteResultsMap = new EnumMap<>(SportEvent.class);
        String[] splitLine = line.split(";");

        String athleteName = splitLine[0];

        // Indexing from 1 in order to ignore the first item in the line which is the athlete's name.
        // The rest are the event results which match with the SportEvent enum values in terms of their order.
        for (int i = 1; i < splitLine.length; i++) {
            athleteResultsMap.put(SportEvent.values()[i-1], splitLine[i].replaceAll("\\s+", ""));
        }

        return new AthleteResults(athleteName, athleteResultsMap);
    }

    /**
     * Read input data line by line, where each line represents a single {@link AthleteResults}, and return a list
     * consisting of these objects.
     *
     * @param reader Decathlon.IO.Input data, separated by newlines
     * @return A list of {@link AthleteResults} that were discovered in the input data.
     */
    @Override
    public ArrayList<AthleteResults> readAthleteResults(final Reader reader) throws IOException {
        BufferedReader bufReader = new BufferedReader(reader);
        ArrayList<AthleteResults> res = new ArrayList<>();

        String line;
        while ((line = bufReader.readLine()) != null) {
            res.add(processLine(line));
        }

        return res;
    }
}
