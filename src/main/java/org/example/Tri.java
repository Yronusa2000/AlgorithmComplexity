package org.example;
import java.math.BigInteger;
import java.util.function.Function;
import java.util.Random;


public class Tri implements Algorithm<Integer[],Integer[]> {


    @Override
    public Integer[] getEntry(Integer n) {
        Integer[] res = new Integer[n];

        Random random = new Random();

        for(int i = 0; i < n; i++){
            res[i] = random.nextInt();
        }
        return res;
    }

    @Override
    public Function<Integer[], Integer[]> getAlgorithm() {
        return null;
    }

    static Tri insertion = new Tri() {
        @Override
        public Function<Integer[], Integer[]> getAlgorithm() {
            return Tri.insertionAlgo;
        }
    };

    static Tri bulles = new Tri(){
        @Override
        public Function<Integer[], Integer[]> getAlgorithm(){
            return new Function<Integer[], Integer[]>() {
                @Override
                public Integer[] apply(Integer[] array) {
                    int n = array.length;
                    for (int i = 0; i < n - 1; i++) {
                        for (int j = 0; j < n - i - 1; j++) {
                            if (array[j] > array[j + 1]) {
                                // Swap arr[j] and arr[j+1]
                                int temp = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = temp;
                            }
                        }
                    }
                    return array;
                }
            };
        }
    };

    public static Function<Integer[], Integer[]> insertionAlgo = new Function<Integer[], Integer[]>() {
        @Override
        public Integer[] apply(Integer[] array) {
            int n = array.length;

            for (int i = 1; i < n; ++i) {
                int key = array[i];
                int j = i - 1;

                // Move elements of array[0..i-1] that are greater than key to one position ahead of their current position
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                }
                array[j + 1] = key;
            }

            return array;
        }
    };


}
