/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sortcompare;

/**
 * 
 * @author Aldemar
 * @version $Id$
 * 
 *          O(nlgn) ???
 * 
 *          Implementation of shell sort use delta insertion sort
 */
public class ShellSort {

    private long executionTime;
    private long countCompares;

    public ShellSort() {
        executionTime = 0;
        countCompares = 0;
    }

    public void sort(int a[]) {
        long initialTime = System.nanoTime();
        int n = a.length;
        int d = (int) (n / 2);
        while (d > 0) {
            print(a);
            for (int i = d; i < n; i++) {
                int j = i;
                int t = a[i];
                while ((j >= d) && (a[j - d] > t)) {
                    a[j] = a[j - d];
                    j = j - d;
                    countCompares++;
                }
                a[j] = t;
            }
            d = (int) (d / 2);
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

    public void print(int a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
    }

    public static void main(String arg[]) {
        ShellSort sort = new ShellSort();
        InsertionSort isort = new InsertionSort();
        int a[] = { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7, 25, 32, 6, 13, 12, 12}; // 7,
                                                                             // 29,
                                                                             // 10,
                                                                             // 12,1,
                                                                             // 2,
                                                                             // 5,
                                                                             // 8,
                                                                             // 9,
                                                                             // 30,98};
        int a2[] = { 124, 32, 32, 25, 25, 16, 16, 14, 14, 13, 13, 12, 12, 12,
                10, 10, 9, 9, 8, 8, 7, 7, 6, 6, 4, 3, 3, 2, 2, 1, 1}; // 7, 29,
                                                                      // 10,
                                                                      // 12,1,
                                                                      // 2, 5,
                                                                      // 8, 9,
                                                                      // 30,98};
        sort.sort(a);
        isort.sort(a2);
        sort.print(a);
        System.out.println("exec time:" + sort.getExecutionTime() + " "
                + isort.getExecutionTime());
        System.out.println("exec time:" + sort.getCountCompares() + " "
                + isort.getCountCompares());
        int d = 16;
        int n = d;
        int s = 0, k = 0;
        while (d > 0) {
            k++;
            for (int i = d; i < n; i++) {
                s += (int) (i / (n / Math.pow(2, k)));
            }
            d = (int) d / 2;
        }
        System.out.println(k + " ==> " + s);
    }
}
