import java.util.EnumMap;

public class Athlete {
    EnumMap<SportEvent, String> results = new EnumMap(SportEvent.class);
    String name;

    Athlete(String name, EnumMap<SportEvent, String> results) {
        this.name = name;
        this.results = results;
    }

    public int getTotalScore() {
        //TODO
        return -1;
    }

}
