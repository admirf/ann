package com.fusion.neural;

/**
 * Created by admir on 11/24/2015.
 * Pokretac testova
 */
public class Driver {

    public static void main(String[] args) {
        try {
            Network xor = NeuralUtil.createXOR();
            //xor.build();
            // testiramo 1 xor 1 ocekivano 0
            String result = NeuralUtil.binaryResultToString(xor.evaulate(new double[]{1, 1}));
            System.out.println(result);
            // 1 xor 0 ocekivano 1
            result = NeuralUtil.binaryResultToString(xor.evaulate(new double[]{1, 0}));
            System.out.println(result);
            // 0 xor 1 ocekivano 1
            result = NeuralUtil.binaryResultToString(xor.evaulate(new double[]{0, 1}));
            System.out.println(result);
            // 0 xor 0 ocekivano 0
            result = NeuralUtil.binaryResultToString(xor.evaulate(new double[]{0, 0}));
            System.out.println(result);
        }
        catch(NeuralException e) {
            System.out.println(e.getError());
        }

    }

}
