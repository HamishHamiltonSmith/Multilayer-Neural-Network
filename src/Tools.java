import java.util.ArrayList;

public class Tools {
    public Double dot(ArrayList<Double> a, ArrayList<Double> w){

        Double fin = 0.0;

        if (a.size() == w.size()) { 
            for (var x = 0; x<a.size(); x++){
                fin += a.get(x) * w.get(x);
            }

            return fin;

        } else {
            System.out.println("Error: Dot product must be on arrays of equal length");

            return -1.0;
        }
    }

    public Double sub(ArrayList<Double> a, ArrayList<Double> b){

        Double fin = 0.0;

        for (var x=0; x<a.size(); x++){
            fin += a.get(x) - b.get(x);
        }

        return fin;
    }

    public int randInt(int max){
        Double base2= Math.floor(Math.random()*max);

        return base2.intValue();
    }

    public Double randRange(Double max){
        Double base1 = Math.random();

        Double base2 = Math.random()*max;
        
        if (base1 > 0.5){
            base2 *= -1;
        }

        return base2;
    }

    public Double weightedSum(ArrayList<Double>weights, ArrayList<Double>activations, ArrayList<Double>bias){
        return dot(activations,weights) + bias.get(0);
    }

    public Double sigWeightedSum(ArrayList<Double>weights, ArrayList<Double>activations, ArrayList<Double>bias){
        Double fin = dot(activations,weights) + bias.get(0);
        return 1/(1+Math.pow(Math.E,-fin));
    }
}
