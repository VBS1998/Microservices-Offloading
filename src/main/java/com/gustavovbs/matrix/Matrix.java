package com.gustavovbs.matrix;

import java.util.Random;

public class Matrix {

    public Double[][] def(int seed, int size){
        Random r = new Random((long) seed);

        Double a[][] = new Double[size][size];
        Double b[][] = new Double[size][size];
        Double c[][] = new Double[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                a[i][j] = r.nextDouble() * r.nextInt();
                b[i][j] = r.nextDouble() * r.nextInt();
                c[i][j] = 0.0;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++)
                    c[i][j] += a[i][k] * b[k][j];
            }
        }

        return c;
    }
}
