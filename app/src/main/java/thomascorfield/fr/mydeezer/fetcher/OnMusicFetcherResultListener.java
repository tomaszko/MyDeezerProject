package thomascorfield.fr.mydeezer.fetcher;

import java.util.ArrayList;

import thomascorfield.fr.mydeezer.model.Music;

public interface OnMusicFetcherResultListener {

    public void onMusicFetcherResult(ArrayList<Music> results, Exception e);
}
