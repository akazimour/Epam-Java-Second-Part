package com.epam.rd.autotasks;

import java.util.Arrays;

class CycleSwap {
    static void cycleSwap(int[] array) {
        if (array.length > 0){
            int container = array[array.length-1];
            for (int i = array.length-2; i > -1; i--) {
                array[i+1]=array[i];
            }
            array[0] = container;
        }
    }

    static void cycleSwap(int[] array, int shift) {
        for (int i = 0; i < shift; i++) {
            cycleSwap(array);
        }
        System.out.println(Arrays.toString(array));
    }
}
