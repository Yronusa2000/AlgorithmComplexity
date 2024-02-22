package org.example;

public interface Generator<S>{

    S apply(Integer n);
    String getName();



}
