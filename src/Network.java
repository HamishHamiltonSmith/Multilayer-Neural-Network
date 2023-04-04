//Where the magic happens, read about how the network works on the github README!


import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.*;

public class Network {

    ArrayList<Layer> layers;
    Tools t = new Tools();
    ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> fullNetwork;
    ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> fullBool;
    Integer vars = 0;

    public Network(ArrayList<Layer> layers){
        this.layers = layers;

        fullNetwork = new ArrayList<>();
        fullBool = new ArrayList<>();


        for (var x=0; x<this.layers.size(); x++){
            fullNetwork.add(this.layers.get(x).neurons);
            fullBool.add(this.layers.get(x).liveBool);
            
            vars += this.layers.get(x).vars;
        }

        System.out.println(vars);
    }

    public void structure(){
        for (var x=0; x<this.layers.size(); x++){
            System.out.println("Layer: " + x+1);
            System.out.println("Type: " + this.layers.get(x).type);
            System.out.println("Size: " + this.layers.get(x).size);
            System.out.println("\n-------------------");
        }
    }

    public void showLayer(int index){
        System.out.println(this.layers.get(index).neurons.toString());
    }

    public void saveAll(String path){
        File f = new File(path);

        try {
            f.createNewFile();

            FileWriter fw = new FileWriter(f);
            fw.write(fullNetwork.toString());
            fw.close();
        } catch (Exception e){
            System.out.println("Error: " + e);
        }

    }

    public void loadNW(String path){
        File f = new File(path);

        try {
            String data = "";

            Scanner fr = new Scanner(f);
            while (fr.hasNextLine()) {
              data += fr.nextLine() + "\n";
            }

            System.out.println(data);

            fr.close();
            

        } catch (Exception e){
            System.out.println("Error: " + e);
        }

    }

    public void resetBool(){
        //Every layer
        for (var x = 0; x<fullBool.size(); x++){
            //Every neuron
            for (var y = 0; y<fullBool.get(x).size(); y++){
                //Weights

                for (var i = 0; i<fullBool.get(x).get(y).get(0).size(); i++){
                    fullBool.get(x).get(y).get(0).set(i,false);
                }

                fullBool.get(x).get(y).get(1).set(0,false);
            }
        }
    }

    public String SGD(ArrayList<Double> inputs, ArrayList<Double> expected){

        Double dx = 0.000000001;
        Double rate = 0.001;
        Double parDerivative;
        Double v1;
        Double v2;

        Integer count = 0;

        while (true) {

            if (count >= vars){
                resetBool();
                System.out.println("Cost: " + C(inputs,expected,fullNetwork));

                break;
            }

            //loop through layers
            for (var x=0; x<fullNetwork.size(); x++){

                //Loop through neurons
                for (var y=0; y<fullNetwork.get(x).size(); y++){

                    ArrayList<Double> weights = fullNetwork.get(x).get(y).get(0);
                    Double bias = fullNetwork.get(x).get(y).get(1).get(0);

                    //Loop through weights
                    for (var i=0; i<weights.size(); i++){

                        //Calculate partial derivative
                        
                        if (fullBool.get(x).get(y).get(0).get(i) != true) {
                            v1 = C(inputs,expected,fullNetwork);
                            fullNetwork.get(x).get(y).get(0).set(i,weights.get(i)+dx);
                            v2 = C(inputs,expected,fullNetwork);
                            fullNetwork.get(x).get(y).get(0).set(i,weights.get(i)-dx);
                            

                            parDerivative = (v2-v1)/dx;

                            if (parDerivative > -0.005 && parDerivative < 0.005){
                                fullBool.get(x).get(y).get(0).set(i,true);
                                count++;
                            } else if (parDerivative > 0){
                                fullNetwork.get(x).get(y).get(0).set(i,weights.get(i)-rate);
                            } else{
                                fullNetwork.get(x).get(y).get(0).set(i,weights.get(i)+rate);
                            }
                        } 

                    }

                    //Do bias

                    if (fullBool.get(x).get(y).get(1).get(0) != true){
                        v1 = C(inputs,expected,fullNetwork);
                        fullNetwork.get(x).get(y).get(1).set(0,bias+dx);
                        v2 = C(inputs,expected,fullNetwork);
                        fullNetwork.get(x).get(y).get(1).set(0,bias-dx);

                        parDerivative = (v2-v1)/dx;

                        

                        if (parDerivative > -0.005 && parDerivative < 0.005){
                            count++;
                            fullBool.get(x).get(y).get(1).set(0,false);
                        } else if (parDerivative > 0){
                            fullNetwork.get(x).get(y).get(1).set(0,bias-rate);
                        } else {
                            fullNetwork.get(x).get(y).get(1).set(0,bias+rate);
                        }
                    }
                }
            }
        }

        return "NaN";
    }

    public Double C(ArrayList<Double> inputs, ArrayList<Double> expected, ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> modNetwork){

        Double cost = 0.0; 


        for (var x=0; x<inputs.size(); x++){
            ArrayList<Double> results = through(new ArrayList<>(Arrays.asList(inputs.get(x))),modNetwork);
            
            cost += Math.pow(t.sub(new ArrayList<>(Arrays.asList(expected.get(x))), results),2);
        }

        cost = cost/(inputs.size());

        return cost;
    }



    public void train(ArrayList<Double> xTrain, ArrayList<Double> yTrain, int epochs){
        
        for (var x=0; x<epochs; x++){

            System.out.println("Epoch [" + x + "]");

            ArrayList<Double> batchExpected = new ArrayList<>();
            ArrayList<Double> inputs = new ArrayList<>();
            int samples = 10;

            //Selecting inputs
            for (var y=0; y<samples; y++){
                int c = t.randInt(xTrain.size()-1);
                inputs.add(xTrain.get(c));

                batchExpected.add(yTrain.get(c));
            }

            String res = SGD(inputs,batchExpected);
            if (res == "B"){
                break;
            }
        }


    }

    public ArrayList<Double> predict(ArrayList<Double> num){

        return through(num,fullNetwork);
    }

    public ArrayList<Double> through(ArrayList<Double> input, ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> currNetwork){

        Tools t = new Tools();

        //a - contains previous activations
        ArrayList<Double> a = new ArrayList<>();

        for (var i = 0; i<input.size(); i++){
            a.add(input.get(i));
        }

        ArrayList<Double> next;
        Double value;

        //Loop through layers
        for (var x=0; x<fullNetwork.size(); x++) {

            next = new ArrayList<>();

            for (var y=0; y<fullNetwork.get(x).size(); y++){
                
                ArrayList<Double> weights = fullNetwork.get(x).get(y).get(0);
                ArrayList<Double> bias = fullNetwork.get(x).get(y).get(1);

                value = t.weightedSum(weights, a, bias);

                if (this.layers.get(x).activation.equals("relu")){
                    if (value < 0){
                        next.add(0.0);
                    } else {
                        next.add(value);
                    }
                } else if (this.layers.get(x).activation.equals("tanh")){
                    next.add(Math.tanh(value));
                }  else {
                    next.add(value);
                }
            }

            a = next;

        }


        return a;
    }
}
