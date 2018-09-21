package com.company;

import java.util.stream.IntStream; //experimenting with functional for loop for formatting
import java.lang.System;
public class Main {
    public static int n = 9;
    public int[] _array = {1,2,5,8,9,3,4,7,6};
    public void insertionSort(){
        int i, j;
        int x;
        for (i=0; i < n; i++){
            x = _array[i];
            j = i-1;
            while (j > 0 && _array[j] > x){
                _array[j+1] = _array[j];
                j--;
            }
            _array[j+1] = x;

        }
    }


    public void view_array(){
        view_array(n,_array);
    }
    public void view_array(int n, int _array[]){
        IntStream.range(0, n)
                .forEach(i -> System.out.print(_array[i] + " "));
    }
    public static void main(String[] args) {

        Main my_program = new Main();
        System.out.print("Pre insertion sort the array is currently : " );
        my_program.view_array();
        System.out.println("\n");
        long startTime = System.nanoTime();
        my_program.insertionSort();
        long endTime   = System.nanoTime();
        System.out.print("Post insertion sort the array is now : " );
        my_program.view_array();
        System.out.println("\n");
        long totalTime = endTime - startTime;
        System.out.println("The time for insertion sort to run with " + n + " inputs is " + totalTime + " nanoseconds.");


    }
}

