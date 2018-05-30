package search.countdown;

import search.Status;
import search.algorithms.BFS;
import search.algorithms.DFS;
import search.algorithms.ISearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by alde on 5/29/18.
 */
public class CountDownMain {


    public static void main(String a[]) {
        List<Long> numbers = new ArrayList<Long>();
        List<Long> numbers2 = new ArrayList<Long>();
        //
        // 100 100 100 100 100 75 345
        numbers.add(100L);
        numbers.add(100L);
        numbers.add(100L);
        numbers.add(100L);
        numbers.add(100L);
        numbers.add(75L);
        // 1 3 1 10 100 75 345
        numbers2.add(1L);
        numbers2.add(3L);
        numbers2.add(1L);
        numbers2.add(10L);
        numbers2.add(100L);
        numbers2.add(75L);
        //
        Long l[] = {1L, 2L, 2L, 2L, 3L, 11L}; // 275
        Long l2[] = {1L, 75L, 100L, 5L, 3L, 25L}; // 25

        ArrayList<Long> arrayList = new ArrayList<Long>(Arrays.asList(l));

        CountDownNode initialNode = new CountDownNode(0, 999, arrayList);
        ISearch search = new BFS<CountDownValue>();
        Status result = search.search(initialNode);
        System.out.println("BFS::");
        System.out.print(result.print());
        //
        search = new DFS<CountDownValue>();
        result = search.search(initialNode);
        System.out.println("DFS::");
        System.out.print(result.print());
        //*/
        System.exit(0);
    }
}
