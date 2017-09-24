import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;

public class SemicolonSeparatedInputImpl implements Input {
    // TODO class comment

    private AthleteResults processLine(final String line, final SportEvent[] sportEvents) {
        EnumMap<SportEvent, String> athleteResultsMap = new EnumMap<>(SportEvent.class);
        String[] splitLine = line.split(";");

        String athleteName = splitLine[0];

        // Indexing from 1 in order to ignore the first item in the line which is the athlete's name.
        // The rest are the event results which match with the SportEvent enum values in terms of their order.
        for (int i = 1; i < splitLine.length; i++) {
            athleteResultsMap.put(sportEvents[i-1], splitLine[i]);
        }

        return new AthleteResults(athleteName, athleteResultsMap);
    }

    //TODO comment
    @Override
    public ArrayList<AthleteResults> readAthleteResults(final BufferedReader reader) {
        ArrayList<AthleteResults> res = new ArrayList<>();
        SportEvent[] sportEvents = SportEvent.values();

        // TODO Maybe add a check for zeroes in case an event wasn't finished?
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                res.add(processLine(line, sportEvents));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
