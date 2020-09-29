package com.atolonrot;

import com.atolonrot.model.Dispositivo;

import java.util.concurrent.RecursiveAction;

/**
 * @author Breyner
 * Clase que extiende del framework Fork/Join para obtener subtareas
 */
public class SumatoriaBateriaForkJoin extends RecursiveAction {

    private final Dispositivo[] input;
    private final int startIndex, endIndex;
    private double value;

    public double getValue() {
        return value;
    }

    public SumatoriaBateriaForkJoin(int startIndex, int endIndex, Dispositivo[] input) {
        this.input = input;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected void compute() {
        final int threshold = 500000;
        if((endIndex - startIndex) <= threshold) {
            for(int i = startIndex; i < endIndex; i++) {
                value += input[i].obtenerBateria();
            }
        } else {
            int middleIndex = (endIndex + startIndex) / 2;
            SumatoriaBateriaForkJoin left = new SumatoriaBateriaForkJoin(startIndex, middleIndex, input);
            SumatoriaBateriaForkJoin right = new SumatoriaBateriaForkJoin(middleIndex, endIndex, input);
            left.fork();
            right.compute();
            left.join();
            value = left.value + right.value;
        }
    }
}
