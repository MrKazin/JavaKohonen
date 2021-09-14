package FirstTry;

import org.w3c.dom.ls.LSOutput;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

public class InObject {
    protected List<Integer> originalParameters;
    protected List<Double> normalizedParameters;

    public InObject(List<Integer> originalParameters) {
        this.originalParameters = originalParameters;
        normalizedParameters = new ArrayList<>();
        double divider = 0.0;
        for(Integer integer : originalParameters){
            divider += Math.pow(integer,2);
        }
        for(Integer integer : originalParameters){
            normalizedParameters.add(integer / Math.sqrt(divider));
        }
    }

    /*@Override
    public String toString() {
        return "\n\n" + "FirstTry.InObject:"+ this.hashCode()+ "\n" + "originalParameters=" + originalParameters + "\n" + "normalizedParameters=" + normalizedParameters;
    }*/
    @Override
    public String toString() {
        return "\n"+originalParameters;
    }

}
