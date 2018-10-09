package com.company;

//import java.util.Arrays;
import java.util.stream.IntStream; //experimenting with functional for loop for formatting
import java.lang.System;
import java.util.Random;


public class Main {

    public int n;
    public int[] _array;
    Random rand = new Random();

    public Main(){
        //generate random number between 2 and 100
        this.n = rand.nextInt(10) + 3;

        //declare array of size n
        this._array= new int[n];
        this.setArray();
    }


    public int get_n(){
        int temp = this.n;
        return temp;
    }

    public void setArray(){
        //fill random array
        int end = n;
        for (int index = 0; index < n; index++)
            _array[index] = rand.nextInt(10) + 0;
    }

//    Insertion Sort
    public void insertionSort(){
        insertionSort(this.n, this._array);
    }
    public void insertionSort(int n, int[]_array){
        int i, j;
        int x;
        for (i=0; i < n; i++){
            x = _array[i];
            j = i-1;
            while (j >= 0 && _array[j] > x){
                _array[j+1] = _array[j];
                j--;
            }
            _array[j+1] = x;

        }
    }

//    Merge Sort
    public void mergeSort(){
        mergeSort(this.n, this._array);
    }

    public void merge(int u_len, int v_len, int V[], int U[], int og_array[]){

        int i = 0;
        int j = 0;
        int k = 0;
        while(i < u_len && j < v_len) {
            if (U[i] < V[j]) {
                og_array[k] = U[i];
                i++; // i tracks how many values from U have been placed in S
            } else {
                og_array[k] = V[j];
                j++;
            }
            k++; // k tracks how many values have been placed in S
        }
        if (i >= u_len) {
            // copy V[j] through V[vLen] to S[k] through S[uLen+vLen];
            int v_index = j;
            int stop = u_len+v_len;
            for (int index = k; index < stop; index++){
                og_array[index] = V[v_index++];
            }

        }
        else {
            //copy U[i] through U[uLen] to S[k] through S[uLen+vLen]
            int u_index = i;
            int stop = u_len+v_len;

            for (int index = k; index < stop; index++){
                og_array[index] = U[u_index++];
            }
        }
    }

    public void mergeSort(int n, int[] array){
        if (n >1){
            int u_len = n/2;
            int v_len = n - u_len;
            int[] U = new int[u_len];
            int[] V = new int[v_len];
            //copy S[1] through S[uLen] to U[1] through U[uLen]
            for (int index = 0; index < u_len; index++){
                U[index] = array[index];
            }
            int v_index = 0;
            //copy S[uLen+1] through S[n] to V[1] through V[vLen]
            for (int index = u_len; index < n; index++){
                V[v_index] = array[index];
                v_index++;
            }
            mergeSort(u_len, U);
            mergeSort(v_len, V);
            merge(u_len, v_len, V, U, array);
        }
    }

    //Output
    public void view_array(){
        view_array(this.n, this._array);
    }
    public void view_array(int n, int _array[]){
        IntStream.range(0, n)
                .forEach(i -> System.out.print(_array[i] + " "));
    }


    //Quick Sort 1
    public void quickSortOne(){
        this.quicksort(0, n-1);
    }
    //Quick Sort Two (Hybrid quick sort)
    public void quickSortTwo(){
        if (this.n >= 16) {
            //System.out.println("Calling quick sort");
            this.quickSortOne();
        }
        else {
            //System.out.println("Calling insertion sort");
            this.insertionSort();
        }
    }
    public void quicksort(int low, int high) {
        int pivotpoint = 0; // passed by reference and set within partition
        if (high > low){
            int index = partition(low, high, pivotpoint);
            quicksort(low, index - 1);
            quicksort(index + 1, high);
        }
    }

    public int partition(int low, int high, int pivotpoint){
        int i, j;
        //int pivotitem;
        int pivotitem = _array[low]; // choose first item for pivot
        j = low;
        for (i = low + 1; i <= high; i++) {
            if (_array[i] < pivotitem) {
                j++;
                //exchange S[i] and S[j];
                int inner_temp = _array[j];
                _array[j] = _array[i];
                _array[i] = inner_temp;
            }
        }
       pivotpoint = j;
       //exchange S[low] and S[pivotpoint]; // put pivot item at pivot point
        int outer_temp = _array[low];
        _array[low] = _array[pivotpoint];
        _array[pivotpoint] = outer_temp;
        return pivotpoint;
    }

    public static void main(String[] args) {
        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");

/*
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
        System.out.println("The time for insertion sort to run with " + my_program.get_n() + " inputs is " + totalTime + " nanoseconds.");*/

        Main my_program = new Main();
        System.out.print("Pre merge sort the array is currently : " );
        my_program.view_array();
        System.out.println("\n");
        //long startTime = System.nanoTime();
        my_program.mergeSort();
        //long endTime   = System.nanoTime();
        System.out.print("Post merge sort the array is now : " );
        my_program.view_array();
        System.out.println("\n");

/*        long totalTime = endTime - startTime;
        System.out.println("The time for merge sort to run with " + n + " inputs is " + totalTime + " nanoseconds.");
        Main my_program = new Main();
        System.out.print("Pre quick sort #1 the array is currently : " );
        my_program.view_array();
        */

        /*System.out.println("\n");
        long startTime = System.nanoTime();
        my_program.quickSortOne();
        long endTime   = System.nanoTime();
        System.out.print("Post quick sort #1 the array is now : " );
        my_program.view_array();
        System.out.println("\n");*/

    }
}

