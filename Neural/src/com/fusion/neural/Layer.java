package com.fusion.neural;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by admir on 11/24/2015.
 *
 * Class that represents a neural layer
 *
 */
public class Layer {

    private List<Neuron> layer;

    /**
     * Constructor with vararg neurons
     * @param neurons
     */
    public Layer(Neuron... neurons) {
        layer = Arrays.asList(neurons);
    }

    /**
     * Constructor that takes a List as argument
     * @param li
     */
    public Layer(List<Neuron> li) {
        this.layer = new ArrayList<>(li);
    }

    /**
     * returns the number of neurons in the layer
     * @return
     */
    public int getSize() {
        return this.layer.size();
    }

    /**
     * adds a Neuron to the layer
     * @param n
     */
    public void addNeuron(Neuron n) {
        this.layer.add(n);
    }

    /**
     * adds multiple neurons to the layer
     * @param n
     */
    public void addNeurons(Neuron... n) {
        this.layer.addAll(Arrays.asList(n));
    }

    /**
     * adds a List of neurons to the layer
     * @param n
     */
    public void addNeurons(List<Neuron> li) {
        this.layer.addAll(li);
    }

    /**
     * returns true if there are no Neurons in the layer
     * @return
     */
    public boolean isEmpty() {
        return (this.getSize() == 0);
    }

    /**
     * returns the List that contains the neurons
     * @return
     */
    public List<Neuron> getNeurons() {
        return this.layer;
    }

    /**
     * Returns a copy of the layer
     * @return
     */
    @Override
    public Layer clone() {
        return new Layer(new ArrayList<Neuron>(this.layer));
    }

}
