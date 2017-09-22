import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;

public class SemicolonSeparatedInputImpl implements Input {
    // TODO class comment

    public final String[] inputLabels = {"Name", "100 metres", "Long jump", "Shot put", "High jump", "400 metres",
            "110 metres hurdles", "Discus throw", "Pole vault", "Javelin throw", "1500 metres race"};

    //TODO comment
    @Override
    public ArrayList<Athlete> readAthleteResults(BufferedReader reader) {
        ArrayList<Athlete> res = new ArrayList<>();


        SportEvent[] sportEvents = SportEvent.values();

        try {
            String line;
            while ((line = reader.readLine()) != null){
                String[] splitLine = line.split(";");
                EnumMap<SportEvent, String> athleteResultsMap = new EnumMap<>(SportEvent.class);

                String athleteName = splitLine[0];


                // Indexing from 1 in order to ignore the first item in the line which is the athlete's name.
                // The rest are the event results which match with the SportEvent enum values in terms of their order.
                for (int i = 1; i < inputLabels.length; i++) {
                    athleteResultsMap.put(sportEvents[i-1], splitLine[i]);
                }

                Athlete athRes = new Athlete(athleteName, athleteResultsMap);

                res.add(athRes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
