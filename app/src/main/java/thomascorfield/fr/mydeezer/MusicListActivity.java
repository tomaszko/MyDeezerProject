package thomascorfield.fr.mydeezer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import thomascorfield.fr.mydeezer.R;

public class MusicListActivity extends Activity {

    private EditText artistEditText;
    private Button searchButton;
    private ListView listView;

    private static final int ACTION_SELECT = 333;
    private static final int ACTION_FAVORITE = 666;

    private ArrayList<Music> musics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        this.artistEditText = (EditText) findViewById(R.id.artistEditText);
        this.searchButton = (Button) findViewById(R.id.searchButton);
        this.listView = (ListView) findViewById(R.id.listView);

        this.musics = Music.getAllMusics(5);

        MusicAdapter adapter = new MusicAdapter();

       //adapter = new ArrayAdapter<Music>(this, android.R.layout.simple_list_item_1, this.musics);

        this.listView.setAdapter(adapter);

        View.OnCreateContextMenuListener listener = new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

                // Cast necessaire dans le contexte d'un ContextMenuListener
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;

                Music musicSelected = musics.get(info.position);

                String favorisMenuText = musicSelected.isFavorite() ? "Retirer des favoris" : "Ajouter aux favoris";

                menu.setHeaderTitle(musicSelected.getTitle());
                menu.add(Menu.NONE, ACTION_SELECT, 0, "Select");
                menu.add(Menu.NONE, ACTION_FAVORITE, 1, favorisMenuText);
            }
        };

        this.listView.setOnCreateContextMenuListener(listener);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Music musicSelected = musics.get(position);
                onMusicSelected(musicSelected);
            }

        };

        this.listView.setOnItemClickListener(clickListener);

    }

    private void onMusicSelected(Music musicSelected) {
        //Toast.makeText(this, musicSelected.getTitle(), Toast.LENGTH_LONG).show();
        Intent showDetailActivityIntent = new Intent(this, MusicActivity.class);
        showDetailActivityIntent.putExtra(MusicActivity.MUSIC_SELECTED, musicSelected);

        startActivity(showDetailActivityIntent);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Music musicSelected = musics.get(info.position);

        switch (item.getItemId()) {

            case ACTION_SELECT:
            onMusicSelected(musicSelected);
            break;

            case ACTION_FAVORITE:
            musicSelected.setFavorite(!musicSelected.isFavorite());
            this.listView.setAdapter(new MusicAdapter());
            Toast.makeText(this, "durée" + musicSelected.getDuration(), Toast.LENGTH_LONG).show();
            break;

            default:
            break;

        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.music_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MusicAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return musics.size();
        }

        @Override
        public Object getItem(int i) {
            return musics.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            // Récupérer la musique à afficher
            Music musicToDisplay = (Music) getItem(i);

            // Récupérer une nouvelle cellule
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            View cell = inflater.inflate(R.layout.music_cell, null);

            ImageView coverImageView = (ImageView) cell.findViewById(R.id.coverImageView);
            ImageView favoriteImageView = (ImageView) cell.findViewById(R.id.favoriteImageView);
            TextView titleTextView = (TextView) cell.findViewById(R.id.titleTextView);
            TextView albumTextView = (TextView) cell.findViewById(R.id.albumTextView);

            titleTextView.setText(musicToDisplay.getTitle());
            albumTextView.setText(musicToDisplay.getAlbum());
            favoriteImageView.setAlpha(musicToDisplay.isFavorite() ? 1f : 0f);

            return cell;
        }
    }
}
