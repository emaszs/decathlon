import java.util.EnumMap;

public class AthleteResults {
    public final String name;
    public final EnumMap<SportEvent, String> results;
    public final int totalScore;


    AthleteResults(String name, EnumMap<SportEvent, String> results) {
        this.name = name;
        this.results = results;
        this.totalScore = getTotalScore();
    }

    // TODO should this be private?
    private int getTotalScore() {
        int res = 0;
        for (SportEvent event : SportEvent.values()) {
            res += getEventScore(event);
        }
        return res;
    }

    // TODO should this be private?
    private int getEventScore(SportEvent event) {
        switch (event) {
            case ONE_HUNDRED:
            case FOUR_HUNDRED:
            case HURDLES:
            case FIFTEEN_HUNDRED:
                double totalSeconds;
                if (results.get(event).matches("^.+\\..+\\..+$")) {
                    String[] splitTime = results.get(event).split("\\.");
                    int minutes = Integer.parseInt(splitTime[0]);
                    double seconds = Double.parseDouble(splitTime[1] + "." + splitTime[2]);
                    totalSeconds = minutes * 60 + seconds;
                } else {
                    totalSeconds = Double.parseDouble(results.get(event));
                }
                return (int) (event.a_val * Math.pow((event.b_val - totalSeconds), event.c_val));
            case LONG_JUMP:
            case SHOT_PUT:
            case HIGH_JUMP:
            case DISCUS:
            case POLE:
            case JAVELIN:
                double meters = Double.parseDouble(results.get(SportEvent.LONG_JUMP));
                return (int) (event.a_val * Math.pow((meters - event.b_val), event.c_val));
        }
        return -1;
    }

}
