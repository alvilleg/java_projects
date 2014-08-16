/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sortcompare;

/**
 *
 * @author Aldemar
 * @version $Id$
 */
public class StupidSort {

    private long executionTime;
    private long countCompares;

    public StupidSort() {
        executionTime = 0;
        countCompares = 0;
    }

    public void sort(int a[]) {
        long initialTime = System.nanoTime();
        countCompares=0;
        int n = a.length;
        sort_aux(a,0,n-1);
        long finalTime = System.nanoTime();
        executionTime = finalTime - initialTime;
    }

    public void sort_aux(int a[],int i,int j){        
        if(a[i]>a[j]){
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        if((i+1)>= j){            
            return;
        }
        
        int k = (j-i+1)/3;
        sort_aux(a,i,j-k);
        sort_aux(a,i+k,j);        
        sort_aux(a,i,j-k);        
    }
    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public long getCountCompares() {
        return countCompares;
    }

    public void setCountCompares(long countCompares) {
        this.countCompares = countCompares;
    }    

    public static void main(String arg[]){
        StupidSort insSort = new StupidSort();
        int a[] = {8,9,3,29,10,12,1,2,5,-90};
//        mergeSort.merge(a, 0, 4, 9);
        insSort.sort(a);

        for(int i=0;i<a.length;i++){
            System.out.print(";"+a[i]);
        }
        System.out.println("\nexec time:"+insSort.getExecutionTime());
    }

}
