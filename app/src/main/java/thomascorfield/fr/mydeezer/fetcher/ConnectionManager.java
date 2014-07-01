package thomascorfield.fr.mydeezer.fetcher;

public interface ConnectionManager {

    public void performURLRequest (String url, OnConnectionResultListener listener);
}
