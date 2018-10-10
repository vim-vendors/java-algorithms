package com.company;

//import java.util.Arrays;
import java.util.stream.IntStream; //experimenting with functional for loop for formatting
import java.lang.System;
import java.util.Random;
import java.lang.InterruptedException;


public class Main {

    public double exp;
    public int n;
    public int[] _array;
    boolean is_random = false;
   // public
    Random rand = new Random();
    Random rando_pivot = new Random();


    public Main(int exponent, boolean random){
        //generate random number between 2 and 100
        //this.n = rand.nextInt(50) + 3;
        this.exp = Math.pow(2, exponent);
        n = (int) Math.round(exp);


        //declare array of size n
        this._array= new int[n];

        if (random)
            this.setArrayRandom();
        else
            this.setArray();
    }


    public int get_n(){
        int temp = this.n;
        return temp;
    }

    public void setArrayRandom(){
        //fill random array
        int end = this.n;
        for (int index = 0; index < n; index++)
            _array[index] = rand.nextInt(100) + 1;
    }

    public void setArray(){
        //fill random array
        int end = this.n;
        for (int index = 0; index < n; index++)
            _array[index] = index;
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
    public void quicksortRando(){
        this.quicksortRando(0, n-1);
    }

    public void quicksortRando(int low, int high) {
        int pivotpoint = 0;

        if (high > low){
            if (this.n > 16){
                pivotpoint = rando_pivot.nextInt(high) + low; // passed by reference and set within partition
            }
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



        //Test insertion sort random
        long[] insertion_time_array = new long[16];
        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, true);

            while (counter < 10){
                //copy array into new object, I know this is a poor approach :(
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.insertionSort();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            insertion_time_array[index-1] = sum / 10;
            System.out.println("The avg time for random insertion sort to run with 2^" + index + " power inputs is " + insertion_time_array[index-1] + " nanoseconds.");
        }

        System.gc();

        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");

        //Test insertion sort sorted

        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, false);

            while (counter < 10){
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.insertionSort();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            insertion_time_array[index-1] = sum / 10;
            System.out.println("The avg time for sorted insertion sort to run with 2^" + index + " power inputs is " + insertion_time_array[index-1] + " nanoseconds.");
        }


        System.gc();

        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");



        //Test merge sort random
        long[] merge_time_array = new long[16];
        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, true);

            while (counter < 10){
                //copy array into new object, I know this is a poor approach :(
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.mergeSort();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            merge_time_array[index-1] = sum / 10;
            System.out.println("The avg time for random merge sort to run with 2^" + index + " power inputs is " + merge_time_array[index-1] + " nanoseconds.");
        }

        System.gc();

        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");

        //Test merge sort sorted

        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, false);

            while (counter < 10){
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.mergeSort();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            merge_time_array[index-1] = sum / 10;
            System.out.println("The avg time for sorted merge sort to run with 2^" + index + " power inputs is " + merge_time_array[index-1] + " nanoseconds.");
        }

        System.gc();

        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");

        //Test quick sort random
        long[] quick_time_array = new long[16];
        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, true);

            while (counter < 10){
                //copy array into new object, I know this is a poor approach :(
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.quickSortOne();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            quick_time_array[index-1] = sum / 10;
            System.out.println("The avg time for random quick sort to run with 2^" + index + " power inputs is " + quick_time_array[index-1] + " nanoseconds.");
        }

        System.gc();

        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");

        //Quick sort one sorted crashes due to Stack Overflow errors once it gets to 2^14

        //Test quick sort sorted

        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, false);

            while (counter < 10){
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.quickSortOne();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            quick_time_array[index-1] = sum / 10;
            System.out.println("The avg time for sorted quick sort to run with 2^" + index + " power inputs is " + quick_time_array[index-1] + " nanoseconds.");
        }



        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");


        //Test quick sort 2 random
        long[] quick_time_array = new long[16];
        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, true);

            while (counter < 10){
                //copy array into new object, I know this is a poor approach :(
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.quickSortTwo();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            quick_time_array[index-1] = sum / 10;
            System.out.println("The avg time for random hybrid quick sort to run with 2^" + index + " power inputs is " + quick_time_array[index-1] + " nanoseconds.");
        }

        System.gc();

        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");

        //Quick sort one sorted crashes due to Stack Overflow errors once it gets to 2^14

        //Test quick sort 2 sorted

        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, false);

            while (counter < 10){
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.quickSortTwo();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            quick_time_array[index-1] = sum / 10;
            System.out.println("The avg time for sorted hyrbid quick sort to run with 2^" + index + " power inputs is " + quick_time_array[index-1] + " nanoseconds.");
        }


        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");


        //Test random quick sort 3 random
        long[] quick_time_array = new long[16];
        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, true);

            while (counter < 10){
                //copy array into new object, I know this is a poor approach :(
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.quicksortRando();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            quick_time_array[index-1] = sum / 10;
            System.out.println("The avg time for random randomized quick sort to run with 2^" + index + " power inputs is " + quick_time_array[index-1] + " nanoseconds.");
        }

        System.gc();

        //Initialize JVM
        for (int index=0;index<100; index++)
            System.out.print(' ');

        System.out.println("\n");

        //Quick sort one sorted crashes due to Stack Overflow errors once it gets to 2^14

        //Test quick sort 3 sorted

        for (int index =1; index < 17; index++){
            long[] temp_time_array = new long[10];
            long counter = 0;
            int count = 0;
            long sum = 0;
            Main og_program = new Main(index, false);

            while (counter < 10){
                Main test_program = new Main(index, false);
                for (int i = 0; i < og_program.get_n(); i++)
                    test_program._array[i] = og_program._array[i];
                long startTime = System.nanoTime();
                test_program.quicksortRando();
                long endTime   = System.nanoTime();
                temp_time_array[count++] = endTime - startTime;
                counter++;
            }
            for (int inner_i = 0; inner_i < 10; inner_i++){
                sum += temp_time_array[inner_i];
            }
            quick_time_array[index-1] = sum / 10;
            System.out.println("The avg time for sorted randomized quick sort to run with 2^" + index + " power inputs is " + quick_time_array[index-1] + " nanoseconds.");
        }



    }
}

