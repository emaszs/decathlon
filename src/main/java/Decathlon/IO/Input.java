package Decathlon.IO;

import Decathlon.Rankings.AthleteResults;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Abstract class which loads the data about athlete's performance in a decathlon competition.
 */
public abstract class Input {
    /**
     * Reads athlete result the input data.
     *
     * @param reader Stream to read from.
     * @return A list of {@link AthleteResults} generated from the input data.
     * @throws IOException If input exception occurred.
     */
    public abstract ArrayList<AthleteResults> readAthleteResults(Reader reader) throws IOException;
}
