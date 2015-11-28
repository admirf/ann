package com.fusion.neural;

/**
 * Created by admir on 11/28/2015.
 * Activator implementation based on threshold neuron activation
 */
public class ThresholdActivator implements IActivator {

    private double threshold;

    public ThresholdActivator(double threshold) {
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public double activate(double received) {
        return (received > this.threshold)? received: 0;
    }
}
