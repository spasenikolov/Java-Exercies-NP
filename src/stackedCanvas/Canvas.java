package stackedCanvas;

import java.util.ArrayList;
import java.util.List;

public class Canvas {

    List<Shape> shapes;

    public Canvas() {
        shapes = new ArrayList<>();
    }

    public void add(String id, Color color, float radius) {

        Circle circle = new Circle(id, color, radius);
        this.shapes.removeIf(shape -> shape.id.equals(circle.id));
        int indexToAdd = shapes.size();
        for(Shape s: this.shapes){
            if(s.weight()<circle.weight()){
                indexToAdd = this.shapes.indexOf(s);
                break;
            }
        }

        this.shapes.add(indexToAdd, circle);
    }

    void add(String id, Color color, float width, float height) {
        Rectangle rectangle = new Rectangle(id, color, width, height);
        this.shapes.removeIf(shape -> shape.id.equals(rectangle.id));

        int indexToAdd = shapes.size();
        for(Shape s: this.shapes){
            if(s.weight()< rectangle.weight()){
                indexToAdd = this.shapes.indexOf(s);
                break;
            }
        }
        this.shapes.add(indexToAdd, rectangle);
    }

    void scale(String id, float scaleFactor) {
        Shape toScale = this.shapes.stream()
                .filter(shape -> shape.id.equals(id))
                .findFirst()
                .get();
        toScale.scale(scaleFactor);
        if (toScale.getType().equals("C") ){
            Circle circle = (Circle) toScale;
            add(circle.id, circle.color, circle.radius);
        }
        else if(toScale.getType().equals("R")){
            Rectangle rectangle = (Rectangle) toScale;
            add(rectangle.id, rectangle.color, rectangle.width, rectangle.height);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.shapes.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
