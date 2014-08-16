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
public class InsertionSort {

    private long executionTime;
    private long countCompares;

    public InsertionSort() {
        executionTime = 0;
        countCompares = 0;
    }

    public void sort(int a[]) {
        long initialTime = System.nanoTime();
        countCompares=0;
        int n = a.length;
        for (int j = 1; j < n; j++) {
            int k = a[j];
            int i = j-1;
            while ( (i>=0) && (k<a[i])) {
                a[i+1] = a[i];
                i=i-1;
                countCompares++;
            }
            a[i+1]=k;
        }
        long finalTime = System.nanoTime();
        executionTime = finalTime - initialTime;
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
        InsertionSort insSort = new InsertionSort();
        int a[] = {1,2,5,8,9,3,7,29,10,12};
//        mergeSort.merge(a, 0, 4, 9);
        insSort.sort(a);
        
        System.out.println("exec time:"+insSort.getExecutionTime());
    }

}
