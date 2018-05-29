package search.algorithms;

import search.Explorable;
import search.Status;

/**
 * Created by alde on 5/28/18.
 */
public interface ISearch {

    public Status search(Explorable initial);
}
