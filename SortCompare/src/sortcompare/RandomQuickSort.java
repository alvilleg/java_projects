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
public class RandomQuickSort {

    private long executionTime;
    private long countCompares;

    public RandomQuickSort() {
        executionTime = 0;
        countCompares = 0;
    }

    public void quickSort(int a[], int p, int r) {
        if (p < r) {
            int q = partition(a, p, r);
            quickSort(a, p, q - 1);
            quickSort(a, q, r);
        }
    }

    public int partition(int a[], int p, int r) {
        int randomPos = (int) (Math.random() * (r - p));
        exchange(a, ((p + randomPos) + 1), r);

        int x = a[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (a[j] <= x) {
                i++;
                exchange(a, i, j);                
            }
            countCompares++;
        }
        exchange(a, i + 1, r);
        return i + 1;
    }

    public void exchange(int a[], int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public void sort(int a[]) {
        long initialTime = System.nanoTime();
        countCompares=0;
        quickSort(a, 0, a.length - 1);
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

    public static void main(String arg[]) {
        RandomQuickSort qSort = new RandomQuickSort();
        int a[] = {987, 7, 2, 99, 84, 37, 54, 10, 1, 2, 1, 2, 5, 8, 9, 3};
        qSort.partition(a, 0, 9);
        qSort.sort(a);
        
        System.out.println("exec time:" + qSort.getExecutionTime());
    //401377
    //28356

    }
}
