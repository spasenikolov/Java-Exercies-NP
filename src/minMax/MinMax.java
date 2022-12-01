package minMax;

import java.util.Comparator;
import java.util.Objects;

public class MinMax<T extends Comparable<T>>{
    T max;
    T min;
    Integer minNumOfProcessedElements;
    Integer maxNumOfProcessedElements;
    Integer total;

    public MinMax() {
    minNumOfProcessedElements = 0;
    maxNumOfProcessedElements = 0;
    total = 0;
    max = null;
    min = null;
    }
    public void update(T element){
        if(min == null) min = element;
        if(max == null) max = element;
        ++total;
        if(min.compareTo(element) > 0){
            min = element;
            minNumOfProcessedElements = 1;
        }
        else {
            if(min.compareTo(element) == 0){
                minNumOfProcessedElements++;
            }
        }
        if(max.compareTo(element) < 0){
            max = element;
            maxNumOfProcessedElements = 1;
        }
        else {
            if(max.compareTo(element) == 0){
                maxNumOfProcessedElements++;
            }
        }
    }
    public T max(){
        return this.max;
    }
    public T min(){
        return this.min;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d\n",this.min.toString(), this.max.toString(), total-(maxNumOfProcessedElements+minNumOfProcessedElements));
    }
}
