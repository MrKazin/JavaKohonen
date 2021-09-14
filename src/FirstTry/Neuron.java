package FirstTry;

import FirstTry.InObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Neuron {
    protected List<Double> parameters;
    protected double radius;
    protected double potential;
    protected double distance;
    protected Set<InObject> clusterSet;

    public Neuron(int numberOfParameters,int numberOfNeurons, List<Double> parametersMins, List<Double> parametersMaxes) {
        parameters = new ArrayList<>();
        for(int i = 0; i < numberOfParameters; i++){
            //parameters.add(ThreadLocalRandom.current().nextDouble(parametersMins.get(i), parametersMaxes.get(i)));
            parameters.add(Math.random());
        }
        radius = 0.5;
        potential = 1.0 / numberOfNeurons;
        distance = 0.0;
        clusterSet = new HashSet<>();
    }

    @Override
    public String toString() {
        return "FirstTry.Neuron{" +
                "parameters=" + parameters +
                ", radius=" + radius +
                ", potential=" + potential +
                ", distance=" + distance +
                '}';
    }
}
