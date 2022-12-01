package stackedCanvas;

public class Rectangle extends Shape{
    float width;
    float height;

    public Rectangle(String id, Color color, float width, float height) {
        super(id, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public String getType() {
        return "R";
    }

    @Override
    public void scale(float scaleFactor) {
        this.width *= scaleFactor;
        this.height *=scaleFactor;
    }

    @Override
    public float weight() {
        return this.width * this.height;
    }

}
