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

public class MergeSort {
    private long executionTime;
    private long countCompares;
    public MergeSort(){
        executionTime = 0;
        countCompares = 0;
    }

    public void mergeSort(int a[],int p,int r){
        int q=0;
        if(p<r){
            q = ((p+r)/2);
            mergeSort(a,p,q);
            mergeSort(a,q+1,r);
            merge(a,p,q,r);
        }
    }

    public void merge(int a[],int p,int q,int r){                
        int n1 = (q-p)+1;
        int n2 = r-q;                
        int L[] = new int[n1+1];
        int R[] = new int[n2+1];

        for(int i=0;i<n1;i++){
            L[i] = a[p+i];
        }
        
        for(int i=0;i<n2;i++){
            R[i] = a[i+q+1];
        }        
        
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;        
        
        int i=0;
        int j=0;
        for(int k=p;k<=r;k++){            
            if(L[i]<R[j]){
                a[k]=L[i];
                i++;
            }
            else{
                a[k]=R[j];
                j++;
            }
            countCompares++;
        }        
    }

    public void sort(int a[]){
        long initialTime = System.nanoTime();
        countCompares = 0;
        mergeSort(a, 0, a.length-1);

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
        MergeSort mergeSort = new MergeSort();
        int a[] = {1,2,5,8,9,3,7,29,10,12};
//        mergeSort.merge(a, 0, 4, 9);
        mergeSort.sort(a);
        
        System.out.println("exec time:"+mergeSort.getExecutionTime());
    }
}
