//Configuration file where the network is created

import java.util.ArrayList;


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

        for (var i = 0.0; i<20; i+=0.5){
            x.add(i);

            //Change this statement to edit the training data
            y.add(0.05*-i*i);
        }

        nw.train(x,y,1000);

        ArrayList<Double> inp = new ArrayList<>();
        ArrayList<Double> input = new ArrayList<>();

        ArrayList<Double> output = new ArrayList<>();

        input.add(0.0);
        
        for (var i = 0.0; i<20; i+=1){
            input.set(0,i);

            inp.add(i);
            output.add(nw.predict(input).get(0));
        }


        new Graph(inp,output,x,y);
    }
}
