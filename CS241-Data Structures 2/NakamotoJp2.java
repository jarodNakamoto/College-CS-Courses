
/**
 * Write a description of class NakamotoJp2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NakamotoJp2
{
    /**
     * Constructor for objects of class NakamotoJp2
     */
    public NakamotoJp2()
    {
        // initialise instance variables
    }
    
    //heap sort methods

    public static int heapSort(int[] heap, int n, boolean printArray)
    {
        //takes in array and heap sorts it
        int numSwaps = 0;
        int[] cloneOfOriginal = null;
        
        //if necessary to print array, create clone
        if(printArray)
        {
             cloneOfOriginal = cloneArray(heap);
        }
        
        //create first heap
        for(int start = n/2 - 1; start >= 0; start--)
        {
            numSwaps += reheapify(heap, start, n - 1, printArray, cloneOfOriginal);
        }
        swap(heap, 0, n - 1);
        numSwaps++;
        //if print array when changed is active
        if(printArray)
             printTwoArrays(cloneOfOriginal, heap);
             
        // do the sorting     
        for(int end = n - 2; end > 0; end--)
        {
            //take the unheaped heap and reheap it
            numSwaps += reheapify(heap, 0, end, printArray, cloneOfOriginal);
            
            //swap the front with the value at the last index of the current heap
            swap(heap, 0, end);
            numSwaps++;
            
            //if print array when changed is active
            if(printArray)
                printTwoArrays(cloneOfOriginal, heap);
        }
        
        return numSwaps;
    }
   
    private static int reheapify(int[] arr, int start, int end, boolean printArray, int[] cloneOfOriginal)
    {
        //heapify heap
        //keep count
        int numSwaps = 0;
        
        boolean done = false;
        int orphan = arr[start];
        int leftIndex = 2 * start + 1;
        while(!done && (leftIndex <= end))
        {
            int largerIndex = leftIndex; //assume larger
            int rightIndex = leftIndex + 1;
            if((rightIndex <= end) && arr[rightIndex] > arr[largerIndex])
            {
                largerIndex = rightIndex;
            }//end if
            if(orphan < arr[largerIndex])
            {
                arr[start] = arr[largerIndex];
                start = largerIndex;
                leftIndex = 2 * start + 1;
                numSwaps++;
                
                //if print array when changed is active
                if(printArray)
                     printTwoArrays(cloneOfOriginal, arr);
            }
            else
            {
                done = true;
            }
        }//end while
        arr[start] = orphan;
        
        return numSwaps;
    }//end reheap
    
    //swap values in the array with each other
    public static void swap(int[] arr, int first, int last)
    {
        int temp = arr[first];
        arr[first] = arr[last];
        arr[last] = temp;
    }//end swap
    
    
    //generate array methods
    public static int[] generateRandomArray()
    {
        //creates a new array of 100 integers and fills it randomly from 100 to 900
        int[] arr = new int[100];
        
        for(int i = 0; i < 100; i++)
        {
            arr[i] = (int)(Math.random()*800) + 100;
        }
        
        return arr;
    }//end generateRandomArray
    
    //general array methods
    
    //print out an array
    public static void printArray(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            //print out the entry in the array at index i
            System.out.print(arr[i]+ " ");
            //make a new line every 20 entries
            if((i+1)%20 == 0)
            {
                System.out.println();
            }
        }
        System.out.println();
    }//end printArray
    
    //print out original array and full array
     public static void printTwoArrays(int[] original, int[] altered)
    {
        //print original array
        System.out.println("Original Array:");
        printArray(original);
        System.out.println();
        
        //print altered array
        System.out.println("Altered Array:");
        printArray(altered);
        System.out.println();
    }//end printTwoArrays
    
    //return a clone of an array
    public static int[] cloneArray(int[] arr)
    {
        int[] newArr = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            newArr[i] = arr[i];
        
        return newArr;
    }
    
    //Main method
    public static void main(String[] args)
    {
        NakamotoJp2 project = new NakamotoJp2();
        
        //part one
        System.out.println("Part One");
        System.out.println();
        int[] arr = {12, 19, 26, 13, 20, 27, 14, 21, 28, 15, 22, 29, 16, 23, 10, 17, 24, 11, 18, 25};
        printArray(arr);
        System.out.println();
        heapSort(arr, arr.length, true);
        printArray(arr);
        System.out.println();
        //end of part one
        
        //part two
        System.out.println("Part Two");
        System.out.println();
        
        int totalNumSwaps = 0;
        for(int i = 1; i <= 20; i++)
        {
           System.out.print("Number of swaps for array number " + i + ":    ");
           int[] arr2 = generateRandomArray();
           //printArray(arr2);
           int numSwaps = heapSort(arr2, 100, false);
           System.out.print(numSwaps);
           totalNumSwaps += numSwaps;
           System.out.println();
        }
        System.out.println();
        System.out.println("Average number of swaps:    "  + (totalNumSwaps/20.0));
         System.out.println();
        //end of part two
    }//end main
}
