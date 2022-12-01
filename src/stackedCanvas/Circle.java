package stackedCanvas;

public class Circle extends Shape{
    float radius;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public String getType() {
        return "C";
    }

    @Override
    public void scale(float scaleFactor) {
        this.radius *= scaleFactor;
    }

    @Override
    public float weight() {
        return (float) (Math.PI*Math.pow(radius, 2));
    }


}
