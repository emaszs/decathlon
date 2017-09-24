public enum SportEvent {
    ONE_HUNDRED("100 meters", 25.4347, 18, 1.81),
    LONG_JUMP("Long jump", 0.14354, 220, 1.4),
    SHOT_PUT("High jump", 51.39, 1.5, 1.05),
    HIGH_JUMP("High jump", 0.8465, 75, 1.42),
    FOUR_HUNDRED("400 meters", 1.53775, 82, 1.81),
    HURDLES("110 meters hurdles", 5.74352, 28.5, 1.92),
    DISCUS("Discus throw", 12.91, 4, 1.1),
    POLE("Pole vault", 0.2797, 100, 1.35),
    JAVELIN("Javelin", 10.14, 7, 1.08),
    FIFTEEN_HUNDRED("1500 meters", 0.03768, 480, 1.85);

    public final String fullName;
    public final double a_val;
    public final double b_val;
    public final double c_val;

    /**
     * Constructor.
     *
     * @param fullName Full and more human-readable event name.
     * @param a_val value for A used in the event score calculations.
     * @param b_val value for B used in the event score calculations.
     * @param c_val value for C used in the event score calculations.
     */
    SportEvent(String fullName, double a_val, double b_val, double c_val) {
        this.fullName = fullName;
        this.a_val = a_val;
        this.b_val = b_val;
        this.c_val = c_val;
    }
}
