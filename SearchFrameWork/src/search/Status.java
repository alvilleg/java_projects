package search;

/**
 * Created by alde on 5/28/18.
 */
public interface Status {
    public boolean isFinal();

    public String print();

    boolean isValid();

    String getKey();
}
