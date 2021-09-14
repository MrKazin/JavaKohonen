package FirstTry;

import FirstTry.InObject;
import FirstTry.InObjectCollection;
import FirstTry.Network;
import FirstTry.Neuron;

import java.util.*;

public class Start {
    public static void main(String[] args) {
        InObject light1 = new InObject(Arrays.asList(4,4,1035,1370,39));
        InObject light2 = new InObject(Arrays.asList(4,4,1360,1420,43));
        InObject light3 = new InObject(Arrays.asList(2,4,1630,1317,55));
        InObject light4 = new InObject(Arrays.asList(4,4,1400,1465,50));
        InObject light5 = new InObject(Arrays.asList(5,4,1400,1640,55));
        InObject light6 = new InObject(Arrays.asList(3,4,665,1300,30));
        InObject light7 = new InObject(Arrays.asList(4,4,1160,1500,50));
        InObject light8 = new InObject(Arrays.asList(5,4,1696,1630,60));
        InObject light9 = new InObject(Arrays.asList(5,4,2350,1745,80));
        InObject light10 = new InObject(Arrays.asList(4,4,1300,1480,50));

        InObject cargo1 = new InObject(Arrays.asList(2,6,11990,3470,210));
        InObject cargo2 = new InObject(Arrays.asList(2,4,4900,2400,170));
        InObject cargo3 = new InObject(Arrays.asList(2,6,22000,3467,550));
        InObject cargo4 = new InObject(Arrays.asList(2,6,18750,4000,500));
        InObject cargo5 = new InObject(Arrays.asList(2,6,8265,2715,330));
        InObject cargo6 = new InObject(Arrays.asList(2,6,40000,2650,700));
        InObject cargo7 = new InObject(Arrays.asList(2,4,7400,2190,90));
        InObject cargo8 = new InObject(Arrays.asList(2,4,3500,2220,90));
        InObject cargo9 = new InObject(Arrays.asList(2,4,2000,1989,80));
        InObject cargo10 = new InObject(Arrays.asList(2,6,12330,2750,800));

        InObject special1 = new InObject(Arrays.asList(2,4,6000,3200,40));
        InObject special2 = new InObject(Arrays.asList(1,4,2800,2215,55));
        InObject special3 = new InObject(Arrays.asList(2,6,24900,2830,165));
        InObject special4 = new InObject(Arrays.asList(2,4,6300,2785,130));
        InObject special5 = new InObject(Arrays.asList(2,4,6500,3050,64));
        InObject special6 = new InObject(Arrays.asList(1,8,11075,3475,540));
        InObject special7 = new InObject(Arrays.asList(2,4,20500,3915,210));
        InObject special8 = new InObject(Arrays.asList(2,4,2300,2370,74));
        InObject special9 = new InObject(Arrays.asList(2,4,5950,3500,100));
        InObject special10 = new InObject(Arrays.asList(2,4,8350,2940,135));

         InObject light11 = new InObject(Arrays.asList(1035,1370));
        InObject light22 = new InObject(Arrays.asList(1360,1420));

        InObjectCollection collection = new InObjectCollection(Arrays.asList(light1,light2,light3,light4,light5,light6,light7,light8,light9,light10,
                                                                             cargo1,cargo2,cargo3,cargo4,cargo5,cargo6,cargo7,cargo8,cargo9,cargo10,
                                                            special1,special2,special3,special4,special5,special6,special7,special8,special9,special10));
        /*FirstTry.InObjectCollection collection = new FirstTry.InObjectCollection(Arrays.asList(light11,light22));
        System.out.println(collection.toString());*/


        Network kohonenNetwork = new Network(5,25,collection.parametersMins,collection.parametersMaxes);

        System.out.println(kohonenNetwork.toString());
        kohonenNetwork.trainNetwork(collection.objects, 0.5, 1.1, 10, 0.75, 0.0001);
        System.out.println(kohonenNetwork.toString());

        for(InObject object : collection.objects){
            kohonenNetwork.check_object(object);
        }

        for(Neuron neuron : kohonenNetwork.layer ){
            System.out.println(neuron.clusterSet);
        }

        /*System.out.println(collection.objects.toString());
        System.out.println("-----------------------------------------------------------");
        kohonenNetwork.test(collection.objects);*/


    }
}
