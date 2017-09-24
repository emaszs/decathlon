import java.util.EnumMap;

/**
 * Represents a single athlete's results in a decathlon competition. That includes both the performance
 * results in each of the 10 events in the competition, as well as a total score which is awarded to the athlete
 * for his/hers performance in all of the events and therefore can be used for comparison between different athletes
 * overall.
 */
class AthleteResults {
    /**
     * Athlete's name
     */
    final String name;

    /**
     * A map for storing athlete's performance in each of the sport events.
     * It is assumed that the athlete finished all of the 10 different events, therefore the number of entries in the
     * map should always be equal to 10, one for each value in the {@link SportEvent} enum.
     */
    final EnumMap<SportEvent, String> results;

    /**
     * Athlete's total score in the competition as calculated by the {@link #getEventScore(SportEvent)} method.
     * This value is set in the constructor.
     */
    final int totalScore;

    /**
     * Constructor.
     *
     * @param name Athlete's name
     * @param results A map containing athlete's performance in each of the 10 sport events.
     */
    AthleteResults(final String name, final EnumMap<SportEvent, String> results) {
        this.name = name;
        this.results = results;
        this.totalScore = getTotalScore();
    }

    /**
     * Returns athlete's total score in the competition by invoking the {@link #getEventScore(SportEvent)}
     * method for each {@link SportEvent} and summing the result.
     *
     * @return Athlete's total score based on his performance.
     * @see #getEventScore(SportEvent)
     */
    private int getTotalScore() {
        int res = 0;
        for (SportEvent event : SportEvent.values()) {
            res += getEventScore(event);
        }
        return res;
    }

    /**
     * Returns athlete's score for a single sport event.
     *
     * The score for track and field events is calculated differently:
     * - For a track event, lower time produces a higher score.
     * - For a field event, higher distance produces a higher score.
     *
     * It is assumed that the time present in the athlete's performance map {@link #results} will be given only in
     * these two formats:
     * - m.s.SS (minutes, seconds, hundredths of a second separated by a dot)
     * - s.SS (seconds, hundredhts of a second separated by a dot)
     *
     * It is also assumed that the distance present in the athlete's performance map {@link #results} will be given in a
     * single format:
     * - m.cm (meters, centimeters separated by a dot)
     *
     * @param event Event for which to calculate the score
     * @return Integer athlete's score for a specific event
     */
    private int getEventScore(final SportEvent event) {
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
        return 0;
    }

}
