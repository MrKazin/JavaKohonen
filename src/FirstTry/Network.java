package FirstTry;

import FirstTry.InObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Network {
    protected List<Neuron> layer;

    public Network(int numberOfParameters, int numberOfNeurons, List<Double> parametersMins, List<Double> parametersMaxes) {
        layer = new ArrayList<>();
        for(int i = 0; i < numberOfNeurons;i++){
            layer.add(new Neuron(numberOfParameters,numberOfNeurons,parametersMins,parametersMaxes));
        }
    }


    public void trainNetwork(List<InObject> list, double learningRate, double decreasingRate, int ages, double pMin, double eps){
        int listSize = list.size();
        int current_age = 0;
        double summary_difference = 0.0;
        do {
            summary_difference = 0.0;
            current_age +=1;
            Collections.shuffle(list);
            for(InObject currentObject : list){
                this.calculateDistancesForNeurons(currentObject);
                Neuron winner = this.findWinner(currentObject, pMin);

                List<Integer> indexesOfNeighbours = this.findNeighbors(winner);
                summary_difference = this.moveWinner(currentObject, winner, learningRate);
                this.moveNeighbours(currentObject, indexesOfNeighbours, (learningRate / decreasingRate) );
            }
            learningRate /= current_age;
        }
        while (current_age != ages && summary_difference / listSize > eps);
    }

    public void calculateDistancesForNeurons(InObject object){
        for(Neuron neuron : layer){
            double distanceValue = 0.0;
            for(int i  = 0; i < object.normalizedParameters.size(); i ++){
                double inputParameter = object.normalizedParameters.get(i);
                double neuronParameter = neuron.parameters.get(i);
                distanceValue += Math.pow((neuronParameter-inputParameter),2);
            }
            neuron.distance = Math.sqrt(distanceValue);
        }
    }

    public Neuron findWinner(InObject object, double pMin){
        Neuron winner = layer.get(0);
        for(Neuron neuron : layer){
            if(layer.indexOf(neuron) != 0){
                if(neuron.potential < pMin)
                    continue;
                if(layer.get(0).potential < pMin)
                    winner = neuron;
                if(neuron.distance < winner.distance)
                    winner = neuron;
            }
        }
        //System.out.println("winner index: " + layer.indexOf(winner) + ", potential:" + winner.potential);
        double checkWinnerPotential = winner.potential - pMin;
        winner.potential = checkWinnerPotential > 0 ? checkWinnerPotential : 0;


        for(Neuron neuron : layer){
            if(layer.indexOf(neuron) != layer.indexOf(winner)){
                double checkLoserPotential = neuron.potential + (1.0/layer.size());
                neuron.potential = checkLoserPotential < 1 ? checkLoserPotential : 1;
            }
        }
        return winner;
    }

    public List<Integer> findNeighbors(Neuron winner){
        List<Integer> indexesOfNeighbours = new ArrayList<>();
        for(Neuron neuron : layer){
            if(layer.indexOf(neuron) != layer.indexOf(winner)){
                double distanceValue = 0.0;
                for(int i =0; i < winner.parameters.size(); i++){
                    double winnerParameter = winner.parameters.get(i);
                    double neuronParameter = neuron.parameters.get(i);
                    distanceValue += Math.pow((neuronParameter - winnerParameter),2);
                }
                distanceValue = Math.sqrt(distanceValue);
                if(distanceValue < winner.radius)
                    indexesOfNeighbours.add(layer.indexOf(neuron));
            }
        }
        return indexesOfNeighbours;
    }

    public double moveWinner(InObject object, Neuron winner, double learningRate){
        double summary_defference = 0.0;
        for(int i =0; i < object.normalizedParameters.size(); i++){
            double difference = object.normalizedParameters.get(i) - winner.parameters.get(i);
            summary_defference += Math.pow(difference,2);
            double move_value = learningRate * difference;
            winner.parameters.set(i, (winner.parameters.get(i) + move_value) );
        }
        winner.radius/=2;
        return summary_defference;
    }

    public void moveNeighbours(InObject object, List<Integer> indexesOfNeighbours, double learningRate){
        for(Integer indexOfNeighbour : indexesOfNeighbours){
            Neuron currentNeighbour = layer.get(indexOfNeighbour);
            for(int i =0; i < object.normalizedParameters.size(); i++){
                double difference = object.normalizedParameters.get(i) - currentNeighbour.parameters.get(i);
                double move_value = learningRate * difference;
                currentNeighbour.parameters.set(i, (currentNeighbour.parameters.get(i) + move_value) );
            }
        }
    }

    public void check_object(InObject object){
        double minDistance = 10;
        Neuron winner = layer.get(0);
        for(Neuron neuron : layer) {
            double distanceValue = 0.0;
            for (int i = 0; i < object.normalizedParameters.size(); i++) {
                double inputParameter = object.normalizedParameters.get(i);
                double neuronParameter = neuron.parameters.get(i);
                distanceValue += Math.pow((neuronParameter - inputParameter), 2);
            }
            distanceValue = Math.sqrt(distanceValue);
            neuron.distance = distanceValue;
            if(distanceValue<minDistance){
                minDistance = distanceValue;
                winner = neuron;
            }
        }
        winner.clusterSet.add(object);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n"+"FirstTry.Network:" + "\n");
        for(Neuron neuron : layer){
            stringBuilder.append(neuron.toString()+"\n");
        }
        stringBuilder.append("\n");
        return String.valueOf(stringBuilder);
    }
}
