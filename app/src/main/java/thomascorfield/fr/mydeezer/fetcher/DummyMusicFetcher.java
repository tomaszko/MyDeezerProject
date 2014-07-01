package thomascorfield.fr.mydeezer.fetcher;

import java.util.ArrayList;

import thomascorfield.fr.mydeezer.fetcher.MusicFetcher;
import thomascorfield.fr.mydeezer.fetcher.OnMusicFetcherResultListener;
import thomascorfield.fr.mydeezer.model.Music;

public class DummyMusicFetcher implements MusicFetcher {
    @Override
    public void fetchMusicsForArtist(String artistName, OnMusicFetcherResultListener listener) {

        ArrayList<Music> list = new ArrayList<Music>();

        for (int i = 0; i < 30; i++) {

            Music m = new Music();
            m.setTitle("Track" + (i + 1));
            m.setArtist("Artist");
            m.setAlbum("Album");
            m.setFavorite(i % 2 == 0);
            m.setDuration(145);
            m.setSampleURL(null);
            m.setLink("http://www.deezer.com");
            m.setCoverURL(null);

            list.add(m);
        }

        listener.onMusicFetcherResult(list, null);
    }
}
