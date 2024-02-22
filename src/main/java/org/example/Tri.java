package org.example;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

import static java.util.Collections.min;


public class Tri implements Benchmark<Integer[],Integer[]> {

    Generator<Integer[]> generator;
    Function<Integer[],Integer[]> sorter;

    public Tri(Generator<Integer[]> generator, Function<Integer[], Integer[]> sorter) {
        this.generator = generator;
        this.sorter = sorter;
    }

    public void test(int n){
        for(Integer i : this.sorter.apply(this.generator.apply(n))){
            System.out.print(i + "\n");
        }

        System.out.print(isSorted(this.sorter.apply(this.generator.apply(n))));
    }


    static Generator<Integer[]> randomSorted = new Generator<Integer[]>() {
        @Override
        public Integer[] apply(Integer n) {
            Integer[] res = new Integer[n];

            Random random = new Random();

            for(int i = 0; i < n; i++){
                res[i] = random.nextInt();
            }
            return res;
        }

        @Override
        public String getName() {
            return "Random Sort";
        }
    };

    static Generator<Integer[]> sortedUp = new Generator<Integer[]>() {

        @Override
        public String getName() {
            return "Sorted";
        }
        @Override
        public Integer[] apply(Integer n) {
            Integer[] res = new Integer[n];

            for(int i = 0; i < n; i++){
                res[i] = i*10;
            }
            return res;
        }
    };

    static Generator<Integer[]> sortedDown = new Generator<Integer[]>() {

        @Override
        public String getName() {
            return "Inversly Sorted";
        }
        @Override
        public Integer[] apply(Integer n) {
            Integer[] res = new Integer[n];

            for(int i = 0; i < n; i++){
                res[i] = (n-i)*10;
            }
            return res;
        }
    };

    static Generator<Integer[]> sameValue = new Generator<Integer[]>() {

        @Override
        public String getName() {
            return "Same value everytime";
        }
        @Override
        public Integer[] apply(Integer n) {
            Integer[] res = new Integer[n];

            Arrays.fill(res, n * 10);
            return res;
        }
    };

    static Generator<Integer[]> twoValues = new Generator<Integer[]>() {

        @Override
        public String getName() {
            return "Only two values, sorted";
        }
        @Override
        public Integer[] apply(Integer n) {
            Integer[] res = new Integer[n];
            if(n%2==0){
                for(int i = 0; i < n/2; i++){
                    res[i] = n*10;
                    res[i+n/2] = n*20;
                }
                return res;
            }

            else{
                for(int i = 0; i < n/2; i++){
                    res[i] = n*10;
                    res[i+n/2] = n*20;
                }
                res[n-1] = n*20;
                return res;
            }

        }
    };

    @Override
    public Generator<Integer[]> getGenerator() {
        return this.generator;
    }

    @Override
    public Function<Integer[], Integer[]> getAlgorithm() {
        return this.sorter;
    }



    public static Function<Integer[], Integer[]> insertionSort = new Function<Integer[], Integer[]>() {
        @Override
        public Integer[] apply(Integer[] array) {
            int n = array.length;
            for(int i = 0; i < n; i++){
                for(int j = i; j > 0; j--){
                    if(array[j-1] > array[j]){
                        int temp = array[j];
                        array[j] = array[j-1];
                        array[j-1] = temp;
                    }
                }
            }
            return array;
        }
    };

    public static Function<Integer[], Integer[]> bubbleSort = new Function<Integer[], Integer[]>() {
        @Override
        public Integer[] apply(Integer[] array) {
            int n = array.length;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n-i-1; j++) {
                    if(array[j+1] < array[j]){
                        int temp = array[j+1];
                        array[j+1] = array[j];
                        array[j] = temp;
                    }
                }
            }
            return array;
        }
    };

    public static Function<Integer[], Integer[]> fusionSort = new Function<Integer[], Integer[]>() {
        @Override
        public Integer[] apply(Integer[] integers) {

            int size = integers.length;
            if(size <= 1){
                return integers;
            }

            int middle = size/2;
            Integer[] right = new Integer[middle];
            Integer[] left = new Integer[size - middle];

            if(middle % 2 == 0){
                for(int i = 0; i < middle; i++){
                    right[i] = integers[i];
                    left[i] = integers[i+middle];
                }
            }

            else{
                    for(int i = 0; i < middle; i++){
                        right[i] = integers[i];
                        left[i] = integers[i+middle];
                    }

                    left[size-middle-1] = integers[integers.length-1];
                    System.out.println("\n \n \n");
                    System.out.print(Arrays.toString(left) + "\n");
                }


            right = fusionSort.apply(right);
            left = fusionSort.apply(left);

            return fusion(right,left);

        }

        public static Integer[] fusion(Integer[] a1, Integer[] a2){
            int max = Math.max(a1.length, a2.length);
            Integer[] res = new Integer[max];
            for(int i = 0; i < max; i++){



                if(i >= a1.length){
                    res[i] = a2[i];
                }

                else if(i >= a2.length){
                    res[i] = a1[i];
                }

                else{
                    res[i] = Math.max(a1[i], a2[i]);
                }

            }

            return res;
        }

    };



    public static Function<Integer[], Integer[]> bogoSort = new Function<Integer[], Integer[]>() {
        @Override
        public Integer[] apply(Integer[] integers) {
            Tri.shuffle(integers);
            while(!isSorted(integers)){
                Tri.shuffle(integers);

            }

            return integers;
        }
    };

    public static Function<Integer[], Integer[]> selectionSort = new Function<Integer[], Integer[]>() {
        @Override
        public Integer[] apply(Integer[] tab) {
                for (int i = 0; i < tab.length - 1; i++)
                {
                    int index = i;
                    for (int j = i + 1; j < tab.length; j++)
                    {
                        if (tab[j] < tab[index]){
                            index = j;
                        }
                    }

                    int min = tab[index];
                    tab[index] = tab[i];
                    tab[i] = min;
                }

                return tab;
            }
    };

    public static Function<Integer[], Integer[]> shellSort = new Function<Integer[], Integer[]>() {
        @Override
        public Integer[] apply(Integer[] tab) {




            return tab;
        }
    };


    public static int min(Integer[] min, int a){
        int res = min[0];
        for(int i = a; i < min.length; i++){
            if(min[i] < res){
                res = min[i];
            }
        }

        return res;
    }

    public static Integer[] shuffle(Integer[] a){

        List<Integer> intList = Arrays.asList(a);

        Collections.shuffle(intList);

        intList.toArray(a);
        return a;
    }

    public static boolean isSorted(Integer[] a2){
        for(int i = 0; i < a2.length-1; i++){
            if(a2[i] > a2[i+1]){
                    return false;
            }
        }

        return true;
    };

}
