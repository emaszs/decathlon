package Decathlon.UI;

import Decathlon.IO.Input;
import Decathlon.IO.Output;
import Decathlon.IO.SemicolonSeparatedInput;
import Decathlon.IO.XmlOutput;
import Decathlon.Rankings.AthleteResults;
import Decathlon.Rankings.RankingBucket;
import Decathlon.Rankings.RankingCalculator;

import java.io.*;
import java.util.ArrayList;

/**
 * Command line interface for producing the decathlon rankings.
 *
 * Reads the input and output file names from either the command line args or prompts the user to enter them if command
 * line args were not provided.
 */
public class CmdLineUI {

    /**
     * Prompts the user to enter the input file name and opens the file for reading.
     *
     * @return {@link Reader} for the input file.
     * @throws IOException If input exception occurred, e.g. file not found.
     */
    public static Reader queryAndOpenInputFile() throws IOException {
        BufferedReader cmdLineInputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the INPUT file path:");
        String path = cmdLineInputReader.readLine();

        return new BufferedReader(new FileReader(path));
    }

    /**
     * Prompts the user to enter the output file name and opens the file for reading.
     *
     * @return {@link Writer} for the output file.
     * @throws IOException If output exception occurred, e.g. out of space.
     */
    public static Writer queryAndOpenOutputFile() throws IOException {
        BufferedReader cmdLineReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the OUTPUT file path:");
        String path = cmdLineReader.readLine();

        return new PrintWriter(path);
    }

    public static void main(String[] args) {
        Reader reader = null;
        Writer writer = null;

        try {
            if (args.length == 2) {
                reader = new BufferedReader(new FileReader(args[0]));
                writer = new PrintWriter(args[1]);
            } else {
                reader = queryAndOpenInputFile();
                writer = queryAndOpenOutputFile();
            }
        } catch (IOException e) {
            System.out.println("Error opening input/output file! Exiting early.");
            e.printStackTrace();
            System.exit(1);
        }

        Input input = new SemicolonSeparatedInput();
        ArrayList<AthleteResults> athleteResultsList = null;
        try {
            athleteResultsList = input.readAthleteResults(reader);
        } catch (IOException e) {
            System.out.println("Error reading input data! Exiting early.");
            e.printStackTrace();
            System.exit(1);
        }

        RankingCalculator<AthleteResults> rankCalc = new RankingCalculator<>();
        ArrayList<RankingBucket<AthleteResults>> rankingBucketList =
                rankCalc.makeSortedRankingBucketList(athleteResultsList);

        int exitStatus = 0;

        Output output = new XmlOutput();
        try {
            output.writeOutput(rankingBucketList, writer);
        } catch (IOException e) {
            System.out.println("Error writing to file!");
            exitStatus = 1;
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Error closing input file!");
            exitStatus = 1;
            e.printStackTrace();
        }

        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing output file!");
            exitStatus = 1;
            e.printStackTrace();
        }

        if (exitStatus == 0) {
            System.out.println("Work completed successfully.");
            System.exit(0);
        } else {
            System.out.println("Something went wrong during the execution, work likely to be incomplete.");
            System.exit(exitStatus);
        }
    }
}
