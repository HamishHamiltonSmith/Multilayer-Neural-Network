//Configuration file where the network is created
//NOTE - Currently a callback is active

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Main {
    public static void main(String[] args) {

        //Activation choices: "relu" & "tanh"
        Layer l1 = new Layer(3,1,"hidden","none");
        Layer l2 = new Layer(5, 3,"hidden","relu");
        Layer l3 = new Layer(5, 5,"hidden","relu");
        Layer l4 = new Layer(1,5,"output","none");


        ArrayList <Layer> layers = new ArrayList<>();
        layers.add(l1);
        layers.add(l2);
        layers.add(l3);
        layers.add(l4);

        
        Network nw = new Network(layers);

        ArrayList<Double> x;
        ArrayList<Double> y;

        x = new ArrayList<>();
        y = new ArrayList<>();

        
        int change = 1;

        List<Double> dataset = Arrays.asList(new Double[]{1.0, 1.5, 0.9, 3.0, 5.0, 8.0, 10.0, 12.0, 13.0,13.4,13.0, 10.0, 3.5});
        
        //Notice how the network trains on the function up to x=20, you can increase this to approximate more, but note accuracy will go down
        for (int i = 0; i<dataset.size(); i+=change){
            x.add(Double.valueOf(i));

            //Change this statement to edit the training data
            y.add(dataset.get(i));
        }

        //Whilst 1000 epochs seems large, the optimization function is fast but a little blunt, so whilst the network will still train
        //quickly (around 5s here) it needs more epochs to develop accuracy.
        Integer epochs = 1000;
        nw.train(x,y,epochs);
 

        ArrayList<Double> inp = new ArrayList<>();
        ArrayList<Double> input = new ArrayList<>();

        ArrayList<Double> output = new ArrayList<>();

        input.add(0.0);
        
        for (var i = 0.0; i<40; i+=0.2){
            input.set(0,i);

            inp.add(i);
            output.add(nw.predict(input).get(0));
        }


        new Graph(inp,output,x,y);
    }
}
