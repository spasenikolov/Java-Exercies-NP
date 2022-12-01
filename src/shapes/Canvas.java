package shapes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Canvas {
    String id;
    List<Integer> sides;

    Integer totalPerimeter;

    public Canvas(String line) {
        List<String> fromMethod = createCanvasFromLine(line);
        List<Integer> forSides = fromMethod.stream().skip(1).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
        this.id = fromMethod.get(0);
        this.sides = forSides;
        this.totalPerimeter = sides.stream().mapToInt(side->4*side).sum();

    }

    private List<String> createCanvasFromLine(String line){
        String[] parts = line.split(" ");
        return Arrays.stream(parts)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return id + " " + sides.size() + " " + totalPerimeter;
    }
}
