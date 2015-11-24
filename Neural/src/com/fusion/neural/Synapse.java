package com.fusion.neural;

/**
 * Created by admir on 11/24/2015.
 *
 * Class that represents a synapse (basically a graph edge)
 * it has a source node(Neuron), a destination node(Neuron) and a weight
 *
 */
public class Synapse {

    private double weight;
    private Neuron source;
    private Neuron dest;

    /**
     * Constructor takes source neuron, destination neuron and weight as arguments
     * @param source
     * @param dest
     * @param weight
     */
    public Synapse(Neuron source, Neuron dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Returns the weight of the synapse
     * @return
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Returns the source Neuron object
     * @return
     */
    public Neuron getSource() {
        return this.source;
    }

    /**
     * Returns the destination Neuron object
     * @return
     */
    public Neuron getDest() {
        return this.dest;
    }
}
