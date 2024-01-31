package org.example;

import java.math.BigInteger;
import java.util.function.Function;

public class Fibonnaci implements Algorithm<Integer,BigInteger> {


    Function<Integer, BigInteger> algorithm;


    @Override
    public Integer getEntry(Integer n) {
        return n;
    }

    @Override
    public Function<Integer, BigInteger> getAlgorithm() {
        return Fibonnaci.recursive;
    }

    static Fibonnaci fiboRecursif = new Fibonnaci() {
        @Override
        public Function<Integer, BigInteger> getAlgorithm() {
            return Fibonnaci.recursive;
        }
    };

    static Fibonnaci fiboArray = new Fibonnaci() {
        @Override
        public Function<Integer, BigInteger> getAlgorithm() {
            return Fibonnaci.fibArray;
        }
    };

    static Fibonnaci fiboVector = new Fibonnaci() {
        @Override
        public Function<Integer, BigInteger> getAlgorithm() {
            return Fibonnaci.fibVector;
        }
    };




    public static Function<Integer, BigInteger> recursive = new Function<Integer, BigInteger>() {
        @Override
        public BigInteger apply(Integer integer) {
            if (integer == 0 || integer == 1) {
                return new BigInteger(String.valueOf(integer));
            } else {
                return apply(integer - 2).add(apply(integer - 1));
            }
        }
    };

    public static Function<Integer, BigInteger> fibArray = new Function<Integer, BigInteger>() {
        @Override
        public BigInteger apply(Integer integer) {

            BigInteger[] array = new BigInteger[integer+1];
            array[0] = new BigInteger(String.valueOf(0));
            array[1] = new BigInteger(String.valueOf(1));
            int max = 1;


            for(int i = max+1; i <= integer; i++){
                array[i] = fib(array[i-1], array[i-2]);
                max++;
            }

            return array[integer];
        }

        public BigInteger fib(BigInteger i1, BigInteger i2){
            return i1.add(i2);
        };
    };

    public static Function<Integer, BigInteger> fibVector = new Function<Integer, BigInteger>() {

        @Override
        public BigInteger apply(Integer integer) {

            BigInteger[] ligne0 = new BigInteger[]{
                    new BigInteger(String.valueOf(0)), new BigInteger(String.valueOf(1))
            };

            BigInteger[] ligne1 = new BigInteger[]{
                    new BigInteger(String.valueOf(1)), new BigInteger(String.valueOf(1))
            };

            BigInteger[][] matrixF = new BigInteger[][]{ligne0, ligne1};

            BigInteger[] vector = new BigInteger[]{new BigInteger("0"), new BigInteger("1")};



            return null;
        }


    };


}
