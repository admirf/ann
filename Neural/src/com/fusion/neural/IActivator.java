package com.fusion.neural;

/**
 * Created by admir on 11/28/2015.
 * Interface for various activation classes used for neuron activation
 */
public interface IActivator {

    /**
     * Virtual method that returns a double activation value when given a double received signal value
     * @param received
     * @return
     */
    double activate(double received);

}
