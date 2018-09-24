package com.company;

import java.util.stream.IntStream; //experimenting with functional for loop for formatting
import java.lang.System;
public class Main {
    public static int n = 9;
    public int[] _array = {1,2,5,8,9,3,4,7,6};

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

    public void view_array(){
        view_array(n,_array);
    }
    public void view_array(int n, int _array[]){
        IntStream.range(0, n)
                .forEach(i -> System.out.print(_array[i] + " "));
    }

    public void mergeSort(){
        mergeSort(n, _array);
    }
    public void mergeSort(int upper_bound, int [] old_array){
        if (upper_bound > 1){
            int lower_bound = (upper_bound /2); //1/2 old array
            int [] left_temp = new int[lower_bound];
            int midpoint = upper_bound - lower_bound;
            int [] right_temp = new int[midpoint];

            for (int index =0; index < lower_bound; index++){
                left_temp[index] = old_array[index];
            }
            int counter = 0;
            for (int index = lower_bound; index < upper_bound; index++){
                 right_temp[counter++] = old_array[index];
            }
            mergeSort(lower_bound, left_temp);
            mergeSort(midpoint, right_temp);
            merge(lower_bound, midpoint, left_temp, right_temp, old_array);
        }
    }
    public void merge(int lower_bound, int upper_bound, int [] left_temp, int [] right_temp, int [] old_array){
        int i_index = 0;
        int j_index = 0;
        int k_index = 0;
        while (i_index <= lower_bound && j_index <= upper_bound){
            if (left_temp[i_index] < right_temp[j_index]){
                old_array[k_index++] = left_temp[i_index++];
                //i_index++;
            }
            else{
                old_array[k_index++] = right_temp[j_index++];
                //j_index++;
            }
            //k_index++;
        }
        if (i_index > lower_bound){
            //copy right_temp[j_index] through right_temp[upper_bound] to
            //old_array[k_index] through old_array[lower_bound + upper_bound];
            for (int index = j_index; index < upper_bound; index++){
                old_array[k_index++] = right_temp[index];
            }
        }
        else        {
            //copy left_temp[i_index] through left_temp[lower_bound] to old_array[k_index] through
            //old_array[lower_bound + upper_bound];
            for (int index = i_index; index < lower_bound; index++){
                old_array[k_index++] = left_temp[index];
            }
        }
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

