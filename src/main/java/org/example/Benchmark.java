package org.example;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public interface Benchmark<S,T> {

    Generator<S> getGenerator();
    Function<S,T> getAlgorithm();

    default Benchmark<S,T> create(Generator<S> generator, Function<S, T> algo){
        return new Benchmark<S, T>() {
            @Override
            public Generator<S> getGenerator() {
                return generator;
            }

            @Override
            public Function<S, T> getAlgorithm() {
                return algo;
            }
        };
    }

    /**
     *
     * @param value Un entier n
     * @return Le temps d'exécution pour l'entier n.
     */

    default long mesure(Integer value){

        S entry = this.getGenerator().apply(value);

        long debut = System.nanoTime();
        this.getAlgorithm().apply(entry);
        long fin = System.nanoTime();
        long time = fin-debut;

      /**  if(time < 1000000000){
            long debut2 = System.nanoTime();
            for(int i = 0; i < 20; i++){
                entry = this.getGenerator().apply(value);
                System.out.print(entry.toString());
                // Used to "reset" the entry in case of random generation:
                // Examples: for the Tri implementation, if the entry isn't reset, the array is already sorted,
                // which would change the time.
                this.getAlgorithm().apply(entry);
            }

            long fin2 = System.nanoTime();
            time = (fin2 - debut2)/20;

        }**/
        return time;
    }

    /**
     *
     * @param value Un entier n
     * @return Retourne et affiche le résultat de l'évaluation d'une entrée de taille n d'un algorithme, affiche aussi le
     * temps d'exécution.
     */
    default T evaluate(Integer value){
        S entry = this.getGenerator().apply(value);
        T res = this.getAlgorithm().apply(entry);
        System.out.print("Résultat : " + res.toString() + "\n");
        if(this instanceof Tri){
            Integer[] array = (Integer[]) res;
            for (Integer integer : array) {
                System.out.print(integer);
            }
        }
        long time = this.mesure(value);
        System.out.print("Temps d'exécution : " + time + "ns\n");
        System.out.print("Temps d'exécution : " + time/1000000000 + "s\n");
        return res;
    }


    /**
     *
     * @param a un entier, borne inférieure
     * @param b un entier, borne supérieure
     *
     * @effet Affiche un graphe du temps d'exécution pour les entiers entre a et b.
     */
    default void graphData(int a, int b, int pas){
        HashMap<Integer, Long> data = this.getTimeFromRange(a,b, pas);
        int size = data.size();
        double[] xData = new double[size];
        double[] yData = new double[size];
        final int[] i = {0};
        data.forEach((integer, aLong) -> {
            xData[i[0]] = integer;
            yData[i[0]] = aLong;
            i[0]++;
        });


        // Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "n =", "Temps en ns", "y(x)", xData, yData);

        // Show it
        new SwingWrapper(chart).displayChart();
    }

    default HashMap<Integer,Long> getTimeFromRange(int a, int b, int pas){

        HashMap<Integer, Long> timesForN = new HashMap<>();
        for(int i = a; i < b; i = i+ pas){
            timesForN.put(i, this.mesure(i));
        }
        return timesForN;
    }

    default void graphData(int a, int b){
        HashMap<Integer, Long> data = this.getTimeFromRange(a,b);
        int size = data.size();
        double[] xData = new double[size];
        double[] yData = new double[size];
        final int[] i = {0};
        data.forEach((integer, aLong) -> {
            xData[i[0]] = integer;
            yData[i[0]] = aLong;
            i[0]++;
        });


        // Create Chart
        XYChart chart = QuickChart.getChart(this.getGenerator().getName(), "n =", "Temps en ns", "y(x)", xData, yData);

        // Show it
        new SwingWrapper(chart).displayChart();
    }

    default HashMap<Integer,Long> getTimeFromRange(int a, int b){

        HashMap<Integer, Long> timesForN = new HashMap<>();
        for(int i = a; i < b; i++){
            timesForN.put(i, this.mesure(i));
        }
        return timesForN;
    }




}
