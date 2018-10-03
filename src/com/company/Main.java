package com.company;

import java.util.stream.IntStream; //experimenting with functional for loop for formatting
import java.lang.System;
public class Main {
    public static int n = 4;
    public int[] _array = {1,2,5, 0};

    public void insertionSort(){
        insertionSort(n, _array);
    }
    public void insertionSort(int n, int[]_array){
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
    public void merge(int low, int mid, int high){
        int i = low;
        int j = mid +1;
        int k = low;

        int array_size = (high - low) + 1;
        int [] new_array = new int[array_size];
        while (i <= mid && j <= high){
            if (_array[i] < _array[j]){
                new_array[k] = _array[i];
                i++;
            }
            else{
                new_array[k] = _array[j];
                j++;
            }
            k++;
        }
        if (i > mid){
            //move _array[j] through _array[high] to new_array[k] through new_array[high]

            int k_index = k;

            for (int j_index = j; j_index <= high; j_index++){
                new_array[k] = _array[j_index];
                k++;
            }

        }
        else{
           // move _array[i] through _array[mid] to new_array[k] through new_array[high]
            int k_index = k;
            for (int i_index = i; i_index < mid; i_index++){
                new_array[k] = _array[i_index];
                k++;
            }
        }
       // move new_array[low] through new_array[high] to _array[low] through _array[high]
        for (int index = low; index < high; index++){
            _array[index] = new_array[index];
        }
    }
    public void mergeSort(){
        mergeSort(0, n-1);
    }
    public void mergeSort(int low, int high){
        int mid;
        if (low < high){
            mid =  (low + high) / 2;
            mergeSort(low, mid);
            mergeSort((mid + 1) ,  high);
            merge(low, mid, high);
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

//        Main my_program = new Main();
//        System.out.print("Pre insertion sort the array is currently : " );
//        my_program.view_array();
//        System.out.println("\n");
//        long startTime = System.nanoTime();
//        my_program.insertionSort();
//        long endTime   = System.nanoTime();
//        System.out.print("Post insertion sort the array is now : " );
//        my_program.view_array();
//        System.out.println("\n");
//        long totalTime = endTime - startTime;
//        System.out.println("The time for insertion sort to run with " + n + " inputs is " + totalTime + " nanoseconds.");

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
        //long totalTime = endTime - startTime;
        //System.out.println("The time for merge sort to run with " + n + " inputs is " + totalTime + " nanoseconds.");



    }
}

