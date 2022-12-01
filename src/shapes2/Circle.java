package shapes2;

import java.util.Map;

    public class Circle implements IShape{
        Double radius;

        public Circle(Double radius) {
            this.radius = radius;
        }

        @Override
        public Double getTotalArea() {
            return Math.PI*Math.pow(radius,2);
        }

        @Override
        public String getShapeType() {
            return "C";
        }
    }
