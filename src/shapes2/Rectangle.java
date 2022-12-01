package shapes2;

public class Rectangle implements IShape{
    Double side;

    public Rectangle(Double side) {
        this.side = side;
    }

    @Override
    public Double getTotalArea() {
        return Math.pow(this.side,2);
    }

    @Override
    public String getShapeType() {
        return "S";
    }
}
