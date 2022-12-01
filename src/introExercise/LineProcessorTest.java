package introExercise;

import java.io.IOException;

public class LineProcessorTest {
    public static void main(String[] args) {
        LineProcessor lineProcessor = new LineProcessor();

        try {
            lineProcessor.readLines(System.in, System.out, 'a');
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
