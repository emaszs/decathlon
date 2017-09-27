package Decathlon.Rankings;

import org.junit.Test;

import java.util.EnumMap;

import static org.junit.Assert.*;

public class AthleteResultsTest {
    private AthleteResults testAthRes = createTestAthleteResults();

    public static AthleteResults createTestAthleteResults() {
        String name = "Siim Susi";

        EnumMap<SportEvent, String> resultsEnum = new EnumMap<>(SportEvent.class);
        resultsEnum.put(SportEvent.ONE_HUNDRED, "12.61");
        resultsEnum.put(SportEvent.LONG_JUMP, "5.00");
        resultsEnum.put(SportEvent.SHOT_PUT, "9.22");
        resultsEnum.put(SportEvent.HIGH_JUMP, "1.50");
        resultsEnum.put(SportEvent.FOUR_HUNDRED, "60.39");
        resultsEnum.put(SportEvent.HURDLES, "16.43");
        resultsEnum.put(SportEvent.DISCUS, "21.60");
        resultsEnum.put(SportEvent.POLE, "2.60");
        resultsEnum.put(SportEvent.JAVELIN, "35.81");
        resultsEnum.put(SportEvent.FIFTEEN_HUNDRED, "5.25.72");

        return new AthleteResults(name, resultsEnum);
    }

    @Test
    public void getTotalScore() {
        testAthRes = createTestAthleteResults();

        assertEquals(3165, testAthRes.calculateTotalScore(testAthRes.results));
    }

    @Test
    public void getEventScore_fieldEvent_returnsCorrectResult() {
        testAthRes = createTestAthleteResults();

        assertEquals(536, testAthRes.calculateEventScore(SportEvent.ONE_HUNDRED));
    }

    @Test
    public void getEventScore_trackEvent_returnsCorrectResult() {
        testAthRes = createTestAthleteResults();

        assertEquals(302, testAthRes.calculateEventScore(SportEvent.DISCUS));
    }
}