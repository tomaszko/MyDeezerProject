package thomascorfield.fr.mydeezer.model;

public interface favoritesDAO {

    public void addFavorite(Music music);

    public void removeFavorite(Music music);

    public boolean isFavorite(Music music);
}