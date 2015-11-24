package com.fusion.neural;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by admir on 11/24/2015.
 * Class that represents a neural network
 * It has the following fields:
 * random used for building random synapse weights between the neurons
 * inputLayer a Layer object which represents the input layer of the network
 * outputLayer a Layer object which represents the output layer of the network
 * hiddenLayers a list of layers inbetween
 * finished which is used to mark when building of the weights is finished
 * You cannot add layers after building the weights nor can you build the weights
 * if you have not input and output layers, Ne znam kog kurca ovo sve na engleskom kucam
 */
public class Network {

    private Random random;
    private Layer inputLayer;
    private List<Layer> hiddenLayers;
    private Layer outputLayer;
    private boolean finished;

    /**
     * Initializer constructor
     */
    public Network() {
        this.finished = false;
        this.inputLayer = new Layer();
        this.hiddenLayers = new ArrayList<>();
        this.outputLayer = new Layer();
    }

    /**
     * Constructor that takes two layers as arguments
     * @param inputLayer
     * @param outputLayer
     */
    public Network(Layer inputLayer, Layer outputLayer) {
        this.inputLayer = inputLayer.clone();
        this.outputLayer = outputLayer.clone();
        this.hiddenLayers = new ArrayList<>();
        this.finished = false;
    }

    /**
     * ovdje pocinje sex, pravljenje random tezina sinapsi tj. povezujem neurone i stavljam tezine (graf ko graf)
     * ovo ce nam trebat kada trenirali posto necemo sigurno rucno pravit cijelu mrezu i tezine stavljat
     * @throws NeuralException
     */
    public void build() throws NeuralException {
        if(inputLayer.isEmpty() || outputLayer.isEmpty()) throw new NeuralException("Input and Output layer not set!");
        this.finished = true;

        random = new Random();

        // Prvo gledam ako nema hidden layera samo postavim za input and output layer sinapse
        if(hiddenLayers.isEmpty()) {
            for(Neuron input: inputLayer.getNeurons()) {
                for(Neuron output: outputLayer.getNeurons()) {
                    input.createSynapse(output, random.nextDouble());
                }
            }
            return;
        }

        // Ako ima hidden layer onda stavljam prvo za input i prvi hidden layer
        for(Neuron input: inputLayer.getNeurons()) {
            for(Neuron output: hiddenLayers.get(0).getNeurons()) {
                input.createSynapse(output, random.nextDouble());
            }
        }

        // Sada isto radim samo za svaki hidden layer i sljedeci
        for(int i = 0; i < hiddenLayers.size() - 1 ;++i) {
            Layer first = hiddenLayers.get(i);
            Layer second = hiddenLayers.get(i + 1);
            for(Neuron input: first.getNeurons()) {
                for(Neuron output: second.getNeurons()) {
                    input.createSynapse(output, random.nextDouble());
                }
            }
        }

        // Stavljam za zadnji hidden i output layer
        for(Neuron input: hiddenLayers.get(hiddenLayers.size() - 1).getNeurons()) {
            for(Neuron output: outputLayer.getNeurons()) {
                input.createSynapse(output, random.nextDouble());
            }
        }

    }

    /**
     * Adds a hidden layer
     * @param hiddenLayer
     * @throws NeuralException
     */
    public void addHiddenLayer(Layer hiddenLayer) throws NeuralException {
        if(this.finished) throw new NeuralException("Already finished building neural network!");
        this.hiddenLayers.add(hiddenLayer);
    }

    /**
     * Sets a layer as the input layer
     * @param inputLayer
     * @throws NeuralException
     */
    public void setInputLayer(Layer inputLayer) throws NeuralException {
        if(this.finished) throw new NeuralException("Already finished building neural network!");
        this.inputLayer = inputLayer;
    }

    /**
     * Sets a layer as the output layer
     * @param outputLayer
     * @throws NeuralException
     */
    public void setOutputLayer(Layer outputLayer) throws NeuralException {
        if(this.finished) throw new NeuralException("Already finished building neural network!");
        this.outputLayer = outputLayer;
    }

    /**
     * E ovdje pocinje onaj lijepi sex na LSD-u, dobivanje rezultata
     * @param inputs
     * @return
     * @throws NeuralException
     */
    public double[] evaulate(double[] inputs) throws NeuralException {
        if(inputs.length != inputLayer.getSize()) throw new NeuralException("The number of inputs doesn't match!");

        // Stavljam received vrijednosti neurona iz inputlayera da su jednaki datom inputu
        // i sa njih saljem prve signale(n.send() jebacka metoda)
        for(int i = 0; i < inputs.length; ++i) {
            Neuron n = inputLayer.getNeurons().get(i);
            n.setReceived(inputs[i]);
            //System.out.print(inputs[i] + " "); zanemari ostatke debugovanja
            n.send();
        }

        // Sada jednostavno prolazim kroz sve hidden layere redom i koristim send, zivili grafovi
        for(Layer l: hiddenLayers) {
            for(Neuron n: l.getNeurons()) {
                //System.out.println(n.getReceived());
                //System.out.print(n.getReceived() + " ");
                n.send();
            }
        }

        /*System.out.println();
        System.out.println(outputLayer.getNeurons().get(0).getReceived());*/


        // kupim rezultate outputLayera u niz
        double[] results = new double[outputLayer.getSize()];
        for(int i = 0; i < results.length; ++i) {
            results[i] = outputLayer.getNeurons().get(i).getReceived();
        }

        // resetujem received vrijednosti neurona
        setReceivedToZero();
        return results;
    }

    /**
     * Resets the received values of the neurons
     */
    private void setReceivedToZero() {
        for(Neuron n: inputLayer.getNeurons()) {
            n.setReceived(0);
        }
        for(Layer l: hiddenLayers) {
            for(Neuron n: l.getNeurons()) {
                n.setReceived(0);
            }
        }
        for(Neuron n: outputLayer.getNeurons()) {
            n.setReceived(0);
        }
    }

    /**
     * Pravi sebi kako hos string reprezentaciju ova meni trebala da vidim jel sve radi
     * @return
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for(Neuron n: inputLayer.getNeurons()) {
            str.append(n.getThreshold() + ": ");
            for(Synapse s: n.getSynapses()) {
                str.append(s.getWeight() + " ");
            }
            str.append('\n');
        }

        for(Layer l: hiddenLayers) {
            for(Neuron n: l.getNeurons()) {
                str.append(n.getThreshold() + ": ");
                for(Synapse s: n.getSynapses()) {
                    str.append(s.getWeight() + " ");
                }
                str.append('\n');
            }
        }

        for(Neuron n: outputLayer.getNeurons()) {
            str.append(n.getThreshold() + ": ");
            for(Synapse s: n.getSynapses()) {
                str.append(s.getWeight() + " ");
            }
            str.append('\n');
        }

        return str.toString();
    }

}
