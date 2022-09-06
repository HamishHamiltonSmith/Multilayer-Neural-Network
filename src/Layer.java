import java.util.ArrayList;

public class Layer {

    int size;
    Integer vars;
    Integer prevSize;
    String type;
    String data;
    String activation;

    ArrayList<ArrayList<ArrayList<Double>>> neurons; 
    ArrayList<ArrayList<ArrayList<Boolean>>> liveBool;


    public Layer(int size, Integer prevSize, String type, String activation){
        this.size = size;
        this.type = type;
        this.prevSize = prevSize;
        this.vars = 0;
        this.activation = activation;

        Tools t = new Tools();

        if (this.type.equals("input") == false){ 
            //Generate randomly generated neurons {w:[0],b:[1]}
            neurons = new ArrayList<ArrayList<ArrayList<Double>>>();
            liveBool = new ArrayList<ArrayList<ArrayList<Boolean>>>();

            for (var x=0; x<this.size; x++){
                neurons.add(new ArrayList<ArrayList<Double>>());
                liveBool.add(new ArrayList<ArrayList<Boolean>>());

                //First for weights, second for bias
                neurons.get(x).add(new ArrayList<Double>());
                neurons.get(x).add(new ArrayList<Double>());

                liveBool.get(x).add(new ArrayList<Boolean>());
                liveBool.get(x).add(new ArrayList<Boolean>());

                //Add weights
                for (var y=0; y<this.prevSize; y++){
                    this.vars++;
                    neurons.get(x).get(0).add(t.randRange(1.0));
                    liveBool.get(x).get(0).add(false);
                }

                //Add bias
                this.vars++;
                neurons.get(x).get(1).add(t.randRange(1.0));
                liveBool.get(x).get(1).add(false);
            }
        } else {
            //Configure inputs
        }
    }
}
