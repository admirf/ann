package com.fusion.neural;

/**
 * Created by admir on 11/24/2015.
 * Class for holding useful static methods
 */
public class NeuralUtil {

    public static String binaryResultToString(double[] result) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < result.length; ++i)
            str.append(result[i]);

        return str.toString();
    }

    /**
     * Method that returns a neural network made for calculating XOR of binary values
     * @return
     * @throws NeuralException
     */
    public static Network createXOR() throws NeuralException {
        Neuron in1 = new Neuron();
        Neuron in2 = new Neuron();

        Neuron hid1 = new Neuron(1.5);
        Neuron hid2 = new Neuron(0.5);

        in1.createSynapse(hid1, 1);
        in1.createSynapse(hid2, 1);
        in2.createSynapse(hid1, 1);
        in2.createSynapse(hid2, 1);

        Neuron out = new Neuron(0.5);
        hid1.createSynapse(out, -1);
        hid2.createSynapse(out, 1);

        Layer input = new Layer(in1, in2);
        Layer hidden = new Layer(hid1, hid2);
        Layer output = new Layer(out);

        Network xor = new Network(input, output);
        xor.addHiddenLayer(hidden);

        return xor;
    }

}
