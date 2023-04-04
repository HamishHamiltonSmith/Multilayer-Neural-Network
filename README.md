# Neural network for polynomial regression

## Intro

This is a multilayer neural network made from scratch in java. The code will also visualise network predictions compared to actuall values once training is complete.The network is a result of a good 5 months researching of calculus among other things but finally this project is finished (for now!). 

Below you can see some examples, an explanation of the network and a guide on how to get started.


## Getting started

To start, download this repo and run main.java to start the network at its default configuration. The input data is at default generated using the equation `y=f(i)=0.01*-i*i` but you can change this from main.java (from here you can also change/add/control layers, activations, epoch count, data size and graphing among other things). Comments in the code will direct you to these attributes. The network will stop sfter 1000 epochs, and will then graph the results. ***Note that around 30% of the time the network will fail to optimize fully, if this happens just re-run the program.*** This happens due to dificulty descending cost - imagine a ball rolling and getting stuck on a bit of flat ground - or in more extreme cases, falling into a hole.

If you are confident with java, have a go at changing up the network layers and other parameters (some, such as learning rate, sample size and partial derivative threshold are stored in Network.java instead). A little basic info if you want to do this, there are 3 activation functions you can use for a given layer these are:

- tanh
- reLU
- linear (no activation)

To create a new layer, use the Layer class, (`new Layer(size,prevsize,type,activation)`), you then must add this to the layers<>() arraylist. Note here that size is the number of neurons in the layer and prevsize is the number that were in the last layer, type is not required so null works find. Remember that Tanh and reLU will both return non-linear outputs, but if you want a linear prediction just put null in the activation field. 

## Behind the scenes

Now for the best bit. Firstly, I'm not going to explain everything about how a neural network works here, however, this site provides a fantastic and in-depth analysis of how neural networks work: http://neuralnetworksanddeeplearning.com/. 

The network starts by randomly initialising its parameters (between 0 and 1) and then selecting random input samples along with their corresponding outputs. These along with all network parameters (weights and biases) are passed to the SGD algorithm (schotastic gradient descent). This basically looks at how tweaking network parameters effects the cost function (MSE), and figures out the downward direction for each cost variable through the use of multivariable calculus. 

I have made a visualisation for this which you can see here: https://replit.com/@HamishHamiltonS/Visualised-Gradient-Descent?v=1. The network repeats this for diferent input samples for each epoch. Over time the networks predictions increase in accuracy as it fine tunes weights and biases. Swing is then used to produce a graph of these results.


## Examples

![Example](https://github.com/HamishHamiltonSmith/Multilayer-Neural-Network/blob/main/examples/Screenshot%202022-09-06%2019.33.46.png)
