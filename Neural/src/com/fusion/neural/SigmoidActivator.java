package com.fusion.neural;

/**
 * Created by admir on 11/28/2015.
 * Activator implementation that uses the sigmoid function for neuron activation
 */
public class SigmoidActivator implements IActivator {

    @Override
    public double activate(double received) {
        return 1.0/(1.0 + java.lang.Math.pow(Math.E, received));
    }
}
