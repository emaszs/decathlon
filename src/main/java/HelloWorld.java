import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class HelloWorld {
    public static void main(String[] args) {
        print(System.out);
    }

    public static void print(PrintStream out) {
        out.println("Hello world again!");

        for (SportEvent e : SportEvent.values()) {
            System.out.println(e.fullName);
        }

        SemicolonSeparatedInputImpl inp = new SemicolonSeparatedInputImpl();

        String path = "C:\\Users\\phoni\\Desktop\\Decathlon_input.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<Athlete> res = inp.readAthleteResults(br);
            for (Athlete r : res) {
                System.out.println(r.results);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
