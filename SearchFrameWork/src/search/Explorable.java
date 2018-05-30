package search;

import java.util.List;

/**
 * Created by alde on 5/28/18.
 */
public interface Explorable<T> extends Status{
    List<Explorable<T>> expand();

    boolean isBetter(Explorable<T> other);

    T getValue();
}
