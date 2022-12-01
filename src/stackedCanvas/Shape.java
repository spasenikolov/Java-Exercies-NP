package stackedCanvas;

public abstract class Shape implements Stackable, Scalable{
    String id;
    Color color;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }
    public abstract String getType();
    @Override
    public String toString() {
        return String.format("%s: %-5s %10s %-10.2f\n", this.getType(), this.id, this.color, this.weight());
    }
}
