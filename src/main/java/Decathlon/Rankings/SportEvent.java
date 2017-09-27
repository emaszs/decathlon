package Decathlon.Rankings;

/**
 * Enum containing the three A,B and C values for each 10 decathlon competition events that are used to calculate each
 * athlete's score.
 */
public enum SportEvent {
    ONE_HUNDRED(25.4347, 18, 1.81),
    LONG_JUMP(0.14354, 220, 1.4),
    SHOT_PUT(51.39, 1.5, 1.05),
    HIGH_JUMP(0.8465, 75, 1.42),
    FOUR_HUNDRED(1.53775, 82, 1.81),
    HURDLES(5.74352, 28.5, 1.92),
    DISCUS(12.91, 4, 1.1),
    POLE(0.2797, 100, 1.35),
    JAVELIN(10.14, 7, 1.08),
    FIFTEEN_HUNDRED(0.03768, 480, 1.85);

    public final double a_val;
    public final double b_val;
    public final double c_val;

    /**
     * Constructor.
     *
     * @param b_val value for B used in the event score calculations.
     * @param c_val value for C used in the event score calculations.
     */
    SportEvent(final double a_val, final double b_val, final double c_val) {
        this.a_val = a_val;
        this.b_val = b_val;
        this.c_val = c_val;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
