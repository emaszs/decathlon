import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;

public class SemicolonSeparatedInputImpl implements Input {
    // TODO class comment

    //TODO comment
    @Override
    public ArrayList<AthleteResults> readAthleteResults(BufferedReader reader) {
        ArrayList<AthleteResults> res = new ArrayList<>();


        SportEvent[] sportEvents = SportEvent.values();

        // TODO validate input according to each enum value with regex.
        // TODO Also maybe add a check for zeroes in case an event wasn't finished?
        try {
            String line;
            while ((line = reader.readLine()) != null){
                String[] splitLine = line.split(";");
                EnumMap<SportEvent, String> athleteResultsMap = new EnumMap<>(SportEvent.class);

                String athleteName = splitLine[0];


                // Indexing from 1 in order to ignore the first item in the line which is the athlete's name.
                // The rest are the event results which match with the SportEvent enum values in terms of their order.
                for (int i = 1; i < splitLine.length; i++) {
                    athleteResultsMap.put(sportEvents[i-1], splitLine[i]);
                }

                AthleteResults athRes = new AthleteResults(athleteName, athleteResultsMap);

                res.add(athRes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
