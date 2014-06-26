package thomascorfield.fr.mydeezer;

import java.io.Serializable;
import java.util.ArrayList;

public class Music implements Serializable {

    private String title;
    private String artist;
    private String album;
    private int duration;
    private boolean favorite;
    private String sampleURL;
    private String coverURL;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getSampleURL() {
        return sampleURL;
    }

    public void setSampleURL(String sampleURL) {
        this.sampleURL = sampleURL;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }

    public static Music getDefaultMusic () {

        Music m = new Music();
        m.setTitle("Twist");
        m.setArtist("Korn");
        m.setAlbum("Life is Peachy");
        m.setFavorite(true);
        m.setDuration(145);
        m.setSampleURL(null);
        m.setLink("http://www.deezer.com");
        m.setCoverURL(null);

        return m;
    }

    public static ArrayList<Music> getAllMusics (int size) {

        ArrayList<Music> list = new ArrayList<Music>();

        for (int i = 0; i < size; i++) {

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

        return list;
    }
}
