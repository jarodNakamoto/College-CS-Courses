
/**
 * Write a description of class Sorts here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

public class Sorts
{
    //useful array methods
    //array generating methods
    public static int[] genArr(int size, int multiplier)
    {
        int[] arr = new int[size];
        for(int i = 0; i < size; i++)
        {
            //fill the array with random values from 0 to multiplier
            arr[i] = (int)(multiplier*Math.random());
        }
        return arr;
    }
    //creates an array from 1 to size filled with their respective index
    public static int[] genInOrderArr(int size)
    {
        int[] arr = new int[size];
        for(int i = 0; i < size; i++)
        {
            //fill the array with its respective index
            arr[i] = i;
        }
        return arr;
    }
    //copy array
    public static int[] copyArr(int[] arr)
    {
        int[] newArr = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            newArr[i] = arr[i];
        return newArr;
    }
    //swaps values at 2 indexes in an array
    public static void swap(int[] arr, int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        //System.out.println("Swapping values at indexes " + a + " and " + b);
    }
    //prints the array
    public static void printArr(int[] arr, String name)
    {
        System.out.print(name + ":    ");
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }
    //saves array arr to file fileName
    public static void saveArrToFile(int[] arr, String fileName) throws Exception
    {
        PrintWriter pw = new PrintWriter(new File(fileName));
        StringBuilder sb = new StringBuilder();
        
        String str;
        for(int i = 0; i < arr.length; i++)
        {
            str = arr[i] + "  ";
            sb.append(str);
        }
        sb.append('\n');
        
        pw.write(sb.toString());
        pw.close();
    }
    
    //sorting methods
    //insertion sort algorithm
    public static void insertionSort(int[] arr,int start, int end)
    {
        //System.out.println("Insertion Sort");
        //for i = start to end-1
        for(int i = start; i < end;i++)
        {
            //j <- i
            int j = i;
            //while j > 0 and arr[j-1] > arr[j]
            while(j > 0 && arr[j-1] > arr[j])
            {
                //swap arr[j] and arr[j-1]
                swap(arr, j, j-1);
                //j <- j-1
                j--;
            }
        }
    }
    
    //mergesort
    public static void mergeSort(int[] arr,int low,int high)
    {
        //System.out.println("Merge Sort");
        //if (low < high)
        if(low < high)
        {
            int mid = (low + high)/2; //java rounds down
            mergeSort(arr,low,mid);
            mergeSort(arr,mid+1,high);
            merge(arr,low,mid,high);
        }
    }
    //merge
    public static void merge(int[] arr, int low, int mid, int high)
    {
        //u is a temporary array that will be sorted
        int[] u = new int[arr.length];
        int i = low, j = mid + 1, k = low;
        //while(i<=mid and j<=high)
        while(i <= mid && j <= high)
        {
            if(arr[i] <= arr[j])
            {
                u[k] = arr[i];//U[k] <- A[i]
                i++;
            }
            else
            {
                u[k] = arr[j];
                j++;
            }
            k++;
        }
        if(i > mid)
        {
            //move A[j]..A[high] to U[k]..U[high]
            for(int q = j; q <= high; q++)
            {
                u[k] = arr[q];
                k++;
            }
        }
        else
        {
            //move A[i]..A[mid] to U[k]..U[high]
            for(int q = i; q <= mid; q++)
            {
                u[k] = arr[q];
                k++;
            }
        }
        //loop overwrites original array with new sorted values
        for(int p = low; p <= high; p++)
            arr[p] = u[p];
    }
    
    //quicksort1
    public static void quickSort1(int[] arr, int p, int q)
    {
        //System.out.println("Q1 Sort");
        if(p<q)
        {
            int pivotPosition = Partition(arr,p,q);
            quickSort1(arr,p,pivotPosition-1);
            quickSort1(arr,pivotPosition+1,q);
        }
    }
    public static int Partition(int[] arr, int first, int last)
    {
        //System.out.println("Partition");
        int pivot = arr[first],tb = first+1,ts = last;
        //loop //while(tb <= ts)
        while(tb <= ts)//for(int tb = first+1;tb<ts;tb++) 
        {
            while(tb <= last && arr[tb] <= pivot){tb++;}
            while(ts > first && arr[ts] > pivot){ts--;}
            if(tb < ts){
                swap(arr,tb,ts);
            }
        }
        //System.out.println("ts: " + ts);
        arr[first] = arr[ts];
        arr[ts] = pivot;
        return ts;
    }
    //quicksort2
    public static void quickSort2(int[] arr, int p, int q)
    {
        //System.out.println("Q2 Sort");
        if(p<q)
        {
            if((q-p) <= 16)
            {
                insertionSort(arr,p,q);
            }
            else
            {
                int pivotPosition = Partition(arr,p,q);
                quickSort2(arr,p,pivotPosition-1);
                quickSort2(arr,pivotPosition+1,q);
            }
        }
    }
    //quicksort3
    public static void quickSort3(int[] arr, int p, int q)
    {
        //System.out.println("Q3 Sort");
        if(p<q)
        {
            if((q-p+1)>16)
            {
                swap(arr,p,(p+(int)(q*Math.random()%(q-p+1))));
            }
            int pivotPosition = Partition(arr,p,q);
            quickSort3(arr,p,pivotPosition-1);
            quickSort3(arr,pivotPosition+1,q);
        }
    }
    // do the sorts and time them 
    public static void main(String[] args) throws Exception
    {
         /*
        //correctness testing
        //makes an array and copies of size 20 with values 0 ... 19
        int size = 20;
        int[] inOrderArr = genInOrderArr(size);
        int[] insArr = copyArr(inOrderArr);
        int[] mergeArr = copyArr(inOrderArr);
        int[] q1Arr = copyArr(inOrderArr);
        int[] q2Arr = copyArr(inOrderArr);
        int[] q3Arr = copyArr(inOrderArr);
        
        //sorts
        insertionSort(insArr, 0, size);
        mergeSort(mergeArr, 0, size-1);
        quickSort1(q1Arr, 0, size-1);
        quickSort2(q2Arr, 0, size-1);
        quickSort3(q3Arr, 0, size-1);
         
        //print results
        printArr(inOrderArr, "Original In Order Array");
        printArr(insArr, "Insertion Array");
        printArr(mergeArr, "Merge Array");
        printArr(q1Arr, "Q1 Array");
        printArr(q2Arr, "Q2 Array");
        printArr(q3Arr, "Q3 Array");
        System.out.println();
        
        //makes an array of size 20 with random values 0 ... 99 
        int[] randomArr = genArr(size,100);
        insArr = copyArr(randomArr);
        mergeArr = copyArr(randomArr);
        q1Arr = copyArr(randomArr);
        q2Arr = copyArr(randomArr);
        q3Arr = copyArr(randomArr);
        
        //sorts
        insertionSort(insArr, 0, size);
        mergeSort(mergeArr, 0, size-1);
        quickSort1(q1Arr, 0, size-1);
        quickSort2(q2Arr, 0, size-1);
        quickSort3(q3Arr, 0, size-1);
         
        //print results
        printArr(randomArr, "Original Random Array");
        printArr(insArr, "Insertion Array");
        printArr(mergeArr, "Merge Array");
        printArr(q1Arr, "Q1 Array");
        printArr(q2Arr, "Q2 Array");
        printArr(q3Arr, "Q3 Array");
        System.out.println();
         */
        
        // /*
        //max size of the array for testing purposes
        int BiggestSize = (int)(Math.pow(2,16));
        //to make it easier to write to a csv
        StringBuilder builder  = new StringBuilder();
        builder.append("insertion,merge,q1,q2,q3");
        builder.append('\n');
        //do testing
        for(int size = 2;size <= BiggestSize; size*=2)
        {
            //generate and make copies of an array of random values 
            //from 0 to 10000000 and size size 
            //int[] arr = genArr(size,10000000);
	    int[] arr = genInOrderArr(size);            

            //saves original array for n up to 32 to a file for verification purposes 
            if(size <= 32)
            {
               saveArrToFile(arr, "OriginalArray-"+size+".txt");
            }
            //totals of respective times
            long insTime = 0, mergeTime = 0, q1Time = 0, q2Time = 0, q3Time = 0;
            for(int i = 0; i < 10; i++)
            {
                int[] insArr = copyArr(arr);
                int[] mergeArr = copyArr(arr);
                int[] q1Arr = copyArr(arr);
                int[] q2Arr = copyArr(arr);
                int[] q3Arr = copyArr(arr);
            
                //track performance of insertion sort
                long insertionStart = System.nanoTime();
                insertionSort(insArr, 0, size);
                long insertionEnd = System.nanoTime();
                insTime += (insertionEnd - insertionStart)/10;
                
                //track performance of merge sort
                long mergeStart = System.nanoTime();
                mergeSort(mergeArr, 0, size-1);
                long mergeEnd = System.nanoTime();
                mergeTime += (mergeEnd - mergeStart)/10;
                
                //track performance of quick sort 1
                long q1Start = System.nanoTime();
                quickSort1(q1Arr, 0, size-1);
                long q1End = System.nanoTime();
                q1Time += (q1End - q1Start)/10;
                
                //track performance of quick sort 2
                long q2Start = System.nanoTime();
                quickSort2(q2Arr, 0, size-1);
                long q2End = System.nanoTime();
                q2Time += (q2End - q2Start)/10;
                
                //track performance of quick sort 3
                long q3Start = System.nanoTime();
                quickSort3(q3Arr, 0, size-1);
                long q3End = System.nanoTime();
                q3Time += (q3End - q3Start)/10;
                
                //saves respective arrays for n up to 32 to a file for verification purposes 
                if(size <= 32)
                {
                   saveArrToFile(insArr, "InsertionSortArray-"+size+"-"+i+".txt");
                   saveArrToFile(mergeArr, "MergeSortArray-"+size+"-"+i+".txt");
                   saveArrToFile(q1Arr, "QuickSort1Array-"+size+"-"+i+".txt");
                   saveArrToFile(q2Arr, "QuickSort2Array-"+size+"-"+i+".txt");
                   saveArrToFile(q3Arr, "QuickSort3Array-"+size+"-"+i+".txt");
                }
            }
            
            //print average times to console
            System.out.println("Sorts of Size " + size);
            System.out.println("Insertion Sort Time: " + (insTime));
            System.out.println("Merge Sort Time: " + (mergeTime));
            System.out.println("Quick Sort 1 Time: " + (q1Time));
            System.out.println("Quick Sort 2 Time: " + (q2Time));
            System.out.println("Quick Sort 3 Time: " + (q3Time));
            System.out.println();
            //add average times to string builder
            builder.append(insTime+",");
            builder.append(mergeTime+",");
            builder.append(q1Time+",");
            builder.append((q2Time)+",");
            builder.append((q3Time)+"");
            builder.append('\n');
        }
        builder.append('\n');
        //write to csv
        PrintWriter pw = new PrintWriter(new File("results.csv"));
        pw.write(builder.toString());
        pw.close();
        // */
    }
}
