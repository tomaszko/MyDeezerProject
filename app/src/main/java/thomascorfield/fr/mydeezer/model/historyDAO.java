package thomascorfield.fr.mydeezer.model;

import java.util.ArrayList;

public interface historyDAO {

    public void addToHistory(String request);

    public ArrayList getRequestMatchingPredicate(String predicate);
}