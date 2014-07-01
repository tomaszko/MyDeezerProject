package thomascorfield.fr.mydeezer.fetcher;

import org.json.JSONObject;

public interface OnConnectionResultListener {

    public void onConnectionResult(JSONObject result, Exception e);
}
