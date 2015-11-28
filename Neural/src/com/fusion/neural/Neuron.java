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
    IActivator activator;
    private List<Synapse> outgoing;

    private void initVars() {
        this.received = 0;
        this.outgoing = new ArrayList<>();
    }

    /**
     * Constructor takes an Activator as parameter
     * @param activator a class that implements an activator
     */
    public Neuron(IActivator activator) {
        this.activator = activator;
        initVars();
    }

    /**
     * Constructor for simple Threshold activation
     * @param threshold
     */
    public Neuron(double threshold) {
        this.activator = new ThresholdActivator(threshold);
        initVars();
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
     * returns the Activator implementation used by the neuron
     * @return
     */
    public IActivator getActivator() {
        return activator;
    }

    /**
     * sets the current Activator implementation
     * @param activator
     */
    public void setActivator(IActivator activator) {
        this.activator = activator;
    }

    /**
     * Sends a signal if the current received value is higher than the threshold
     */
    public void send() {
       // if(this.received > this.threshold) {
        // if(this.received < this.threshold) this.received = 0;
        for (Synapse synapse : outgoing) {
            double w = activator.activate(this.received) * synapse.getWeight();
            synapse.getDest().receive(w);
        }
       // }
    }

    /**
     * Return the List object containing the synapses
     * @return
     */
    public List<Synapse> getSynapses() {
        return outgoing;
    }

    /**
     * Constructor which sets the activator to the default activator(sigmoid)
     */
    public Neuron() {
        this.activator = new SigmoidActivator();
        initVars();
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
