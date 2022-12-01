package shapes2;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class ShapesApplication {

    double maxArea;
    List<Canvas> canvases;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
    }

    public void readCanvases(InputStream in){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        this.canvases = bufferedReader.lines()
                .map(line->{
                    try {
                       return Canvas.createCanvas(line,maxArea);
                    }
                    catch (IrregularCanvasException e){
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingDouble(Canvas::getSumArea).reversed())
                .collect(Collectors.toList());

    }

    public void printCanvases(OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        canvases.forEach(printWriter::println);
        printWriter.close();

    }
}
