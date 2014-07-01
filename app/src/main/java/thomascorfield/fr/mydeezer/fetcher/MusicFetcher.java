package thomascorfield.fr.mydeezer.fetcher;


import java.util.ArrayList;

import thomascorfield.fr.mydeezer.model.Music;

public interface MusicFetcher {

    public void fetchMusicsForArtist(String artistName, OnMusicFetcherResultListener listener);
}
