
/**
 * Write a description of class Project2 here.
 * 
 * @author (your name) Jarod Nakamoto
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class Project2
{
    public static int Prim (int[][] Cost, int n, int[][] T)
    {
        //Since in java matrices and arrays are 0 to size-1, some indexes 
        //in the algorithm should be 1 less in value
        int MinCost = 0;
        
        //creates Near array, indices 0 to n-1
        // by default Near(0) = 0
        int[] Near = new int[n];
        int i, j, k, min, m; //variables that are going to be used alot 
                            // are declared up here to minimize garbage collection
        //for i = 1 to n-1 do Near(i) = 1
        for(i = 1; i < n; i++)
        {
            Near[i] = 1;
        }
        Near[0] =0;
        //for i = 0 to n-2 do 
        for(i = 0; i < (n-1); i++)
        {
            //let j be an index s.t. (Near(j) != 0) and Cost(j, Near(j)) is minimum;
            j = -1;
            //find j
            min = 999;
            for(m = 0; m < n;m++)
            {
                if(Near[m] != 0 && Cost[m][Near[m]-1] < min)
                {
                    j = m;
                    min = Cost[m][Near[m]-1];
                }
            }
            //modified to stop if we have a vertex that don't have edges
            if(j != -1)
            {
                //(T(i,1),T(i,2)) = (j, Near(j)); // add edge to the MST
                T[i][0] = j+1;
                T[i][1] = Near[j];
                //MinCost = MinCost + Cost            //(j, Near(j));
                MinCost += Cost[j][Near[j]-1];
                Near[j] = 0;
                //for k=0 to n-1 do
                for(k = 0; k < n; k++)
                {
                    if(Near[k] != 0 && Cost[k][Near[k]-1] > Cost[k][j])
                    {
                        Near[k] = j+1; //update cost if lower
                    }
                }
            }
        }
        return MinCost;
    }
   
    public static int Kruskal(int[][] Cost,int n,ArrayList<Edge> T,L l)
    {
        int minCost = 0;
        //create array to represent the tree
        int[] A = new int[n];
        for(int i = 0; i < n; i++)
        {
            A[i] = i;
        }
        int[] height = new int[n];
        ArrayList<Edge> sortedEdges = new ArrayList<Edge>();
        
        for(int r = 0; r< n;r++)
        {
            for(int c = 0; c < n; c++)
            {
                if(Cost[r][c]  != 0 && Cost[r][c] != 999)
                    sortedEdges.add(new Edge(Cost[r][c], r, c));
            }
        }
        
        //sort sortedEdges
        quickSort2(sortedEdges, 0, sortedEdges.size());
        //insertionSort(sortedEdges, 0, sortedEdges.size());
        
                
        int a = -1, b = -2, oldA, oldB,find2A,find2B;
        Edge e;
        long kruskalStart = System.nanoTime();
        //While (T contains fewer than n-1 edges) and (E not empty ) do
        while(T.size() < n-1 && !sortedEdges.isEmpty())
        {
            //choose an edge (u,v) from E of the lowest cost;
            e = sortedEdges.remove(0);
            //delete (u,v) from E
            
            //if(u,v)doesnt create a cycle in T
            oldA = a;
            oldB = b;
            a = e.row;
            b = e.col;
            find2A = a;
            find2B = b;
            while(A[find2A] != find2A)
            {
                find2A = A[find2A];
            }
            while(A[find2B] != find2B)
            {
                find2B = A[find2B];
            }
            //there shouldn't be any max values or 0's in sortedEdges
            //System.out.println("find2A and B: " + find2A + "  " + find2B + "\n");
            if((oldA == a && oldB == b))
            {
            }
            //else if(Find2(a,A) != Find2(b,A))
            
            else if(find2A != find2B)
            {
                //Merge3(A,height,a,b);
                //merge sets labeled a and b
                if(height[a] == height[b])
                {
                    A[b] = a;
                    height[a] = height[a]+1;
                }
                else
                {
                    if (height[a] > height[b] )
                        A[b] = a;
                    else 
                        A[a] = b;
                }
                //System.out.println("t's size: " + T.size() + "\n");
                T.add(e);
                minCost += e.cost;
            }
            //then add (u,v)
            //else discard (u,v)
        }
        long kruskalEnd = System.nanoTime();
        long kruskalTime = kruskalEnd - kruskalStart;
        l.val = kruskalTime;
        return minCost;
    }
    
    public static void genMatrix(int n,int e, int[][] Cost)
    {
        int num = e;
        while(num != 0)
        {
            int r = (int)(Math.random()*n);
            int c = (int)(Math.random()*n);
            if(r != c)
            {
                int cost = (int)(Math.random() * 100) + 1;
                Cost[r][c] = cost;
                Cost[c][r] = cost;
                num--;
            }
        }
        for(int r = 0; r < n; r++)
        {
            for(int c = 0; c < n; c++)
            {
                if(r != c && Cost[r][c] == 0)
                {
                    Cost[r][c] = 999;
                    Cost[c][r] = 999;
                }
            }
        }
    }
    public static void insertionSort(ArrayList<Edge> arr,int start, int end)
    {
        //System.out.println("Insertion Sort");
        //for i = start to end-1
        for(int i = start; i < end;i++)
        {
            //j <- i
            int j = i;
            //while j > 0 and arr[j-1] > arr[j]
            while(j > 0 && arr.get(j-1).cost > arr.get(j).cost)
            {
                //swap arr[j] and arr[j-1]
                swap(arr, j, j-1);
                //j <- j-1
                j--;
            }
        }
    }
    //quicksort is fast so use that
   public static void quickSort2(ArrayList<Edge> arr, int p, int q)
    {
	if(q == arr.size())
	    q--;
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
    public static int Partition(ArrayList<Edge> arr, int first, int last)
    {
        //System.out.println("Partition");
        Edge pivotE = arr.get(first);
        int pivot = pivotE.cost,tb = first+1,ts = last;
        //loop //while(tb <= ts)
        while(tb <= ts)//for(int tb = first+1;tb<ts;tb++) 
        {
            while(tb <= last && arr.get(tb).cost <= pivot){tb++;}
            while(ts > first &&  arr.get(ts).cost > pivot){ts--;}
            if(tb < ts){
                swap(arr,tb,ts);
            }
        }
        //System.out.println("ts: " + ts);
        if(ts > -1)
        {
            arr.set(first, arr.get(ts));
            arr.set(ts, pivotE);
        }
        return ts;
    }
    
    public static void swap(ArrayList<Edge> arr, int a, int b)
    {
        if(a < arr.size() && b > -1)
        {
            Edge temp = arr.get(a);
            arr.set(a, arr.get(b));
            arr.set(b, temp);
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        //make a string builder to be added to keep track of the things
        //to be added to a spreadsheet
        StringBuilder builder  = new StringBuilder();
        StringBuilder n10 = new StringBuilder();
        //title row
        builder.append("n,e,PrimTime,KruskalTime");
        builder.append('\n');
        //n = 10 to 90
        for(int n = 10; n < 100; n+=10)
        {
            //figure out the number of edges for a full graph
            int E = (n * (n-1) / 2);
            //to test with e edges from .2E to E
            for(double e = .2 * E; e <= E; e += .2 * E)
            {
                int[][] Cost = new int[n][n];
                int[][] T = new int[n][2];
                genMatrix(n,(int)e,Cost);
                
                // add n and e to builder
                builder.append(n+",");
                builder.append(e+",");
                //do prims
                long primStart = System.nanoTime();
                int primCost = Prim(Cost,n,T);
                long primEnd = System.nanoTime();
                long primTime = primEnd-primStart;
                // add prim time to builder
                builder.append(primTime+",");
                
                //lets us keep the mst
                ArrayList<Edge> t = new ArrayList<Edge>();
                //lets us keep the time
                L kruskalTime = new L((long)-1);
                //do kruskals
                int kruskalCost = Kruskal(Cost,n,t,kruskalTime);
                // add kruskal time to builder
                builder.append(kruskalTime.val+"\n");
                
                if(n == 10)
                {
                    n10.append("e: " + e + "\n");
                    n10.append("Prims: \n");
                    n10.append("Cost: " + primCost + "\n");
                    for(int j = 0; j < n; j++)
                        n10.append("("+T[j][0]+","+T[j][1]+")  ");
                    n10.append("\n");
                    n10.append("Kruskals: \n");
                    n10.append("Cost: " + kruskalCost + "\n");
                    n10.append("t's size: " + t.size() + "\n");
                    for(int j = 0; j < t.size(); j++)
                    {
                        Edge ed = t.get(j);
                        n10.append("("+ed.row+","+ed.col+")  ");
                    }
                    n10.append("\n");
                    n10.append("Cost Matrix: \n");
                    for(int r = 0; r < n; r++)
                    {
                        for(int c = 0; c < n; c++)
                             n10.append(Cost[r][c]+"  ");
                        n10.append("\n");
                    }
                    n10.append("\n\n");
                }
            }
        }
         n10.append("\n");
        //write to csv
        PrintWriter pw = new PrintWriter(new File("outputN10.txt"));
        pw.write(n10.toString());
        pw.close();
	
        //n =100 to 1000
        for(int n = 100; n <= 1000; n+=100)
        {
            int E = n * (n-1) / 2;
            //to test with e edges from .2E to E
            for(double e = .2 * E; e <= E; e += .2 * E)
            {
                int[][] Cost = new int[n][n];
                int[][] T = new int[n][n];
                genMatrix(n,(int)e,Cost);
                
                // add n and e to builder
                builder.append(n+",");
                builder.append(e+",");
                //do prims
                long primStart = System.nanoTime();
                int primCost = Prim(Cost,n,T);
                long primEnd = System.nanoTime();
                long primTime = primEnd-primStart;
                // add prim time to builder
                builder.append(primTime+",");
                
                //lets us keep the mst
                ArrayList<Edge> t = new ArrayList<Edge>();
                //lets us keep the time
                L kruskalTime = new L((long)-1);
                //do kruskals
                Kruskal(Cost,n,t,kruskalTime);
                // add kruskal time to builder
                builder.append(kruskalTime.val+"\n");
            }
        }
        
        builder.append('\n');
        //write to csv
        pw = new PrintWriter(new File("output.csv"));
        pw.write(builder.toString());
        pw.close();
    }
    
    //holds the values needed for Edge
    public static class Edge
    {
        int cost;
        int row;
        int col;
        
        public Edge(int c, int x, int y)
        {
            cost = c;
            row = x;
            col = y;
        }
    }
    //holds the time for kruskal's
    public static class L
    {
        long val;
        public L(long lg)
        {
            val = lg;
        }
    }
}
