package shapes2;

public class IrregularCanvasException extends RuntimeException {
   // Canvas [canvas_id] has a shape with area larger than [max_area].

    public IrregularCanvasException(String canvasId, double maxArea) {
        super(String.format("Canvas %s has a shape with area larger than %.2f", canvasId, maxArea));
    }
}
