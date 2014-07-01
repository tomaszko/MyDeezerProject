package thomascorfield.fr.mydeezer.model;

import java.util.ArrayList;

public interface RecentRepository {

    public void add(String request);

    public ArrayList<String> getRequestMatchingPredicate(String predicate);
}