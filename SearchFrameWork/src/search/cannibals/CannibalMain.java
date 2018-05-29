package search.cannibals;

import search.Status;
import search.algorithms.BFS;
import search.algorithms.DFS;
import search.algorithms.ISearch;

/**
 * Created by alde on 5/28/18.
 */
public class CannibalMain {

    public static void main(String a[]) {
        CannibalNode initialNode = new CannibalNode(3,3,null,3,0);
        ISearch search = new BFS();
        Status result = search.search(initialNode);
        System.out.println("BFS::");
        System.out.print(result.print());

        //
        search = new DFS();
        result = search.search(initialNode);
        System.out.println("DFS::");
        System.out.print(result.print());
        //*/
        System.exit(0);
    }
}
