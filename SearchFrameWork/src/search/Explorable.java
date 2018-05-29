package search;

import java.util.List;

/**
 * Created by alde on 5/28/18.
 */
public interface Explorable extends Status{
    List<Explorable> expand();


}
