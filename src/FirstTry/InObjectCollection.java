package FirstTry;

import FirstTry.InObject;

import java.util.ArrayList;
import java.util.List;

public class InObjectCollection {
    protected List<InObject> objects;
    protected List<Double> parametersMins;
    protected List<Double> parametersMaxes;

    public InObjectCollection(List<InObject> objects) {
        this.objects = objects;
        parametersMins = new ArrayList<>();
        parametersMaxes = new ArrayList<>();
        for(int i = 0; i < objects.get(0).normalizedParameters.size(); i++){
            parametersMins.add(this.findNormalizedMin(i));
            parametersMaxes.add(this.findNormalizedMax(i));
        }
    }

    public double findNormalizedMin(int parameterIndex){
        double localMin = objects.get(0).normalizedParameters.get(parameterIndex);
        for(InObject object : objects){
            if(object.normalizedParameters.get(parameterIndex) < localMin){
                localMin = object.normalizedParameters.get(parameterIndex);
            }
        }
        return  localMin;
    }

    public double findNormalizedMax(int parameterIndex){
        double localMax = objects.get(0).normalizedParameters.get(parameterIndex);
        for(InObject object : objects){
            if(object.normalizedParameters.get(parameterIndex) > localMax){
                localMax = object.normalizedParameters.get(parameterIndex);
            }
        }
        return  localMax;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n"+"Study Collection:");
        for(InObject object : objects){
            stringBuilder.append(object.toString());
        }
        stringBuilder.append("\n" + "Minimal borders: " + "\n" + parametersMins.toString());
        stringBuilder.append("\n" + "Maximal borders: " + "\n" + parametersMaxes.toString());
        return String.valueOf(stringBuilder);
    }
}
