package org.example;


import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        new Tri(Tri.twoValues, Tri.insertionSort).graphData(50, 2000);
        new Tri(Tri.sortedUp, Tri.insertionSort).graphData(50,2000);
        new Tri(Tri.randomSorted, Tri.insertionSort).graphData(50, 2000);
        new Tri(Tri.sortedDown, Tri.insertionSort).graphData(50, 2000);
        new Tri(Tri.sameValue, Tri.insertionSort).graphData(50, 2000);


    }




}