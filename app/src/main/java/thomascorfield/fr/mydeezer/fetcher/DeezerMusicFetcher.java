package thomascorfield.fr.mydeezer.fetcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import thomascorfield.fr.mydeezer.model.Music;

public class DeezerMusicFetcher implements MusicFetcher {

    private ConnectionManager connectionManager;

    public DeezerMusicFetcher(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void fetchMusicsForArtist(String artistName, final OnMusicFetcherResultListener listener) {

        String req = "http://api.deezer.com/search?q=" + artistName.replace(" ", "+").trim();

        this.connectionManager.performURLRequest(req, new OnConnectionResultListener() {

            @Override
            public void onConnectionResult(JSONObject result, Exception e) {

            if (e != null) {

                listener.onMusicFetcherResult(null, e);

            } else {

                try {

                    ArrayList<Music> list = new ArrayList<Music>();
                    JSONArray data = result.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {

                        JSONObject item = data.getJSONObject(i);
                        Music m = new Music("0");
                        m.setTitle(item.getString("title"));
                        m.setArtist(item.getJSONObject("artist").getString("name"));
                        m.setAlbum(item.getJSONObject("album").getString("title"));
                        m.setDuration(item.getInt("duration"));
                        m.setSampleURL(item.getString("preview"));
                        m.setLink(item.getString("link"));
                        m.setCoverURL(item.getJSONObject("album").getString("cover"));
                        m.setFavorite(false);

                        list.add(m);
                    }

                    listener.onMusicFetcherResult(list, null);

                } catch (JSONException e1){

                    listener.onMusicFetcherResult(null, e1);
                }
            }
            }
        });
    }
}
