package com.fusion.neural;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admir on 11/24/2015.
 *
 * Class that represents a neuron (basically a graph node)
 * The neuron has the following attribures:
 * A threshold which is used to determine if the neuron will activate upon receiving a signal
 * A List object of which contains the outgoing synapses of the neuron
 * A received attribute which is used for calculating the current received signal
 *
 */
public class Neuron {

    private double received;
    private List<Synapse> outgoing;
    private double threshold;

    /**
     * Constructor takes a threshold as parameter
     * @param threshold
     */
    public Neuron(double threshold) {
        this.received = 0;
        this.threshold = threshold;
        this.outgoing = new ArrayList<>();
    }

    /**
     * appends a signal to the current received value
     * @param r
     */
    public void receive(double r) {
        this.received += r;
    }

    /**
     * sets the current receivede value to the given argument
     * @param received
     */
    public void setReceived(double received) {
        this.received = received;
    }

    /**
     * return the current received value
     * @return
     */
    public double getReceived() {
        return this.received;
    }

    /**
     * Sends a signal if the current received value is higher than the threshold
     */
    public void send() {
        if(this.received > this.threshold) {
            for (Synapse synapse : outgoing) {
                double w = this.received * synapse.getWeight();
                synapse.getDest().receive(w);
            }
        }
    }

    /**
     * Return the List object containing the synapses
     * @return
     */
    public List<Synapse> getSynapses() {
        return outgoing;
    }

    /**
     * Constructor which sets the threshold automatically to 0, useful for input neurons
     */
    public Neuron() {
        this(0);
    }

    /**
     * Returns the threshold of the neuron
     * @return
     */
    public double getThreshold() {
        return threshold;
    }

    /**
     * Sets the threshold of the neuron
     * @param threshold
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Adds a synapse to the neuron that connects to another with a weight of w
     * @param dest
     * @param w
     */
    public void createSynapse(Neuron dest, double w) {
        this.outgoing.add(new Synapse(this, dest, w));
    }

}
