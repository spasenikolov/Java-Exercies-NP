package shapes;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShapesApplication {

     private List<Canvas> canvasList;

    public ShapesApplication() {
        this.canvasList = new ArrayList<>();
    }

    public int readCanvases(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        bufferedReader.lines().forEach(canvas->this.canvasList.add(new Canvas(canvas)));
        return (int) canvasList.stream().mapToInt(canvas->canvas.sides.size()).sum();

    }

    public void printLargestCanvasTo(PrintStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(findLargestCanvas());
        printWriter.flush();

    }

    private Canvas findLargestCanvas(){
       return canvasList.stream().max(Comparator.comparingInt(canvas-> canvas.sides.stream().mapToInt(s-> 4*s).sum())).get();
    }
}
