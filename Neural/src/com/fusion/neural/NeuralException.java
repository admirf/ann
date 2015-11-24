package com.fusion.neural;

/**
 * Created by admir on 11/24/2015.
 * Currently just a dummy exception
 */
public class NeuralException extends Exception {
    private String msg;
    public NeuralException(String msg) {this.msg = msg;}
    public String getError() {return msg;}
}
