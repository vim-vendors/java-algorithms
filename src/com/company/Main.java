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

    public void merge(int [] temp_array, int low, int mid, int high){

        int k = 0; //workspace index
        int i = low; //low pointer
        //int j = mid +1;
        int j = high -1; //mid pointer
        int num = high - low + 1;

        //int [] new_array = new int[high];
        while (low <= j && mid <= high){
            if (_array[i] < _array[mid]){
                temp_array[k++] = _array[low++];
                //i++;
            }
            else{
                temp_array[k++] = _array[mid++];
                //j++;
            }
           // k++;
        }

        while (i <= j) {
            temp_array[k++] = _array[low++];
        }

        while (mid <= high){
            temp_array[k++] = _array[mid++];
        }

        for (int index =0; index < num; index++){
            _array[i + k] = temp_array[k];
        }

    }
    public void mergeSort(){
        int [] temp_array = new int[n];
        mergeSort(temp_array, 0, n-1);
    }
    public void mergeSort(int [] temp_array, int low, int high){
        if (low == high)
            return;
        else{
            int mid = (low + high) / 2;
            mergeSort(temp_array, low, mid);
            mergeSort(temp_array, (mid + 1) ,  high);
            merge(temp_array, low, (mid+1), high);
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

