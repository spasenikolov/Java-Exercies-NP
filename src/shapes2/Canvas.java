package shapes2;

import java.util.*;

public class Canvas {
    private String canvasId;
    List<IShape> shapes;

    public Canvas(String canvasId, List<IShape> shapes){
        this.canvasId = canvasId;
        this.shapes = shapes;
    }



    public String getCanvasId() {
        return canvasId;
    }

    public static Canvas createCanvas(String line, double maxArea) throws IrregularCanvasException{
        String [] parts = line.split("\\s+");
        String canvasId = parts[0];
        List<IShape> shapeList = new ArrayList<>();

        for (int i=1; i<parts.length-1; i++){
            if(parts[i].equals("C")){
                i++;
                shapeList.add(new Circle(Double.valueOf(parts[i])));

            }
            else if(parts[i++].equals("S")){

                shapeList.add(new Rectangle(Double.valueOf(parts[i])));

            }
        }
        if(shapeList.stream().anyMatch(shape -> shape.getTotalArea() > maxArea)) throw new IrregularCanvasException(canvasId, maxArea);

       return new Canvas(canvasId, shapeList);
    }

    public Double getMaxArea(){
       return this.shapes.stream().map(IShape::getTotalArea).max(Comparator.comparingInt(Double::intValue)).orElse(0.0);
    }
    public DoubleSummaryStatistics getStatistics(){
        return this.shapes.stream()
                .mapToDouble(IShape::getTotalArea).summaryStatistics();
    }

    @Override
    public String toString() {
        DoubleSummaryStatistics doubleSummaryStatistics = getStatistics();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s ", this.canvasId));
        stringBuilder.append(String.format("%d %d %d ", this.shapes.size(), countCirles(), countSquares()));
        stringBuilder.append(String.format("%.2f %.2f %.2f", doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getMax(), doubleSummaryStatistics.getAverage()));
        return stringBuilder.toString();

    }

    private Integer countSquares(){
        return this.shapes.stream().filter(shape-> shape.getShapeType().equals("S")).mapToInt(i->1).sum();
    }
    private Integer countCirles(){
        return this.shapes.stream().filter(shape-> shape.getShapeType().equals("C")).mapToInt(i->1).sum();
    }

    public Double getSumArea(){
        return this.shapes.stream().mapToDouble(IShape::getTotalArea).sum();
    }
}

