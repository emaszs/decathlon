public enum SportEvent {
    ONE_HUNDRED("100 meters"),
    LONG_JUMP("Long jump"),
    SHOT_PUT("High jump"),
    HIGH_JUMP("High jump"),
    FOUR_HUNDRED("400 meters"),
    HURDLES("110 meters hurdles"),
    DISCUS("Discus throw"),
    POLE("Pole vault"),
    JAVELIN("Javelin"),
    FIFTEEN_HUNDRED("1500 meters");

    public final String fullName;

    SportEvent(String fullName) {
        this.fullName = fullName;
    }
}
